mod models;
mod db;
mod templates;

use clap::{Parser, Subcommand};
use dotenvy::dotenv;
use sqlx::postgres::PgPoolOptions;
use std::env;
use std::fs;
use std::io::Write;

#[derive(Parser)]
#[command(name = "logic-chisel")]
#[command(about = "Minecraft Mod Code Generator", long_about = None)]
struct Cli {
    #[command(subcommand)]
    command: Commands,
}

#[derive(Subcommand)]
enum Commands {
    /// Initialize the environment
    Init,
    /// Generate Mod Source Code
    Generate,
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    // .envファイルがあれば読み込む
    dotenv().ok();

    let cli = Cli::parse();

    match cli.command {
        Commands::Init => {
            println!("Initializing LogicChisel environment...");
            // テンプレートディレクトリ確認
            if !fs::metadata("templates").is_ok() {
                println!("Creating templates directory...");
                fs::create_dir("templates")?;
                // デフォルトテンプレートの作成などはここで行うと良い
            }
            println!("Environment initialized.");
        }
        Commands::Generate => {
            println!("Connecting to database...");
            let database_url = env::var("DATABASE_URL").expect("DATABASE_URL must be set");

            let pool = PgPoolOptions::new()
                .max_connections(5)
                .connect(&database_url)
                .await?;

            println!("Checking database connection...");
            db::check_connection(&pool).await?;
            println!("Connection successful!");

            println!("Initializing Template Engine...");
            let tera = templates::init_tera()?;

            println!("Fetching game objects...");
            let objects = db::fetch_all_game_objects(&pool).await?;
            println!("Found {} objects.", objects.len());

            println!("Generating ModItems.java...");
            let rendered_items = templates::render_items(&tera, &objects)?;
            
            // 出力ディレクトリ作成
            fs::create_dir_all("generated/java")?;
            fs::create_dir_all("generated/assets/models/item")?; // Models用

            let mut file = fs::File::create("generated/java/ModItems.java")?;
            file.write_all(rendered_items.as_bytes())?;
            println!(" - generated/java/ModItems.java");

            println!("Generating ModBlocks.java...");
            let rendered_blocks = templates::render_blocks(&tera, &objects)?;
            let mut file = fs::File::create("generated/java/ModBlocks.java")?;
            file.write_all(rendered_blocks.as_bytes())?;
            println!(" - generated/java/ModBlocks.java");

            println!("Generating Item Models...");
            for obj in &objects {
                if obj.object_type == "ITEM" {
                    let rendered_json = templates::render_item_model(&tera, obj)?;
                    let path = format!("generated/assets/models/item/{}.json", obj.id);
                    let mut file = fs::File::create(&path)?;
                    file.write_all(rendered_json.as_bytes())?;
                    println!(" - {}", path);
                }
            }
        }
    }

    Ok(())
}

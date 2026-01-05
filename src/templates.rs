use tera::{Tera, Context};
use crate::models::GameObject;

pub fn init_tera() -> Result<Tera, tera::Error> {
    // templates ディレクトリ以下の全てのファイルを読み込む
    // 実行時のカレントディレクトリに templates があることを想定
    let tera = Tera::new("templates/**/*")?;
    Ok(tera)
}

pub fn render_items(tera: &Tera, objects: &[GameObject]) -> Result<String, tera::Error> {
    let mut context = Context::new();
    
    // アイテムのみをフィルタリング
    // (実際にはSQL側でフィルタリングするのが効率的だが、ここでは一旦全リストからフィルタ)
    let items: Vec<&GameObject> = objects.iter()
        .filter(|o| o.object_type == "ITEM")
        .collect();
        
    context.insert("items", &items);
    context.insert("mod_id", "logicchisel"); // TODO: Configから取得

    tera.render("ModItems.java.tera", &context)
}

pub fn render_blocks(tera: &Tera, objects: &[GameObject]) -> Result<String, tera::Error> {
    let mut context = Context::new();
    let blocks: Vec<&GameObject> = objects.iter()
        .filter(|o| o.object_type == "BLOCK")
        .collect();
        
    context.insert("blocks", &blocks);
    context.insert("mod_id", "logicchisel");

    tera.render("ModBlocks.java.tera", &context)
}

pub fn render_item_model(tera: &Tera, object: &GameObject) -> Result<String, tera::Error> {
    let mut context = Context::new();
    context.insert("mod_id", "logicchisel");
    
    // texture_path がプロパティにあると仮定
    let texture_path = object.properties.get("texture_path")
        .and_then(|v| v.as_str())
        .unwrap_or("missing_texture");
        
    context.insert("texture_path", texture_path);

    tera.render("item_model.json.tera", &context)
}

use sqlx::postgres::PgPool;
use crate::models::GameObject;

/// データベース接続を確認する
pub async fn check_connection(pool: &PgPool) -> Result<(), sqlx::Error> {
    sqlx::query("SELECT 1").execute(pool).await?;
    Ok(())
}

/// 全てのゲームオブジェクトを取得し、object_type と id でソートする
pub async fn fetch_all_game_objects(pool: &PgPool) -> Result<Vec<GameObject>, sqlx::Error> {
    let objects = sqlx::query_as::<_, GameObject>(
        r#"
        SELECT id, mod_id, object_type, display_name, properties
        FROM game_objects
        ORDER BY object_type, id
        "#
    )
    .fetch_all(pool)
    .await?;

    Ok(objects)
}

use serde::{Deserialize, Serialize};
use sqlx::FromRow;

#[derive(Debug, FromRow, Serialize, Deserialize)]
pub struct GameObject {
    /// MOD内での一意なID (例: prototype_sword)
    pub id: String,
    
    /// MOD ID (例: logicchisel)
    pub mod_id: String,
    
    /// オブジェクトの種類: 'ITEM' または 'BLOCK'
    pub object_type: String,
    
    /// ゲーム内表示名 (例: 最強の剣)
    pub display_name: String,
    
    /// 種類ごとの固有パラメータ
    pub properties: sqlx::types::Json<serde_json::Value>,
}

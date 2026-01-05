-- LogicChisel v2.0 Database Schema

-- game_objects: Minecraft内の全てのアイテム・ブロックを管理する単一テーブル
-- Generic Object Patternを採用

CREATE TABLE IF NOT EXISTS game_objects (
    -- MOD内での一意なID (例: prototype_sword)
    id TEXT PRIMARY KEY,

    -- MOD ID (例: logicchisel)
    mod_id TEXT NOT NULL,

    -- オブジェクトの種類: 'ITEM' または 'BLOCK'
    object_type TEXT NOT NULL,

    -- ゲーム内表示名 (例: 最強の剣)
    display_name TEXT NOT NULL,

    -- 種類ごとの固有パラメータ (JSONB)
    -- ITEMの場合: { "tool_type": "sword", "attack_damage": 9999 ... }
    -- BLOCKの場合: { "material": "stone", "hardness": 2.0 ... }
    properties JSONB NOT NULL,

    -- 作成日時
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 検索効率向上のためのインデックス (必要に応じて追加)
CREATE INDEX IF NOT EXISTS idx_game_objects_mod_id ON game_objects(mod_id);
CREATE INDEX IF NOT EXISTS idx_game_objects_object_type ON game_objects(object_type);

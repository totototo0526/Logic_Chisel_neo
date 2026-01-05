# LogicChisel v2.0 Setup Walkthrough

LogicChisel v2.0 の初期セットアップとCore CLIの実装が完了しました。
PostgreSQLからデータを取得し、Teraテンプレートエンジンを使ってMinecraft ModのJavaソースコードを自動生成する基盤が整いました。

## 実施した変更
### 1. プロジェクト構成
- `Cargo.toml` に必要な依存関係 (`sqlx`, `tokio`, `clap`, `tera`, `serde` 等) を追加しました。
- `schema.sql` を作成し、`Generic Object Pattern` に基づくデータベーススキーマを定義しました。

### 2. Core CLI 実装
- `src/models.rs`: DBのレコードをRustの構造体 (`GameObject`) にマッピング。
- `src/db.rs`: `sqlx` を使用した非同期DB接続とデータ取得ロジック。
- `src/main.rs`: `clap` を使用したCLIコマンド (`init`, `generate`) の実装。

### 3. テンプレートエンジン実装
- `src/templates.rs`: `Tera` の初期化とレンダリングヘルパー関数。
- `templates/`: 以下のテンプレートを作成しました。
    - `ModItems.java.tera`: アイテム登録用 (DeferredRegister)
    - `ModBlocks.java.tera`: ブロック登録用 (DeferredRegister + BlockItem)
    - `item_model.json.tera`: アイテムモデルJSON用

## 検証方法と実行手順

### 手順1: データベースの準備
PostgreSQLが動作しており、 `.env` に接続情報が設定されている必要があります。

```bash
# .env の例
DATABASE_URL=postgres://user:password@localhost/logicchisel
```

### 手順2: データベースのセットアップ (初回のみ)
`schema.sql` を実行してテーブルを作成し、テストデータを挿入します。

```bash
# psqlなどで実行
psql -d logicchisel -f schema.sql

# テストデータの挿入 (例)
INSERT INTO game_objects (id, mod_id, object_type, display_name, properties) VALUES
('test_sword', 'logicchisel', 'ITEM', 'Test Sword', '{"tool_type": "sword", "attack_damage": 10}'),
('test_block', 'logicchisel', 'BLOCK', 'Test Block', '{"material": "stone", "hardness": 1.5, "resistance": 6.0}');
```

### 手順3: コード生成の実行
CLIを実行してコードを生成します。

```bash
cargo run -- generate
```

成功すると、`generated/` ディレクトリ配下に以下のファイルが生成されます。
- `generated/java/ModItems.java`
- `generated/java/ModBlocks.java`
- `generated/assets/models/item/test_sword.json`

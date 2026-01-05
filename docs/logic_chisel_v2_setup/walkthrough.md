# LogicChisel v2.0 Setup Walkthrough

LogicChisel v2.0 の初期セットアップ、Core CLI実装、およびPostgreSQL環境構築が完了しました。

## 実施した変更
### 1. プロジェクト構成
- `Cargo.toml`: 依存関係 (`sqlx`, `tera` 等) を追加。
- `schema.sql`: データベーススキーマ定義。

### 2. Core CLI & Templates
- `src/`: モデル、DB接続、テンプレート処理、CLIロジックを実装。
- `templates/`: JavaコードおよびJSONアセット用のテンプレートを作成。

### 3. PostgreSQL環境 (Docker Compose)
- `docker-compose.yml`: PostgreSQL 16コンテナ定義。 (Port: 5435)
- `.env`: DB接続情報 (`DATABASE_URL`)。
- **データ初期化**: `schema.sql` 適用とテストデータの投入を実施済み。

## 検証結果
以下の手順で動作を検証しました。

1. **DB起動**: Dockerコンテナ (`logicchisel_db`) がポート **5435** で正常に起動。
2. **データ確認**: テストデータ (`test_sword`, `test_block`) がDBに存在することを確認。
3. **コード生成**: `cargo run -- generate` が以下のファイルを生成することを確認。
    - `generated/java/ModItems.java`
    - `generated/java/ModBlocks.java`
    - `generated/assets/models/item/test_sword.json`

## 今後の操作
コンテナは起動したままですので、開発を継続できます。

### コンテナ操作
```bash
docker compose up -d  # 起動
docker compose down   # 停止
docker compose exec db psql -U user -d logicchisel # SQL実行
```

### コード生成
```bash
cargo run -- generate
```

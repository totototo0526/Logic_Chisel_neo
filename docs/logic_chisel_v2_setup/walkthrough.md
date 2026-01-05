# LogicChisel v2.0 Setup Walkthrough

LogicChisel v2.0 の初期セットアップ、Core CLI実装、PostgreSQL環境構築、およびMDK統合が完了しました。

## 実施した変更
### 1. プロジェクト構成
- `Cargo.toml`: 依存関係 (`sqlx`, `tera` 等) を追加。
- `schema.sql`: データベーススキーマ定義。

### 2. Core CLI & Templates
- `src/`: モデル、DB接続、テンプレート処理、CLIロジックを実装。
- `templates/`: JavaコードおよびJSONアセット用のテンプレートを作成。
- **MDK統合**: パッケージ名 (`package_name`) を動的に変更できるようにし、出力先をMDKのソースディレクトリに変更しました。

### 3. PostgreSQL環境 (Docker Compose)
- `docker-compose.yml`: PostgreSQL 16コンテナ定義。 (Port: 5435)
- `.env`: DB接続情報 (`DATABASE_URL`)。

## 検証結果 (MDK統合)
`cargo run -- generate` を実行すると、以下のパスにファイルが生成されることを確認しました。

1. **Items**: `logicchiselmod-template-1.21.1/src/main/java/com/example/totototo/ModItems.java`
2. **Blocks**: `logicchiselmod-template-1.21.1/src/main/java/com/example/totototo/ModBlocks.java`
3. **Assets**: `logicchiselmod-template-1.21.1/src/main/resources/assets/logicchisel/models/item/test_sword.json`

これにより、RustツールからMDKへのシームレスなコード注入が可能になりました。

## 今後の操作
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

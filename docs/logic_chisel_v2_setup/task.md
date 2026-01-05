# LogicChisel v2.0 タスクリスト

- [x] プロジェクトの初期化と構成
    - [x] `logic-chisel` プロジェクトの確認と設定
    - [x] `Cargo.toml` の更新
- [x] データベース初期化
    - [x] `schema.sql` の作成
- [x] CLIアプリケーション実装 (Core)
    - [x] `src/main.rs`, `src/db.rs`, `src/models.rs`
- [x] テンプレートエンジン統合
    - [x] `src/templates.rs`, templates files
- [x] PostgreSQLセットアップ
    - [x] `docker-compose.yml`, `.env` setup
- [x] MDK統合 (Integration with MDK)
    - [x] テンプレート修正: パッケージ名を `{{ package_name }}` に変更
    - [x] `src/templates.rs`: `package_name` の注入
    - [x] `src/main.rs`: 出力パスの変更 (Java & Assets)

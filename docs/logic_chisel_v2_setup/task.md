# LogicChisel v2.0 タスクリスト

- [x] プロジェクトの初期化と構成
    - [x] `logic-chisel` プロジェクトの確認と設定
    - [x] `Cargo.toml` の更新
- [x] データベース初期化
    - [x] `schema.sql` の作成
- [x] CLIアプリケーション実装 (Core)
    - [x] `src/models.rs`: データ構造定義
    - [x] `src/db.rs`: DB接続とクエリ実装
    - [x] `src/main.rs`: CLIコマンド処理
- [x] テンプレートエンジン統合
    - [x] `Cargo.toml`: Tera依存関係追加
    - [x] `src/templates.rs`: Tera設定
    - [x] `templates/ModItems.java.tera`: アイテム登録用
    - [x] `templates/ModBlocks.java.tera`: ブロック登録用
    - [x] `templates/item_model.json.tera`: JSONアセット用
    - [x] `src/main.rs`: 複数ファイル生成対応

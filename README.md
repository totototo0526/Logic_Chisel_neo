# LogicChisel (v2.0)

**"Crafting Mods, crafting memories."**

LogicChisel（ロジック・チゼル）は、Minecraft (NeoForge 1.21+) のMOD開発を、Javaコードを書くことなく、直感的なGUI操作だけで行えるようにする**MOD生成フレームワーク**です。

## 🛡️ プロジェクト概要 (Mission)

このプロジェクトは、単なる便利ツールの開発にとどまらず、以下の2つの「真の目的」を持っています。

1.  **家族の創造性を解放する (Family Creativity)**
    * **Logic (息子)**: 複雑なパラメータ調整や、イベントロジック（ETAモデル）のパズルを解く楽しさを提供する。
    * **Design (娘)**: テクスチャやお絵描きを通じて、自分のデザインがゲームに反映される喜びを提供する。
    * これらを統合し、家族で一つの世界を作り上げる体験を創出します。

2.  **技術的探求 (Tech Exploration)**
    * **Architect (父)**: Rust言語とゲーム開発技術（ECS, Immediate Mode GUI）の習得、および堅牢なバックエンドシステムの構築。

## 🏗️ アーキテクチャ (Architecture)

LogicChiselは、Rustのパフォーマンスと型安全性を活かした「Core」と、ユーザーフレンドリーな「UI」の2層構造で構成されています。

### 1. The Chisel (Core Engine)
* **役割**: データの整合性チェック、およびJavaソースコードの生成。
* **技術**: Rust, `sqlx` (DB通信), `Tera` (テンプレートエンジン)
* **特徴**: MODの仕様データは、ローカルファイルではなく **VPS上のPostgreSQL** で一元管理されます。これにより、データの競合を防ぎ、家族間のコラボレーションを実現します。

### 2. The Handle (UI / UX)
* **役割**: ユーザーからの入力を受け付けるフロントエンド。
* **技術**: `egui` (Immediate Mode GUI)
* **Editor**:
    * **Logic Editor**: 数値設定やノードベースのロジック構築画面。
    * **Design Tool**: ドット絵作成・プレビュー機能。

## 🛠️ 技術スタック (Tech Stack)

* **Language**: Rust (Edition 2021)
* **GUI Framework**: `egui`
* **Database**: PostgreSQL 16+ (Running on VPS/Docker)
* **DB Client**: `sqlx` (Compile-time checked queries)
* **Template Engine**: `tera`
* **Infrastructure**: Docker Compose, Nginx, Certbot

## 🗺️ ロードマップ (Roadmap)

### Phase 0: Environment (Current) 🚧
* Rust開発環境の構築 (`rust-analyzer`)
* VPS/DBインフラの整備
* GUIのHello World (`egui`)

### Phase 1: Basic Items ("The Origin")
* 「最強の剣」と「基本のブロック」の生成パイプライン確立。
* DBスキーマ設計 (`Generic Object Pattern`)。
* Javaコード生成エンジンの実装。

### Phase 2: Design Tools
* `egui::Painter` を活用した簡易テクスチャ作成ツールの実装。
* デザイン・フィードバック・ループ。

### Phase 3: Logic System (ETA)
* イベント(Event)・トリガー(Trigger)・アクション(Action) モデルの実装。
* 高度なゲームロジックの定義機能。

## 🚀 開発の始め方 (Getting Started)

### 前提条件
* Rust (Latest Stable)
* Docker & Docker Compose

### セットアップ
```bash
# 1. リポジトリのクローン
git clone [https://github.com/totototo0526/logic-chisel.git](https://github.com/totototo0526/logic-chisel.git)
cd logic-chisel

# 2. 環境変数の設定 (.envファイルを作成)
echo "DATABASE_URL=postgres://user:password@localhost/logicchisel" > .env

# 3. 実行
cargo run

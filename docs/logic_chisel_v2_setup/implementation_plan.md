# LogicChisel v2.0 実装計画 (Template Engine)

## 目標
`Tera` テンプレートエンジンを組み込み、DBから取得したデータを元にJavaソースコードを生成する機能を実装する。

## 変更内容
### 設定ファイル
#### [MODIFY] [Cargo.toml](file:///home/kitten/Logic_Chisel_neo/Cargo.toml)
- `tera = "1"` を追加

### テンプレート処理
#### [NEW] [src/templates.rs](file:///home/kitten/Logic_Chisel_neo/src/templates.rs)
- `init_tera() -> Result<Tera>`: テンプレートエンジンの初期化
- `render_items(objects: &[GameObject]) -> Result<String>`: ModItems.java の生成

### テンプレートファイル
#### [NEW] [templates/ModItems.java.tera](file:///home/kitten/Logic_Chisel_neo/templates/ModItems.java.tera)
- ModItems.java のテンプレート定義

### CLI連携
#### [MODIFY] [src/main.rs](file:///home/kitten/Logic_Chisel_neo/src/main.rs)
- `generate` コマンド内でテンプレートレンダリングを呼び出し、ファイルを出力する処理を追加。

## 検証計画
### 自動テスト
- `cargo check`

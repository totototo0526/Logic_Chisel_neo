# MDK Integration Plan

## 目標
生成されたModコードを、リポジトリ内のMDK (`logicchiselmod-template-1.21.1`) に直接統合する。

## 変更内容
### テンプレート
#### [MODIFY] [templates/ModItems.java.tera](file:///home/kitten/Logic_Chisel_neo/templates/ModItems.java.tera)
- `package {{ package_name }};` に変更

#### [MODIFY] [templates/ModBlocks.java.tera](file:///home/kitten/Logic_Chisel_neo/templates/ModBlocks.java.tera)
- `package {{ package_name }};` に変更

### Rustコード
#### [MODIFY] [src/templates.rs](file:///home/kitten/Logic_Chisel_neo/src/templates.rs)
- `render_items` と `render_blocks` 関数で `package_name` をコンテキストに追加

#### [MODIFY] [src/main.rs](file:///home/kitten/Logic_Chisel_neo/src/main.rs)
- 定数定義:
    - `PACKAGE_NAME`: `com.example.totototo`
    - `MDK_ROOT`: `logicchiselmod-template-1.21.1`
- 出力ロジックの変更:
    - Java: `MDK_ROOT/src/main/java/com/example/totototo/`
    - Assets: `MDK_ROOT/src/main/resources/assets/logicchisel/models/item/`

## 検証計画
### 手動検証
- `cargo run -- generate` を実行
- 出力されたファイルがMDKのフォルダ内に正しいパスで存在することを確認

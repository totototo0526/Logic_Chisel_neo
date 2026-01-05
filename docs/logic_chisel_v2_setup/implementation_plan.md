# PostgreSQL Setup Plan

## 目標
Docker Composeを使用してPostgreSQL環境を構築し、LogicChiselが即座に利用できる状態にする。

## 変更内容
### インフラ構成
#### [NEW] [docker-compose.yml](file:///home/kitten/Logic_Chisel_neo/docker-compose.yml)
- PostgreSQL 16
- データベース名: `logicchisel`
- ユーザー/パスワード: `user`/`password`
- ポート: 5432 (ホスト)

### 環境変数
#### [NEW] [.env](file:///home/kitten/Logic_Chisel_neo/.env)
- `DATABASE_URL` の設定

### 初期化スクリプト
- コンテナ起動後、`schema.sql` を適用
- テスト用データ (剣、ブロック) を挿入

## 検証計画
### 手動検証
- `docker compose up -d` で起動確認
- `cargo run -- generate` が成功し、ファイルが生成されることを確認

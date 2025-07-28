package io.github.totototo0526.logicchisel.codegen;

import io.github.totototo0526.logicchisel.codegen.config.Config;
import io.github.totototo0526.logicchisel.codegen.config.ConfigLoader;

public class Main {
    public static void main(String[] args) {
        System.out.println("LogicChisel Code Generator -- Booting up...");

        // タスク1: config.yml の読み込み処理
        Config config = ConfigLoader.loadConfig();

        // 読み込んだ設定内容をコンソールに出力して確認
        System.out.println("Loaded Configuration: " + config);

        // ■次のタスク
        // 2. ロギング機構の実装
        // 3. DslCodeGenerator のインスタンス化と実行
    }
}

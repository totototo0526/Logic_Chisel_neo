package io.github.totototo0526.logicchisel.codegen;

import io.github.totototo0526.logicchisel.codegen.config.Config;
import io.github.totototo0526.logicchisel.codegen.config.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("LogicChisel Code Generator -- Booting up...");

        Config config = ConfigLoader.loadConfig();
        LOGGER.info("Loaded Configuration: {}", config);

        // ■次のタスク
        // 3. DslCodeGenerator のインスタンス化と実行
    }
}

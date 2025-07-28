package io.github.totototo0526.logicchisel.codegen;

import io.github.totototo0526.logicchisel.codegen.config.Config;
import io.github.totototo0526.logicchisel.codegen.config.ConfigLoader;
import io.github.totototo0526.logicchisel.codegen.dsl.DslCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("LogicChisel Code Generator -- Booting up...");

        Config config = ConfigLoader.loadConfig();
        LOGGER.info("Loaded Configuration: {}", config);

        // ジェネレーターを生成して実行
        DslCodeGenerator generator = new DslCodeGenerator(config);
        generator.execute();

        LOGGER.info("Process finished.");
    }
}

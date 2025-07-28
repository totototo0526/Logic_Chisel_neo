package io.github.totototo0526.logicchisel.codegen.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ConfigLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigLoader.class);
    private static final String CONFIG_FILE_NAME = "config.yml";

    public static Config loadConfig() {
        Yaml yaml = new Yaml();
        try (InputStream in = new FileInputStream(CONFIG_FILE_NAME)) {
            LOGGER.info("Loading configuration from {}", CONFIG_FILE_NAME);
            return yaml.loadAs(in, Config.class);
        } catch (FileNotFoundException e) {
            LOGGER.warn("{} not found. Using default configuration.", CONFIG_FILE_NAME);
            return new Config();
        } catch (Exception e) {
            LOGGER.error("Failed to load {}. Using default configuration.", CONFIG_FILE_NAME, e);
            return new Config();
        }
    }
}

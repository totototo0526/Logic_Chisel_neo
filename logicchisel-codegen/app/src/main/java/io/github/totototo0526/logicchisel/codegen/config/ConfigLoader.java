package io.github.totototo0526.logicchisel.codegen.config;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ConfigLoader {

    private static final String CONFIG_FILE_NAME = "config.yml";

    public static Config loadConfig() {
        Yaml yaml = new Yaml();
        try (InputStream in = new FileInputStream(CONFIG_FILE_NAME)) {
            // config.yml が存在すれば、その内容を読み込んでConfigオブジェクトを生成
            System.out.println("INFO: Loading configuration from " + CONFIG_FILE_NAME);
            return yaml.loadAs(in, Config.class);
        } catch (FileNotFoundException e) {
            // config.yml が見つからない場合は、デフォルト値を持つConfigオブジェクトを返す
            System.out.println("INFO: " + CONFIG_FILE_NAME + " not found. Using default configuration.");
            return new Config();
        } catch (Exception e) {
            // その他のエラー（例：YAMLの書式が不正）
            System.err.println("ERROR: Failed to load " + CONFIG_FILE_NAME + ". Using default configuration.");
            e.printStackTrace();
            return new Config();
        }
    }
}

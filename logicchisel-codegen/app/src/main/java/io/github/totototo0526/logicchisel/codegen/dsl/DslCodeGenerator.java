package io.github.totototo0526.logicchisel.codegen.dsl;

import io.github.totototo0526.logicchisel.codegen.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DslCodeGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DslCodeGenerator.class);
    private final Config config;
    private final Yaml yaml;

    public DslCodeGenerator(Config config) {
        this.config = config;
        this.yaml = new Yaml();
    }

    public void execute() {
        LOGGER.info("DSLコード生成を開始します...");
        Path outputDir = Paths.get(config.getOutputDirectory());
        
        try {
            // 出力ディレクトリが存在しない場合に作成
            Files.createDirectories(outputDir);
            LOGGER.info("出力ディレクトリを準備しました: {}", outputDir);
        } catch (IOException e) {
            LOGGER.error("出力ディレクトリの作成に失敗しました: {}", outputDir, e);
            return;
        }

        List<Path> dslFiles = findDslFiles();
        if (dslFiles.isEmpty()) {
            LOGGER.warn(".lcmd ファイルが入力ディレクトリに見つかりません。生成する対象がありません。");
        } else {
            LOGGER.info("{}個の .lcmd ファイルを処理します。", dslFiles.size());
            for (Path dslFile : dslFiles) {
                try (InputStream in = new FileInputStream(dslFile.toFile())) {
                    ItemDefinition itemDef = yaml.loadAs(in, ItemDefinition.class);
                    LOGGER.info("  -> 解析成功: id='{}'", itemDef.getId());

                    String className = "ItemDef_" + itemDef.getId();
                    String javaCode = generateJavaCode(itemDef, className);
                    
                    saveCodeToFile(outputDir, className, javaCode);

                } catch (Exception e) {
                    LOGGER.error("  -> 解析失敗: {}", dslFile.getFileName(), e);
                }
            }
        }
        
        LOGGER.info("DSLコード生成が完了しました。");
    }

    private String generateJavaCode(ItemDefinition itemDef, String className) {
        // Javaクラスの単純なテンプレート
        return String.format(
            "package generated.items;\n\n" +
            "public class %s {\n" +
            "    public static final String ID = \"%s\";\n" +
            "    public static final String NAME = \"%s\";\n" +
            "}\n",
            className, itemDef.getId(), itemDef.getName()
        );
    }
    
    // === ここから追加 ===
    private void saveCodeToFile(Path outputDir, String className, String javaCode) {
        // 現時点では、パッケージは常に "generated.items" と仮定
        Path packageDir = outputDir.resolve("generated/items");
        try {
            Files.createDirectories(packageDir);
            
            Path outputFile = packageDir.resolve(className + ".java");
            
            try (PrintWriter writer = new PrintWriter(outputFile.toFile(), "UTF-8")) {
                writer.print(javaCode);
            }
            
            LOGGER.info("    -> Javaコードをファイルに保存しました: {}", outputFile);

        } catch (IOException e) {
            LOGGER.error("    -> ファイルへのJavaコードの保存に失敗しました: {}", className, e);
        }
    }
    // === ここまで追加 ===

    private List<Path> findDslFiles() {
        Path inputPath = Paths.get(config.getInputDirectory());
        if (!Files.isDirectory(inputPath)) {
            LOGGER.warn("入力ディレクトリ '{}' が存在しません。", inputPath);
            return Collections.emptyList();
        }

        try (Stream<Path> stream = Files.walk(inputPath)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".lcmd"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("入力ディレクトリからのファイルの読み込みに失敗しました: {}", inputPath, e);
            return Collections.emptyList();
        }
    }
}

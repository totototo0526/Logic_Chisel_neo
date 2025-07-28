package io.github.totototo0526.logicchisel.codegen.config;

public class Config {
    private String inputDirectory = "src/main/resources/definitions"; // デフォルト値
    private String outputDirectory = "build/generated-src"; // デフォルト値

    // SnakeYAMLがマッピングするために、getter/setterが必要
    public String getInputDirectory() {
        return inputDirectory;
    }

    public void setInputDirectory(String inputDirectory) {
        this.inputDirectory = inputDirectory;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @Override
    public String toString() {
        return "Config{" +
                "inputDirectory='" + inputDirectory + '\'' +
                ", outputDirectory='" + outputDirectory + '\'' +
                '}';
    }
}

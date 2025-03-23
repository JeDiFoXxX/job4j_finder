package ru.job4j;

import java.io.IOException;
import java.nio.file.Files;

public class FileSearch {
    public static void main(String[] args) throws IOException {
        Validate.validate(args);
        Setting setting = new Setting(args);
        FileSearchVisitor visitor = new FileSearchVisitor();
        visitor.writePattern(setting);
        Files.walkFileTree(setting.getDirectory(), visitor);
    }
}

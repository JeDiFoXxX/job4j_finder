package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileSearchVisitor extends SimpleFileVisitor<Path> {
    private static final Logger LOG = LoggerFactory.getLogger(FileSearch.class.getName());
    private String pattern;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.getFileName().toString().matches(pattern)) {
            LOG.info("Match found: {}", file.getFileName());
        }
        return CONTINUE;
    }

    public void writePattern(Setting setting) {
        this.pattern = choosePattern(setting);
    }

    private String choosePattern(Setting setting) {
        return switch (setting.getType()) {
            case "mask" -> "\\S*\\.".concat(setting.getPattern().substring(2));
            default -> setting.getPattern();
        };
    }
}

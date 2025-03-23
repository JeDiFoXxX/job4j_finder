package ru.job4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Validate {
    public static void validate(String[] args) {
        validateInputArgs(args);
    }

    private static void validateInputArgs(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String string : args) {
            builder.append(string);
        }
        if (args.length != 4 || !builder.toString().matches("-d=\\S*-n=\\S*-t=\\S*-o=\\S*")) {
            throw new IllegalArgumentException(
                    "The required parameters [-d, -n, -t, -o] are missing or malformed.");
        }
        Path path = Path.of(args[0].substring(3));
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(
                    String.format("The provided path '%s' not a directory.", path)
            );
        }
        if (!args[3].endsWith(".txt")) {
            throw new IllegalArgumentException(
                    String.format("The file %s must have a .txt extension", args[3].substring(3)));
        }
        String t = args[2].substring(3);
        String n = args[1].substring(3);
        switch (t) {
            case "mask" -> {
                if (!n.matches("\\*\\.\\S*")) {
                    throw new IllegalArgumentException(String.format("The pattern '%s' is invalid for mask", n));
                }
            }
            case "name" -> {
                if (!n.matches("\\S*\\.\\S*") || n.contains("*")) {
                    throw new IllegalArgumentException(String.format("The pattern '%s' is invalid for name", n));
                }
            }
            case "regex" -> {
                try {
                    Pattern.compile(n);
                } catch (PatternSyntaxException e) {
                    throw new IllegalArgumentException(String.format("The pattern '%s' is invalid for regex", n));
                }
            }
            default -> throw new IllegalArgumentException("Invalid t. Expected 'mask', 'name', or 'regex'.");
        }
    }
}

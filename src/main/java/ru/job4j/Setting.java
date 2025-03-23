package ru.job4j;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Setting {
    private final Map<String, String> map;

    public Setting(String[] args) {
        this.map = Arrays.stream(args)
                .map(arg -> arg.substring(1).split("="))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> parts[1]
                ));
        System.setProperty("logfile", map.get("o"));
    }

    public Path getDirectory() {
        return Path.of(map.get("d"));
    }

    public String getPattern() {
        return map.get("n");
    }

    public String getType() {
        return map.get("t");
    }

    public String getOutputFile() {
        return map.get("o");
    }
}

package com.xiaoyue.celestial_core.content.loot;

import dev.xkmc.l2core.serial.configval.AbstractConfigParser;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Optional;

public record IntConfigValue(String path, List<String> line) {

    public static IntConfigValue of(String file, ModConfigSpec.ConfigValue<Integer> config) {
        return new IntConfigValue(file, config.getPath());
    }

    public static IntConfigValue of(String data) {
        if (!data.contains("/")) return new IntConfigValue(data, List.of());
        int last = data.lastIndexOf('/');
        var line = data.substring(last + 1).split("\\.");
        return new IntConfigValue(data.substring(0, last), List.of(line));
    }

    public int get() {
        Optional<Object> parsed = AbstractConfigParser.parse(path, line);
        if (parsed.isPresent()) {
            return parsed.map(e -> e instanceof Number val ? val.intValue() : 0).orElse(0);
        }
        return Integer.parseInt(path);
    }

    public String toData() {
        StringBuilder lines = new StringBuilder();
        for (var e : line) {
            if (!lines.isEmpty()) {
                lines.append(".");
            }
            lines.append(e);
        }
        return path + "/" + lines;
    }

}

package com.xiaoyue.celestial_core.content.loot;

import dev.xkmc.l2core.serial.configval.AbstractConfigParser;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Optional;

public record DoubleConfigValue(String path, List<String> line) {

    public static DoubleConfigValue of(String file, ModConfigSpec.ConfigValue<Double> config) {
        return new DoubleConfigValue(file, config.getPath());
    }

    public static DoubleConfigValue of(String data) {
        if (!data.contains("/")) return new DoubleConfigValue(data, List.of());
        int last = data.lastIndexOf('/');
        var line = data.substring(last + 1).split("\\.");
        return new DoubleConfigValue(data.substring(0, last), List.of(line));
    }

    public double get() {
        Optional<Object> parsed = AbstractConfigParser.parse(path, line);
        if (parsed.isPresent()) {
            return parsed.map(e -> e instanceof Number val ? val.doubleValue() : 0d).orElse(0d);
        }
        return Double.parseDouble(path);
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

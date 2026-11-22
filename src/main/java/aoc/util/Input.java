package aoc.util;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class Input {
    private Input() {}

    // Reads inputs/<year>/day<DD>.txt, relative to the project root.
    public static List<String> lines(int year, int day) {
        Path path = Path.of("inputs", String.valueOf(year), String.format("day%02d.txt", day));
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new UncheckedIOException("Missing input file: " + path.toAbsolutePath(), e);
        }
    }
}
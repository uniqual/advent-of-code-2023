package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class InputReader {

    private InputReader() {
    }

    public static List<String> getAllFileLines(String filename) {
        try {
            String filePath = String.format("inputs/%s", filename);
            Path path = Paths.get(filePath);
            return Files.readAllLines(path).stream().toList();
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}

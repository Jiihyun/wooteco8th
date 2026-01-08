package pairmatching.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import pairmatching.exception.ExceptionMessage;

public final class FileReader {

    private FileReader() {
    }

    private static final String BACKEND_PATH = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_PATH = "src/main/resources/frontend-crew.md";

    public static List<String> readBackend() {
        return readLines(BACKEND_PATH);
    }

    public static List<String> readFrontend() {
        return readLines(FRONTEND_PATH);
    }

    private static List<String> readLines(String path) {
        try {
            return Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        } catch (IOException ioException) {
            throw new IllegalStateException(ExceptionMessage.CANNOT_READ_FILE.getMessage(), ioException);
        }
    }
}

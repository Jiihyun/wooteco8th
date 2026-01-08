package attendance.util;

import attendance.exception.ExceptionMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class FileReader {

    private FileReader() {
    }

    private static final String ATTENDANCES_PATH = "src/main/resources/attendances.csv";

    public static List<String> readAttendances() {
        return readLines(ATTENDANCES_PATH);
    }

    private static List<String> readLines(String path) {
        try {
            List<String> allLines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
            allLines.removeFirst();
            return allLines;
        } catch (IOException ioException) {
            throw new IllegalStateException(ExceptionMessage.CANNOT_READ_FILE.getMessage(), ioException);
        }
    }
}

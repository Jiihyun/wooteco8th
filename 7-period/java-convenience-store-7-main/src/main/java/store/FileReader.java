package store;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader {

    private FileReader() {
    }

    private static final String PRODUCTS_PATH = "src/main/resources/products.md";
    private static final String PROMOTIONS_PATH = "src/main/resources/promotions.md";

    public static List<String> readProducts() {
        return readLines(PRODUCTS_PATH);
    }

    public static List<String> readPromotions() {
        return readLines(PROMOTIONS_PATH);
    }

    private static List<String> readLines(String path) {
        try {
            List<String> allLines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
            allLines.removeFirst();
            return allLines;
        } catch (IOException ioException) {
            throw new RuntimeException("파일을 읽는 중 문제가 발생했습니다.", ioException);
        }
    }
}

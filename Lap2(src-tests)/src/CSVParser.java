import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public static List<String> parseCSVFromFile(String filePath) throws IOException {
        List<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    strings.add(part.trim());
                }
            }
        }
        return strings;
    }
}

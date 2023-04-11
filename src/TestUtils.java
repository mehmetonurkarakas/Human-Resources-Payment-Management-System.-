import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TestUtils {
    public static double testOutputFile(String outputFilepath, String expectedOutputFilepath) {
        try {
            List<String> outputLines = Files.readAllLines(Paths.get(outputFilepath)).stream().map(String::trim).filter(line -> !line.isEmpty()).collect(Collectors.toList());
            List<String> expectedOutputLines = Files.readAllLines(Paths.get(expectedOutputFilepath)).stream().map(String::trim).filter(line -> !line.isEmpty()).collect(Collectors.toList());

            int correct = 0;
            for(int i = 0; i < outputLines.size(); i++) {
                try {
                    if(outputLines.get(i).equals(expectedOutputLines.get(i)))
                        correct++;
                    else
                        System.out.printf("Output:\n%s\nExpected:\n%s\n", outputLines.get(i), expectedOutputLines.get(i));
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }

            return correct / (double) expectedOutputLines.size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

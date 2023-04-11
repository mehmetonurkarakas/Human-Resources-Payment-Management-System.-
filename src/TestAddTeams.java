import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class TestAddTeams {
    final static String INPUT_FILE = "io/staff.txt";
    final static String OUTPUT_FILE = "io/output_add_teams_test.txt";
    final static String EXPECTED_OUTPUT_FILE = "io/expected_output_add_teams_test.txt";

    public static void main(String[] args) {
        // DON'T CHANGE LINES BELOW, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
        PrintStream console = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Main.main(new String[]{INPUT_FILE});

        System.setOut(new PrintStream(baos));
        executeTestCommands();

        try {
            Files.write(Paths.get(OUTPUT_FILE), Arrays.asList(baos.toString().trim().split("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.setOut(console);
        System.out.println(TestUtils.testOutputFile(OUTPUT_FILE, EXPECTED_OUTPUT_FILE));
    }

    static void executeTestCommands() {
        try {
            Main.company.addNewTeam("Team 1", "Muhammet Basturk", new HashSet<>(Arrays.asList("Enis Babaoglu", "Senay Yuce", "Yesim Ozbey", "Yasemin Kilicli", "Sadi Oncel")));
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }

        try {
            Main.company.addNewTeam("Team 2", "Senay Yuce", new HashSet<>(Arrays.asList("Enis Babaoglu", "Senay Yuce", "Yesim Ozbey", "Yasemin Kilicli", "Sadi Oncel")));
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            Main.company.addNewTeam("Team 3", "Suna Veli", new HashSet<>(Arrays.asList("Enis Babaoglu", "Enis Babaoglu", "Enis Babaoglu", "Yesim Ozbey", "Yasemin Kilicli", "Sadi Oncel")));
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            Main.company.addNewTeam("Team 4", "Suna Veli", new HashSet<>(Arrays.asList("Enis Babaoglu", "Enis Babaoglu", "Burak Erdogan", "Yesim Ozbey", "Yasemin Kilicli", "Sadi Oncel")));
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            Main.company.addNewTeam("Team 5", "Sahin Gunaydin", new HashSet<>(Arrays.asList("Hilal Boran", "Aylin Boran", "Hasan Arslan", "Enis Babaoglu", "Yasemin Kilicli", "Cemile Altug")));
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            Main.company.addNewTeam("Team 6", "Sahin Gunaydin", new HashSet<>(Arrays.asList("Hilal Boran", "Aylin Boran", "Enis Babaoglu", "Yasemin Kilicli", "Cemile Altug")));
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        for(int i = 1; i < 7; i++) {
            try {
                Team team = Main.company.getTeamByName("Team " + i);
                System.out.println(team.toString());
            } catch (InvalidCommandException e) {
                System.out.println("InvalidCommandException! Message= " + e.getMessage());
            }
        }

    }
}

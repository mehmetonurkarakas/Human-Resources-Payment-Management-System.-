import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class TestMembers {
    final static String INPUT_FILE = "io/staff.txt";
    final static String OUTPUT_FILE = "io/output_members_test.txt";
    final static String EXPECTED_OUTPUT_FILE = "io/expected_output_members_test.txt";

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
            StaffMember volunteer = new Volunteer("Burak Erdogan", "Mamak 349. Sokak", "5512345123");
            Main.company.addStaffMember(volunteer);
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            StaffMember manager = new Manager("Mehtap Kubat", "Mamak 349. Sokak", "5543345123", "28954651050", 14119.57);
            Main.company.addStaffMember(manager);
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            StaffMember manager = new Manager("Ali Aydost", "Mamak 349. Sokak", "5512345123", "28879071050", 17119.57);
            Main.company.addStaffMember(manager);
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            StaffMember employee = new Employee("Merve Kizil", "Sincan 32. Sokak", "5557453423", "46541442135", 7013.57);
            Main.company.addStaffMember(employee);
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            StaffMember hourlyWorker = new HourlyWorker("Aylin Boran", "282 Florence Street", "5362443521", "60113324635", 33.11);
            Main.company.addStaffMember(hourlyWorker);
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            StaffMember hourlyWorker = new HourlyWorker("Ayse Ozyurek", "282 Florence Street", "5365743521", "60965257635", 40.11);
            Main.company.addStaffMember(hourlyWorker);
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }


        try {
            Main.company.removeStaffMember("Ali Eren");
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            Main.company.removeStaffMember("Merve Kizilcahamam");
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }


        try {
            Main.company.removeStaffMember("Ayse Ozyurek");
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }


        Main.company.printStaffMembers();
    }
}

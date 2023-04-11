import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class TestStatistics {
    final static String INPUT_FILE = "io/staff.txt";
    final static String OUTPUT_FILE = "io/output_statistics_test.txt";
    final static String EXPECTED_OUTPUT_FILE = "io/expected_output_statistics_test.txt";

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
        topHourlyWorkersTest();
        topPaidEmployeesTest();
    }

    static void topPaidEmployeesTest() {
        try {
            TreeMap<Employee, Double> topPaidEmployeeMap = Main.company.getTopPaidEmployees(-1);
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            TreeMap<Employee, Double> topPaidEmployeeMap = Main.company.getTopPaidEmployees(500);
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            TreeMap<Employee, Double> topPaidEmployeeMap = Main.company.getTopPaidEmployees(10);
            for(Employee employee : topPaidEmployeeMap.keySet()) {
                Double payment = topPaidEmployeeMap.get(employee);
                System.out.println(String.format("%s => %.2f", employee.getName(), payment));
            }
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }
    }

    static void topHourlyWorkersTest() {
        HourlyWorker hourlyWorker1 = (HourlyWorker) Main.company.getStaffMemberMap().get("Aylin Ozmen");
        hourlyWorker1.addHours(50);
        HourlyWorker hourlyWorker2 = (HourlyWorker) Main.company.getStaffMemberMap().get("Metin Abasiyanik");
        hourlyWorker2.addHours(90);
        HourlyWorker hourlyWorker3 = (HourlyWorker) Main.company.getStaffMemberMap().get("Cemile Altug");
        hourlyWorker3.addHours(100);
        HourlyWorker hourlyWorker4 = (HourlyWorker) Main.company.getStaffMemberMap().get("Metin Ince");
        hourlyWorker4.addHours(60);
        HourlyWorker hourlyWorker5 = (HourlyWorker) Main.company.getStaffMemberMap().get("Ecrin Batuk");
        hourlyWorker5.addHours(70);
        HourlyWorker hourlyWorker6 = (HourlyWorker) Main.company.getStaffMemberMap().get("Muhammet Ozmen");
        hourlyWorker6.addHours(55);

        try {
            TreeSet<HourlyWorker> topHourlyWorkersMap = Main.company.getTopWorkedHourlyWorkers(-1);
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            TreeSet<HourlyWorker> topHourlyWorkersMap = Main.company.getTopWorkedHourlyWorkers(100);
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }

        try {
            TreeSet<HourlyWorker> topHourlyWorkersMap = Main.company.getTopWorkedHourlyWorkers(4);
            for(HourlyWorker worker : topHourlyWorkersMap) {
                System.out.println(String.format("%s => worked %d hours", worker.getName(), worker.getHoursWorked()));
            }
        } catch (InvalidCommandException e) {
            System.out.println("InvalidCommandException! Message= " + e.getMessage());
        }
    }
}

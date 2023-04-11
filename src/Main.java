import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Main
{
    public static Company company;

    public static void main(String[] args) {
        company = new Company();
        String inputPath = args[0];
        readInput(inputPath);
    }

    public static void readInput(String inputPath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(inputPath));
            for (String s : lines) {
                String[] line = s.split("\t");;
                if (Objects.equals(line[0], "Volunteer")){
                    Volunteer volunteer = new Volunteer(line[1],line[2],line[3]);
                    company.addStaffMember(volunteer);
                }
                else if (Objects.equals(line[0], "Standard")){
                    double d=Double.parseDouble(line[5]);
                    Employee employee = new Employee(line[1],line[2],line[3],line[4],d);
                    company.addStaffMember(employee);
                }
                else if (Objects.equals(line[0],"Manager")){
                    double d=Double.parseDouble(line[5]);
                    Manager manager = new Manager(line[1],line[2],line[3],line[4],d);
                    company.addStaffMember(manager);
                }
                else if (Objects.equals(line[0],"Hourly")){
                    double d=Double.parseDouble(line[5]);
                    HourlyWorker hourlyWorker = new HourlyWorker(line[1],line[2],line[3],line[4],d);
                    company.addStaffMember(hourlyWorker);
                }

            }
        } catch (IOException | InvalidCommandException e) {
            e.printStackTrace();
        }

        /* TODO: Take input path and read file, instantiate staff member objects. While you are
            instantiating objects such as Employee etc., each one should be added to the company
            by using its addStaffMember method. */
    }
}

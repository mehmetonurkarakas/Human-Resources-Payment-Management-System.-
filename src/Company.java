import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Company {
    /** This is a mapping which maps the names of teams with the team objects. */
    HashMap<String, Team> teamsMap;

    /** This is a mapping which maps the names of staff members with the staff member objects. */
    HashMap<String, StaffMember> staffMemberMap;

    public Company() {
        staffMemberMap = new HashMap<>();
        teamsMap = new HashMap<>();
    }
    public TreeSet<HourlyWorker> getTopWorkedHourlyWorkers(int count) throws InvalidCommandException {
        if (count<1||count>staffMemberMap.size()){
            throw new InvalidCommandException("Invalid count ("+count+") argument passed to getTopWorkedHourlyWorkers.");
        }
        Comparator<HourlyWorker> hourlyWorkerComparator = new Comparator<HourlyWorker>() {
            @Override
            public int compare(HourlyWorker worker1, HourlyWorker worker2) {
                return Integer.compare(worker2.getHoursWorked(), worker1.getHoursWorked());
            }
        };

        TreeSet<HourlyWorker> hourlyWorkers = new TreeSet<>(hourlyWorkerComparator);
        staffMemberMap.forEach((key,value)->{
            if(value instanceof HourlyWorker){
                hourlyWorkers.add((HourlyWorker) value);
            }
        });

        TreeSet <HourlyWorker> topWorkers = new TreeSet<>(hourlyWorkerComparator);

        for (int i = 0;i<count;i++){
            topWorkers.add(hourlyWorkers.first());
            hourlyWorkers.remove(hourlyWorkers.first());
        }

        return topWorkers;
    }

    public TreeMap<Employee, Double> getTopPaidEmployees(int count) throws InvalidCommandException {
        if (count<1||count>staffMemberMap.size()){
            throw new InvalidCommandException("Invalid count ("+count+") argument passed to getTopPaidEmployees.");
        }
        Comparator<Employee> employeeComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee emp1, Employee emp2) {
                return Double.compare(emp2.calculatePayment(), emp1.calculatePayment());
            }
        };
        TreeMap<Employee, Double> sortedWorkersMap = new TreeMap<>(employeeComparator);
        staffMemberMap.forEach((key,value)->{
            if(value instanceof Employee){
                sortedWorkersMap.put((Employee) value,value.calculatePayment());
            }
        });

        TreeMap<Employee,Double> topPaidWorkers = new TreeMap<>(employeeComparator);

        for (int i = 0;i<count;i++){
            topPaidWorkers.put(sortedWorkersMap.firstKey(),sortedWorkersMap.get(sortedWorkersMap.firstKey()));
            sortedWorkersMap.remove(sortedWorkersMap.firstKey());
        }
        return topPaidWorkers;
    }

    public void addStaffMember(StaffMember member) throws InvalidCommandException {
        if (staffMemberMap.containsKey(member.getName())){
            throw new InvalidCommandException("Staff member "+member.getName()+" already exists in company.");
        }
        else {
            staffMemberMap.put(member.getName(),member);
            System.out.printf("Staff member %s added to company.\n",member.getName());
        }
    }

    public void removeStaffMember(String name) throws InvalidCommandException {
        if (!staffMemberMap.containsKey(name)){
            throw new InvalidCommandException("Staff member "+name+" does not exist in company.");
        }
        else {
            staffMemberMap.remove(name);
            System.out.printf("Staff member %s removed from company.\n",name);
        }
    }

    public void addNewTeam(String teamName, String managerName, HashSet<String> teamMemberNames) throws InvalidCommandException {
        if (!staffMemberMap.containsKey(managerName)){
            throw new InvalidCommandException("There is no manager with name "+managerName+".");
        }

        for (String s:teamMemberNames) {
            if (!staffMemberMap.containsKey(s)){
                throw new InvalidCommandException("There is no employee with name "+s+".");
            }
        }


        TreeSet<Employee> teamMembers = new TreeSet<>();

        for (String s:staffMemberMap.keySet()) {
            for (String sss:teamMemberNames){
                if (Objects.equals(s, sss)&& staffMemberMap.get(s) instanceof Employee){
                    teamMembers.add((Employee) staffMemberMap.get(sss));
                }
                else if (Objects.equals(s, sss)&& staffMemberMap.get(s) instanceof Volunteer){
                    throw new InvalidCommandException("There is no employee with name "+s+".");
                }
            }
        }
        Manager manager = null;
        for (String s:staffMemberMap.keySet()) {
            if (Objects.equals(s, managerName)&&staffMemberMap.get(s) instanceof Manager){
                manager = (Manager) staffMemberMap.get(s);
            }
            else if (Objects.equals(s, managerName)&& !(staffMemberMap.get(s) instanceof Manager)){
                throw new InvalidCommandException("There is no manager with name "+managerName+".");
            }
        }
        Team team = new Team(teamName,manager,teamMembers);
        teamsMap.put(teamName,team);
        System.out.println("Team with name "+teamName+" added.");

    }

    public Team getTeamByName(String teamName) throws InvalidCommandException {

        if (!teamsMap.containsKey(teamName)){
            throw new InvalidCommandException("Team with name "+teamName+" not found.");
        }
        Team team = null;

        for (String s: teamsMap.keySet()) {
            if (Objects.equals(s, teamName)){
                team = teamsMap.get(s);
            }
        }
        return team;


    }

    public void printStaffMembers() {
        TreeMap<String, StaffMember> workerList = new TreeMap<>(staffMemberMap);
        workerList.forEach((key,value)->{
            if(value instanceof HourlyWorker){
                System.out.println(key+" => HourlyWorker");
            }
            else if(value instanceof Manager){
                System.out.println(key+" => Manager");
            }
            else if(value instanceof Employee){
                System.out.println(key+" => Employee");
            }
            else {
                System.out.println(key+ " => Volunteer");
            }

        });

    }

    public HashMap<String, Team> getTeamsMap() {
        return teamsMap;
    }

    public HashMap<String, StaffMember> getStaffMemberMap() {
        return staffMemberMap;
    }
}

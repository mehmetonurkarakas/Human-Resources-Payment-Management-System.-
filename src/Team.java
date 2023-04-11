import java.util.TreeSet;

public class Team
{
    TreeSet<Employee> members;
    Manager manager;
    String teamName;

    Team(String teamName,Manager manager,TreeSet<Employee> members){
        this.members = members;
        this.manager = manager;
        this.teamName = teamName;
    }

    public String toString(){

        String out = "Team: "+this.teamName+" - Manager: "+this.manager.name+" - Members: ";
        int k = 1;
        for (Employee emp:this.members) {
            if (k!=members.size()){
                out += emp.name+", ";
            }
            else {
                out += emp.name;
            }
            k++;
        }
        return out;
    }

}

abstract public class StaffMember
{
    String name;
    String address;
    String phone;

    StaffMember(String name,String address,String phone){
        this.name = name;
        this.address = name;
        this.phone = phone;
    }

    public abstract double calculatePayment();

    public String getName() {
        return this.name;
    }
}

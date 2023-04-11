public class Employee extends StaffMember implements Comparable
{
    String socialSecurityNumber;
    double payRate;

    public Employee(String name, String address, String phone, String socSecNumber, double payRate) {
        super(name, address, phone);
        this.socialSecurityNumber = socSecNumber;
        this.payRate = payRate;
    }

    @Override
    public double calculatePayment() {
        return payRate;
    }

    @Override
    public int compareTo(Object o) {
        Employee personToCompare = ((Employee) o);
        String otherPersonsName = personToCompare.getName();
        return this.name.compareTo(otherPersonsName);
    }
}

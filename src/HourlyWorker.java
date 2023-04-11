public class HourlyWorker extends Employee
{
    int totalHoursWorked;


    public HourlyWorker(String name, String address, String phone, String socSecNumber, double payRate) {
        super(name, address, phone, socSecNumber, payRate);
    }

    public void addHours(int hours) {
        this.totalHoursWorked += hours;
    }

    @Override
    public double calculatePayment() {
        return this.payRate*this.totalHoursWorked;
    }

    public int getHoursWorked() {
        return this.totalHoursWorked;
    }
}

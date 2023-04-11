public class Manager extends Employee
{
    double bonus;

    public Manager(String name, String address, String phone, String socSecNumber, double payRate) {
        super(name, address, phone,socSecNumber,payRate);
    }
    @Override
    public double calculatePayment() {
        return this.bonus+this.payRate;
    }

    public void awardBonus(double bonus){
        this.bonus += bonus;
    }
}

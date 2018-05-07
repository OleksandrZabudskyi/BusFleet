package ua.training.model.entity;

public class Admin extends Employee {
    private String passportNumber;
    private String passportRegistration;

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportRegistration() {
        return passportRegistration;
    }

    public void setPassportRegistration(String passportRegistration) {
        this.passportRegistration = passportRegistration;
    }

    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                "passportNumber='" + passportNumber + '\'' +
                ", passportRegistration='" + passportRegistration + '\'' +
                '}';
    }
}

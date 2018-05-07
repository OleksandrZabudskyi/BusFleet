package ua.training.model.entity;

public class Driver extends Employee {
    private String drivingLicenceNumber;
    private int expiryDate;
    private int drivingExperience;
    private boolean assigned;

    public String getDrivingLicenceNumber() {
        return drivingLicenceNumber;
    }

    public void setDrivingLicenceNumber(String drivingLicenceNumber) {
        this.drivingLicenceNumber = drivingLicenceNumber;
    }

    public int getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(int expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getDrivingExperience() {
        return drivingExperience;
    }

    public void setDrivingExperience(int drivingExperience) {
        this.drivingExperience = drivingExperience;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    @Override
    public String toString() {
        return "Driver{" +
                super.toString() +
                "drivingLicenceNumber='" + drivingLicenceNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", drivingExperience=" + drivingExperience +
                ", assigned=" + assigned +
                '}';
    }

    public static final class DriverBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String password;
        private ROLE role;
        private String drivingLicenceNumber;
        private int expiryDate;
        private int drivingExperience;
        private boolean assigned;

        public DriverBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public DriverBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public DriverBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public DriverBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public DriverBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public DriverBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public DriverBuilder setRole(ROLE role) {
            this.role = role;
            return this;
        }

        public DriverBuilder setDrivingLicenceNumber(String drivingLicenceNumber) {
            this.drivingLicenceNumber = drivingLicenceNumber;
            return this;
        }

        public DriverBuilder setExpiryDate(int expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public DriverBuilder setDrivingExperience(int drivingExperience) {
            this.drivingExperience = drivingExperience;
            return this;
        }

        public DriverBuilder setAssigned(boolean assigned) {
            this.assigned = assigned;
            return this;
        }

        public Driver createDriver() {
            Driver driver = new Driver();
            driver.setId(id);
            driver.setFirstName(firstName);
            driver.setLastName(lastName);
            driver.setPhoneNumber(phoneNumber);
            driver.setEmail(email);
            driver.setPassword(password);
            driver.setRole(role);
            driver.setDrivingLicenceNumber(drivingLicenceNumber);
            driver.setExpiryDate(expiryDate);
            driver.setDrivingExperience(drivingExperience);
            driver.setAssigned(assigned);
            return driver;
        }
    }

}

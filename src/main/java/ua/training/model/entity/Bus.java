package ua.training.model.entity;

public class Bus {
    private int id;
    private String model;
    private String licensePlate;
    private int manufactureYear;
    private String parkingSpot;
    private boolean used;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", manufactureYear=" + manufactureYear +
                ", parkingSpot='" + parkingSpot + '\'' +
                ", used=" + used +
                '}';
    }
}

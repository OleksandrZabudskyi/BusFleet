package ua.training.model.entity;

public class Bus {
    private int id;
    private String busModel;
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

    public String getBusModel() {
        return busModel;
    }

    public void setBusModel(String busModel) {
        this.busModel = busModel;
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
                ", busModel='" + busModel + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", manufactureYear=" + manufactureYear +
                ", parkingSpot='" + parkingSpot + '\'' +
                ", used=" + used +
                '}';
    }
}

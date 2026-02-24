package ProblemDomain;

public abstract class Vehicle implements Rentable{
    //since this is an abstract class we dont need to be worried about child class specific attributes
    private long carID;
    private String vehicleType;
    private String subType;
    private int speed;
    private double fuel;
    private int seats;
    private int year;
    private String drivetrain;
    private int price;
    private int quantity;

    public Vehicle(long carID, String vehicleType, String subType, int speed, double fuel, int seats, int year, String drivetrain, int price, int quantity){
        this.carID = carID;
        this.vehicleType = vehicleType;
        this.subType = subType;
        this.speed = speed;
        this.fuel = fuel;
        this.seats = seats;
        this.year = year;
        this.drivetrain = drivetrain;
        this.price = price;
        this.quantity = quantity;
    }

    public long getCarID(){
        return carID;
    }

    public String getVehicleType(){
        return vehicleType;
    }

    public String getSubType(){
        return subType;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getDriveTrain() { 
        return drivetrain; 
    }

    @Override //per instructions
    public boolean isAvailable(){
        return quantity > 0;
    }

    @Override
    public void checkout(){
        if (isAvailable()){
            quantity--; //quick method
        }
    }

    public String toFileString(){
        return carID + ";" + vehicleType + ";" + subType + ";" + speed + ";" + fuel + ";" + 
               seats + ";" + year + ";" + drivetrain + ";" + price + ";" + quantity;
    }

    @Override
    public String toString(){
        return "Car ID:\t\t" + carID + "\n" +
                "Vehicle Type:\t" + vehicleType + "\n" +
                "Sub Type:\t" + subType + "\n" +
                "Speed:\t\t" + speed + "\n" +
                "Fuel:\t\t" + fuel + "\n" +
                "Number of Seats:\t" + seats + "\n" +
                "Year:\t\t" + year + "\n" +
                "DriveTrain:\t\t" + drivetrain + "\n" +
                "Price:\t\t" + price + "\n" +
                "Available:\t\t" + quantity + "\n";
    }
    
}

package ProblemDomain;

public class SUV extends Vehicle {

	public SUV(long carID, String vehicleType, String subType, int speed, double fuel, int seats, int year,
			String drivetrain, int price, int quantity) {
		super(carID, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity);
	}

	@Override
	public String toFileString() {
		return super.toFileString();
	}

	@Override
	public String toString() {
		return super.toString() + "DriveTrain:\t\t" + getDriveTrain() + "\n";
	}
}
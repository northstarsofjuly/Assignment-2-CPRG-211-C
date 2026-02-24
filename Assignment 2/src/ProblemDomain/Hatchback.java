package ProblemDomain;

public class Hatchback extends Vehicle {
	private String hatchType;

	public Hatchback(long carID, String vehicleType, String subType, int speed, double fuel, int seats, int year,
			String drivetrain, int price, int quantity, String hatchType) {
		super(carID, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity);
		this.hatchType = hatchType;
	}

	public String getHatchType() {
		return hatchType;
	}

	private String getFormattedHatchType() {
		switch (hatchType.toUpperCase()) {
		case "S":
			return "Standard Liftgate";
		case "T":
			return "Split Liftgate";
		case "P":
			return "Power Liftgate";
		default:
			return hatchType;
		}
	}

	@Override
	public String toFileString() {
		return super.toFileString() + ";" + hatchType;
	}

	@Override
	public String toString() {
		return super.toString() + "Hatch Type:\t" + getFormattedHatchType() + "\n";
	}
}
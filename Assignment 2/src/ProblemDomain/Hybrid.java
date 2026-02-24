package ProblemDomain;

public class Hybrid extends Vehicle {
	private String powerTrain;
	private int electricRange;
	
	public Hybrid(long carID, String vehicleType, String subType, double fuel, int seats, int year, String drivetrain, double price, int quantity, String powerTrain, int electricRange) {
		super(carID, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity);
		
		this.powerTrain = powerTrain;
		this.electricRange = electricRange;
	}
	
	public String getPowerTrain() {
		return powerTrain;
	}
	
	private String getFormattedPowerTrain() {
		switch (powerTrain.toUpperCase()) {
		case "E": return "Series Hybrid";
		case "A": return "Parallel Hybrid";
		case "PHEV": return "Plug-in Hybrid";
		default: return powerTrain;
		}
		
	}
	
	@Override
	public String toFileString() {
		return super.toFileString() + ";" powerTrain + ";" + electricRange;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Power Train:\t" + getFormattedPowerTrain() + "\nElectric Range:\t" + electricRange + "\n";
	}

}

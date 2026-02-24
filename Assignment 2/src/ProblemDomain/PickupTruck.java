package ProblemDomain;

public class PickupTruck extends Vehicle {
	
	private String cargoBeds;
	private int cargoCapacity;
	
	public PickupTruck(long carID, String vehicleType, String subType, double fuel, int seats, int year, String drivetrain, double price, int quantity, String cargoBeds, int cargoCapacity) {
		super(carID, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity);
		
		this.cargoBeds = cargoBeds;
		this.cargoCapacity = cargoCapacity;
	}
	
	private String getFormattedCargoBeds() {
		switch (cargoBeds.toUpperCase()) {
		case "SB": return "Short Bed";
		case "EB": return "Extended Bed";
		case "DB": return "Dump Bed";
		default: return cargoBeds;
		}
		
	}
	
	@Override
	public String toFileString() {
		return super.toFileString() + ";" + cargoBeds + ";" + cargoCapacity;

	}
	
	@Override
	public String toString() {
		return super.toString() + "Cargo Capacity:\t" + cargoCapacity + "\nCargo Beds:\t" + getFormattedCargoBeds() + "\n";
	}

}

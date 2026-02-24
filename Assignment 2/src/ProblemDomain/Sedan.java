package ProblemDomain;



public class Sedan extends Vehicle {
   private String trunkSize;



   public Sedan(long carID, String vehicleType, String subType, int speed, double fuel, int seats, int year, String drivetrain, int price, int quantity, String trunkSize) {
       super(carID, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity);
       this.trunkSize = trunkSize;
   }



   public String getTrunkSize() { return trunkSize; }



   private String getFormattedTrunkSize() {
       switch (trunkSize.toUpperCase()) {
           case "L": return "Large/spacious Trunk";
           case "S": return "Small Trunk";
           case "M": return "Moderate Trunk";
           default: return trunkSize;
       }
   }

   @Override //per instructions to override tostring
   public String toFileString() {
       return super.toFileString() + ";" + trunkSize;
   }

   @Override //per instructions to override tostring
   public String toString() {
       return super.toString() + "Trunk Size:\t" + getFormattedTrunkSize() + "\n";
   }
}
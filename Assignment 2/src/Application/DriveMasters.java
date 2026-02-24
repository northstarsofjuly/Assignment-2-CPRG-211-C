package Application;
import ProblemDomain.*;
import java.io.*;
import java.util.*;

import javax.annotation.processing.FilerException;
import javax.print.event.PrintEvent;

public class DriveMasters {
    public static final String FILE_NAME = "vehicles.txt";
    public static List<Vehicle> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        loadVehicles();
        int option = 0;
        do {
            System.out.println("Welcome to DriveMasters\nPlease choose any option below");
            System.out.println("---------------------------------");
            System.out.println("1\tPurchase Vehicle");
            System.out.println("2\tFind Vehicle by Type");
            System.out.println("3\tDisplay Vehicles by Subtype");
            System.out.println("4\tProduce Random Vehicles List");
            System.out.println("5\tSave & Exit");

            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Not a valid input!");
                scanner.nextLine();
                continue;
            }
            switch (option) { //Easy way of getting user input
                case 1: purchaseVehicle(); 
                break;
                case 2: findVehicleByType();
                break;
                case 3: displayVehicleBySubtype();
                break;
                case 4: produceRandomVehicleList();
                break;
                case 5: saveAndExit();
                break;
                default: System.out.println("Invalid! Try again.");
            }
        } while (option != 5);
    }

    //parse method
    private static void loadVehicles(){
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){ //per https://www.w3schools.com/java/java_bufferedreader.asp
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(";");
                if (parts.length < 10) continue;
                long carID = Long.parseLong(parts[0]); //we split it into components like previous codes we've written
                String vehicleType = parts[1];
                String subType = parts[2];
                int speed = Integer.parseInt(parts[3]);
                double fuel = Double.parseDouble(parts[4]);
                int seats = Integer.parseInt(parts[5]);
                int year = Integer.parseInt(parts[6]);
                String drivetrain = parts[7];
                int price = Integer.parseInt(parts[8]);
                int quantity = Integer.parseInt(parts[9]);

                int firstDigit = Character.getNumericValue(String.valueOf(carID).charAt(0));//How we determine the type of vehicle
                
                switch (firstDigit){
                    case 1:
                        inventory.add(new Sedan(carID, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity, parts[10]));//parts[10] is trunk size
                        break;
                    case 2:
                        inventory.add(new Hatchback(carID, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity, parts[10]));//parts[10] is HatchType
                        break;
                    case 3:
                        inventory.add(new SUV(carID, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity)); //no parts [10]
                        break;
                    case 4:
                        inventory.add(new Hybrid(carID, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity, parts[10], Integer.parseInt(parts[11]))); //power train and electric range
                        break;
                    case 5:
                        inventory.add(new PickupTruck(carID, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity, parts[10], Integer.parseInt(parts[11]))); //power train and electric range
                        break;
                }
            } 
        } catch (IOException e){
                System.out.println("Error reading the data file");
            } catch (NumberFormatException e){
                System.out.print("Error parsing vehicle data");
            }
        }

    private static void purchaseVehicle(){
        System.out.print("Enter Car ID: ");
        try {
            long carID = scanner.nextLong();
            scanner.nextLine();
            boolean found = false;
            for (Vehicle v: inventory){
                if (v.getCarID() == carID){
                    found = true;
                    if (v.isAvailable()){
                        v.checkout();
                        System.out.println("The Vehicle \"" + v.getVehicleType() + " " + v.getSubType() + "\" has been checked out.");
                    } else {
                        System.out.println("The vehicle is not available.");
                    }
                    break;
                }
            }
            if (!found) {
                System.out.println("Error: Car ID does not match.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }
    private static void findVehicleByType(){
        System.out.print("Enter vehicle type to search for: (Sedan, SUV, Hatchback, Pickup Truck and Hybrid car)");
        String type = scanner.nextLine().trim();
        System.out.println("Matching vehicles: ");
        for (Vehicle v: inventory){
            if (v.getVehicleType().equalsIgnoreCase(type)||
            (type.equalsIgnoreCase("hybrid car")&&v.getVehicleType().equalsIgnoreCase("hybrid"))){
            System.out.print(v.toString());
            }
        }
    }

    private static void displayVehicleBySubtype(){
        System.out.println("#\tSub Type");
        System.out.println("1\tSedan");
        System.out.println("2\tHatchBack");
        System.out.println("3\tSUV");
        System.out.println("4\tHybrid");
        System.out.println("5\tPickup Truck");
        System.out.print("Enter type of vehicle: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Matching Vehicles:");

        switch(choice){
            case 1:
                System.out.print("Enter a format (L for Large/Spacious trunk, S for Small Trunk, or M for Moderate Trunk): ");
                String trunk = scanner.nextLine().trim(); //ignores white space
                for (Vehicle v : inventory){
                    if (v instanceof Sedan && ((Sedan) v).getTrunkSize().equalsIgnoreCase(trunk)){
                        System.out.print(v.toString());
                    }
                }
                break;
            case 2:
                System.out.print("Enter HatchType (S for Standard Liftgate, T for Split Liftgate, P for Power Liftgate): ");
                String hatch = scanner.nextLine().trim(); //ignores white space
                for (Vehicle v : inventory){
                    if (v instanceof Hatchback && ((Hatchback) v).getHatchType().equalsIgnoreCase(hatch)){
                        System.out.print(v.toString());
                    }
                }
                break;
            case 3:
                System.out.print("Enter Drivetrain (AWD for All Wheel Drive, 4WD for Four Wheel Drive): ");
                String dt = scanner.nextLine().trim(); //ignores white space
                for (Vehicle v : inventory){
                    if (v instanceof SUV && ((SUV) v).getDriveTrain().equalsIgnoreCase(dt)){
                        System.out.print(v.toString());
                    }
                }
                break;
            case 4:
                System.out.print("Enter a PowerTrain (E for Series Hybrid , A for Parallel Hybrid, PHEV for Plug-in Hybrid): ");
                String pt = scanner.nextLine().trim(); //ignores white space
                for (Vehicle v : inventory){
                    if (v instanceof Hybrid && ((Hybrid) v).getPowerTrain().equalsIgnoreCase(pt)){
                        System.out.print(v.toString());
                    }
                }
                break;
            case 5:
                System.out.print("Enter CargoBeds (SB for Short Beds , EB for Extended Beds, DB for Dump Beds): ");
                String bed = scanner.nextLine().trim();
                for (Vehicle v : inventory) {
                    if (v instanceof PickupTruck && ((PickupTruck) v).getCargoBeds().equalsIgnoreCase(bed)) {
                        System.out.print(v.toString());
                    }
                }
                break;
            default:
                System.out.println("Not valid!");
        }
    }

    private static void produceRandomVehicleList(){
        System.out.print("Enter number of vehicles to display: ");
        try {
            int count = scanner.nextInt();
            scanner.nextLine();
            if (inventory.isEmpty()){
                System.out.println("No vehicles in inventory.");
                return;
            }
            System.out.println("Random Vehicles: ");
            Random rand = new Random();
            for (int i = 0; i < count; i++){
                int randomIndex = rand.nextInt(inventory.size());
                System.out.print(inventory.get(randomIndex).toString());
                System.out.println("----------------------------------");
            }
        } catch (InputMismatchException e){
            System.out.println("Invalid number！");
            scanner.nextLine();
        }
    }

    private static void saveAndExit(){
        System.out.print("Saving vehicles...");
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))){
            for (Vehicle v : inventory){
                pw.println(v.toFileString());
            } System.out.println("Done!");
        } catch (IOException e){
            System.out.println("Error saving!");
        }
        System.out.println("Goodbye!");
    }
}

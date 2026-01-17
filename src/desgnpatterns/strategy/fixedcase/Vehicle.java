package desgnpatterns.strategy.fixedcase;

/**
 * CONTEXT CLASS: Vehicle that uses DriveStrategy
 * 
 * STRATEGY PATTERN COMPONENT: Context
 * -------------------------------------
 * The Context maintains a reference to one of the Strategy objects.
 * It does NOT know the concrete class of a strategy - it works with
 * all strategies through the Strategy interface.
 * 
 * KEY BENEFITS OF THIS DESIGN:
 * 1. OPEN-CLOSED PRINCIPLE: Can add new strategies without modifying Vehicle
 * 2. SINGLE RESPONSIBILITY: Vehicle focuses on vehicle concerns, not drive logic
 * 3. RUNTIME FLEXIBILITY: Can change strategy at runtime via setDriveStrategy()
 * 4. LOOSE COUPLING: Vehicle doesn't depend on concrete strategy classes
 * 5. EASY TESTING: Can inject mock strategies for unit testing
 * 
 * COMPARISON WITH FAILURE CASE:
 * - No switch/if-else statements for drive behavior
 * - Adding new vehicle types doesn't require code changes here
 * - Drive behavior is encapsulated in separate strategy classes
 * 
 * @see desgnpatterns.strategy.failurecase.Vehicle for the problematic version
 */
public class Vehicle {
    
    /**
     * Reference to the current drive strategy
     * This is the key to the Strategy Pattern - we program to an interface,
     * not to a concrete implementation.
     */
    private DriveStrategy driveStrategy;
    
    /**
     * Vehicle properties
     */
    private String model;
    private String manufacturer;
    private int year;
    
    /**
     * Constructor with strategy injection (Dependency Injection)
     * 
     * This allows the client to specify the behavior at object creation time.
     * The vehicle doesn't need to know which concrete strategy it will use.
     * 
     * @param model The model name of the vehicle
     * @param manufacturer The manufacturer name
     * @param year The year of manufacture
     * @param driveStrategy The initial drive strategy to use
     */
    public Vehicle(String model, String manufacturer, int year, DriveStrategy driveStrategy) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.driveStrategy = driveStrategy;
    }
    
    /**
     * Convenience constructor for simple cases
     * Defaults to NormalDriveStrategy
     */
    public Vehicle(String model, String manufacturer, int year) {
        this(model, manufacturer, year, new NormalDriveStrategy());
    }
    
    /**
     * Perform the drive action using the current strategy
     * 
     * KEY POINT: This method doesn't know HOW the vehicle drives.
     * It delegates the responsibility to the strategy object.
     * This is the heart of the Strategy Pattern!
     */
    public void performDrive() {
        String vehicleInfo = String.format("%d %s %s", year, manufacturer, model);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        driveStrategy.drive(vehicleInfo);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
    
    /**
     * RUNTIME STRATEGY CHANGE: This is a major advantage over the failure case!
     * 
     * Allows changing the drive behavior at runtime without:
     * - Creating a new Vehicle object
     * - Modifying any existing code
     * - Losing vehicle state
     * 
     * USE CASES:
     * - Switch from Normal to Sport mode
     * - Activate Off-Road mode when terrain changes
     * - Switch to Electric mode in hybrid vehicles
     * 
     * @param driveStrategy The new drive strategy to use
     */
    public void setDriveStrategy(DriveStrategy driveStrategy) {
        System.out.println("🔄 Switching drive mode: " + 
                          this.driveStrategy.getStrategyName() + " → " + 
                          driveStrategy.getStrategyName());
        this.driveStrategy = driveStrategy;
    }
    
    /**
     * Get current drive strategy
     * Useful for logging and debugging
     */
    public DriveStrategy getDriveStrategy() {
        return driveStrategy;
    }
    
    /**
     * Display vehicle information along with current drive capability
     */
    public void displayInfo() {
        System.out.println("╭─────────────────────────────────────────────╮");
        System.out.println("│ VEHICLE INFORMATION                         │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│ Model: " + padRight(model, 37) + "│");
        System.out.println("│ Manufacturer: " + padRight(manufacturer, 30) + "│");
        System.out.println("│ Year: " + padRight(String.valueOf(year), 38) + "│");
        System.out.println("│ Drive Mode: " + padRight(driveStrategy.getStrategyName(), 32) + "│");
        System.out.println("│ Max Speed: " + padRight(driveStrategy.getMaxSpeed() + " km/h", 33) + "│");
        System.out.println("╰─────────────────────────────────────────────╯");
    }
    
    /**
     * Helper method for display formatting
     */
    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
    
    // Standard getters
    public String getModel() {
        return model;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public int getYear() {
        return year;
    }
}








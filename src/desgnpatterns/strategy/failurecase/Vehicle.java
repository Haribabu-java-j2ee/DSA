package desgnpatterns.strategy.failurecase;

/**
 * FAILURE CASE: Vehicle class WITHOUT Strategy Pattern
 * 
 * PROBLEM DEMONSTRATION:
 * ---------------------
 * This class shows what happens when we DON'T use the Strategy Pattern.
 * All driving behaviors are hardcoded using if-else or switch statements.
 * 
 * ISSUES WITH THIS APPROACH:
 * 1. OPEN-CLOSED PRINCIPLE VIOLATION: Every new vehicle type requires modifying this class
 * 2. CODE DUPLICATION: Similar logic repeated across multiple vehicle subclasses
 * 3. TIGHT COUPLING: Drive behavior is tightly coupled with vehicle type
 * 4. MAINTENANCE NIGHTMARE: Changes to one drive behavior may affect others
 * 5. TESTING DIFFICULTY: Cannot test drive behaviors in isolation
 * 6. RUNTIME FLEXIBILITY: Cannot change drive behavior at runtime
 * 
 * @see desgnpatterns.strategy.fixedcase.Vehicle for the corrected implementation
 */
public class Vehicle {
    
    private String vehicleType;
    private String model;
    
    public Vehicle(String vehicleType, String model) {
        this.vehicleType = vehicleType;
        this.model = model;
    }
    
    /**
     * PROBLEMATIC METHOD: Uses conditional logic to determine drive behavior
     * 
     * FAILURE POINTS:
     * - Adding a new vehicle type (e.g., "HELICOPTER") requires modifying this method
     * - If drive logic changes for "CAR", we risk breaking "TRUCK" or "MOTORCYCLE" logic
     * - Cannot reuse "NORMAL_DRIVE" logic for a new vehicle without copy-paste
     * - Violates Single Responsibility Principle (SRP) - method does too many things
     */
    public void drive() {
        switch (vehicleType.toUpperCase()) {
            case "CAR":
                // Normal driving capability
                System.out.println(model + " is driving normally on road with 4 wheels.");
                System.out.println("Speed limit: 120 km/h, Fuel: Petrol/Diesel");
                break;
                
            case "SPORTS_CAR":
                // Sport driving capability - high speed
                System.out.println(model + " is driving in SPORT MODE!");
                System.out.println("Speed limit: 300 km/h, Turbo: ENABLED, Fuel: Premium");
                break;
                
            case "TRUCK":
                // Heavy vehicle driving capability
                System.out.println(model + " is driving as heavy vehicle.");
                System.out.println("Speed limit: 80 km/h, Load capacity: HIGH, Fuel: Diesel");
                break;
                
            case "ELECTRIC_CAR":
                // Electric driving capability
                System.out.println(model + " is driving silently on electric power.");
                System.out.println("Speed limit: 150 km/h, Battery: Lithium-ion, Emission: ZERO");
                break;
                
            case "MOTORCYCLE":
                // Two-wheeler driving capability
                System.out.println(model + " is riding on 2 wheels.");
                System.out.println("Speed limit: 180 km/h, Fuel: Petrol, Lane splitting: ALLOWED");
                break;
                
            case "OFF_ROAD":
                // Off-road driving capability
                System.out.println(model + " is driving in OFF-ROAD mode!");
                System.out.println("4x4: ENGAGED, Ground clearance: HIGH, Terrain: ANY");
                break;
                
            default:
                // PROBLEM: What if someone adds a new vehicle type?
                // This default case will silently fail or throw an error
                System.out.println("Unknown vehicle type: " + vehicleType);
                System.out.println("Cannot determine drive capability!");
                throw new UnsupportedOperationException(
                    "Drive behavior not defined for: " + vehicleType +
                    ". Developer must modify Vehicle.java to add support!"
                );
        }
    }
    
    /**
     * ANOTHER PROBLEMATIC METHOD: Cannot change drive behavior at runtime
     * 
     * SCENARIO: What if a normal car wants to switch to sport mode temporarily?
     * With this design, it's IMPOSSIBLE without changing vehicleType string.
     */
    public void performEmergencyDrive() {
        // Forced to duplicate logic or create complex workarounds
        System.out.println("Emergency mode activated for " + model);
        
        // PROBLEM: Cannot simply "switch strategy" - must add more conditions
        if (vehicleType.equals("CAR") || vehicleType.equals("SPORTS_CAR")) {
            System.out.println("Engaging sport mode for emergency...");
        } else if (vehicleType.equals("ELECTRIC_CAR")) {
            System.out.println("Engaging max battery power for emergency...");
        } else {
            System.out.println("Standard emergency driving...");
        }
    }
    
    // Getters
    public String getVehicleType() {
        return vehicleType;
    }
    
    public String getModel() {
        return model;
    }
}


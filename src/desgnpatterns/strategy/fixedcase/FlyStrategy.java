package desgnpatterns.strategy.fixedcase;

/**
 * CONCRETE STRATEGY: Fly Capability (Demonstrates Extensibility)
 * 
 * STRATEGY PATTERN COMPONENT: Concrete Strategy (New Addition)
 * --------------------------------------------------------------
 * This class demonstrates how easily new strategies can be added
 * without modifying any existing code!
 * 
 * In the failure case, adding helicopter support required:
 * - Modifying Vehicle.java
 * - Adding new case to switch statement
 * - Risk of breaking existing code
 * 
 * With Strategy Pattern:
 * - Just create this new class
 * - Implement the interface
 * - That's it! Zero changes to existing code!
 * 
 * USE CASES:
 * - Helicopters
 * - Drones
 * - Flying cars (future!)
 * 
 * CHARACTERISTICS:
 * - Speed Limit: 250 km/h (cruise)
 * - Altitude: Variable
 * - Controls: 3D movement
 */
public class FlyStrategy implements DriveStrategy {
    
    private static final int MAX_SPEED = 250;
    private static final String STRATEGY_NAME = "FLY_MODE";
    
    /**
     * Implements flying behavior
     * 
     * Note: Although the interface method is called "drive",
     * we implement flying behavior. The interface represents
     * "movement capability" in general terms.
     * 
     * @param vehicleModel The model name of the vehicle/aircraft
     */
    @Override
    public void drive(String vehicleModel) {
        System.out.println("🚁 [FLY MODE]");
        System.out.println("   Vehicle: " + vehicleModel);
        System.out.println("   ├─ AIRBORNE OPERATIONS ACTIVATED!");
        System.out.println("   ├─ Cruise Speed: " + MAX_SPEED + " km/h");
        System.out.println("   ├─ Altitude Control: ACTIVE");
        System.out.println("   ├─ Rotor: SPINNING");
        System.out.println("   ├─ Navigation: GPS ENABLED");
        System.out.println("   ├─ Autopilot: AVAILABLE");
        System.out.println("   └─ Landing Gear: RETRACTED");
    }
    
    @Override
    public String getStrategyName() {
        return STRATEGY_NAME;
    }
    
    @Override
    public int getMaxSpeed() {
        return MAX_SPEED;
    }
}







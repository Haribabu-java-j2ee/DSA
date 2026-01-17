package desgnpatterns.strategy.fixedcase;

/**
 * CONCRETE STRATEGY: Normal Drive Capability
 * 
 * STRATEGY PATTERN COMPONENT: Concrete Strategy
 * -----------------------------------------------
 * Implements the standard driving behavior for regular vehicles.
 * 
 * USE CASES:
 * - Sedans, Hatchbacks, Regular cars
 * - City driving conditions
 * - Fuel-efficient driving mode
 * 
 * CHARACTERISTICS:
 * - Speed Limit: 120 km/h
 * - Fuel Type: Petrol/Diesel
 * - Drive Mode: Comfort
 */
public class NormalDriveStrategy implements DriveStrategy {
    
    private static final int MAX_SPEED = 120;
    private static final String STRATEGY_NAME = "NORMAL_DRIVE";
    
    /**
     * Implements normal driving behavior
     * 
     * @param vehicleModel The model name of the vehicle
     */
    @Override
    public void drive(String vehicleModel) {
        System.out.println("🚗 [NORMAL DRIVE MODE]");
        System.out.println("   Vehicle: " + vehicleModel);
        System.out.println("   ├─ Driving normally on road with standard settings");
        System.out.println("   ├─ Speed Limit: " + MAX_SPEED + " km/h");
        System.out.println("   ├─ Fuel Type: Petrol/Diesel");
        System.out.println("   ├─ Drive Mode: Comfort");
        System.out.println("   └─ Traction Control: ON");
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








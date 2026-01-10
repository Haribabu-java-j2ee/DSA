package desgnpatterns.strategy.fixedcase;

/**
 * CONCRETE STRATEGY: Heavy Vehicle Drive Capability
 * 
 * STRATEGY PATTERN COMPONENT: Concrete Strategy
 * -----------------------------------------------
 * Implements driving behavior for trucks and heavy vehicles.
 * 
 * USE CASES:
 * - Trucks, Lorries
 * - Buses
 * - Construction vehicles
 * 
 * CHARACTERISTICS:
 * - Speed Limit: 80 km/h (legal limit for heavy vehicles)
 * - Load Capacity: High
 * - Fuel Type: Diesel
 * - Air Brakes: Enabled
 */
public class HeavyVehicleDriveStrategy implements DriveStrategy {
    
    private static final int MAX_SPEED = 80;
    private static final String STRATEGY_NAME = "HEAVY_VEHICLE_DRIVE";
    
    /**
     * Implements heavy vehicle driving behavior
     * 
     * @param vehicleModel The model name of the vehicle
     */
    @Override
    public void drive(String vehicleModel) {
        System.out.println("🚛 [HEAVY VEHICLE DRIVE MODE]");
        System.out.println("   Vehicle: " + vehicleModel);
        System.out.println("   ├─ Heavy vehicle mode activated");
        System.out.println("   ├─ Speed Limit: " + MAX_SPEED + " km/h");
        System.out.println("   ├─ Fuel Type: Diesel");
        System.out.println("   ├─ Load Capacity: HIGH");
        System.out.println("   ├─ Air Brakes: ENABLED");
        System.out.println("   ├─ Jake Brake: READY");
        System.out.println("   └─ Wide Turn Alert: ON");
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







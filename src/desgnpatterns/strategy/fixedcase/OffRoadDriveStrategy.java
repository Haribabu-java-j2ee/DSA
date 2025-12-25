package desgnpatterns.strategy.fixedcase;

/**
 * CONCRETE STRATEGY: Off-Road Drive Capability
 * 
 * STRATEGY PATTERN COMPONENT: Concrete Strategy
 * -----------------------------------------------
 * Implements off-road driving behavior for rugged terrains.
 * 
 * USE CASES:
 * - SUVs, Jeeps, Land Rovers
 * - 4x4 vehicles
 * - Adventure/Expedition vehicles
 * 
 * CHARACTERISTICS:
 * - Speed Limit: 80 km/h (safety in rough terrain)
 * - 4WD: Engaged
 * - Ground Clearance: High
 * - Terrain: Any (mud, sand, rocks, snow)
 */
public class OffRoadDriveStrategy implements DriveStrategy {
    
    private static final int MAX_SPEED = 80;
    private static final String STRATEGY_NAME = "OFF_ROAD_DRIVE";
    
    /**
     * Implements off-road driving behavior
     * 
     * @param vehicleModel The model name of the vehicle
     */
    @Override
    public void drive(String vehicleModel) {
        System.out.println("🏔️ [OFF-ROAD DRIVE MODE]");
        System.out.println("   Vehicle: " + vehicleModel);
        System.out.println("   ├─ OFF-ROAD MODE ENGAGED!");
        System.out.println("   ├─ Speed Limit: " + MAX_SPEED + " km/h");
        System.out.println("   ├─ 4WD: ENGAGED");
        System.out.println("   ├─ Differential Lock: ON");
        System.out.println("   ├─ Ground Clearance: MAXIMUM");
        System.out.println("   ├─ Hill Descent Control: ACTIVE");
        System.out.println("   ├─ Terrain Mode: ALL-TERRAIN");
        System.out.println("   └─ Skid Plates: PROTECTED");
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


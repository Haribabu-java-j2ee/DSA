package desgnpatterns.strategy.fixedcase;

/**
 * CONCRETE STRATEGY: Sport Drive Capability
 * 
 * STRATEGY PATTERN COMPONENT: Concrete Strategy
 * -----------------------------------------------
 * Implements high-performance driving behavior for sports vehicles.
 * 
 * USE CASES:
 * - Sports cars (Ferrari, Lamborghini, Porsche)
 * - Track driving
 * - Performance mode in regular cars
 * 
 * CHARACTERISTICS:
 * - Speed Limit: 300 km/h
 * - Fuel Type: Premium
 * - Drive Mode: Sport/Track
 * - Turbo: Enabled
 */
public class SportDriveStrategy implements DriveStrategy {
    
    private static final int MAX_SPEED = 300;
    private static final String STRATEGY_NAME = "SPORT_DRIVE";
    
    /**
     * Implements sport/high-performance driving behavior
     * 
     * @param vehicleModel The model name of the vehicle
     */
    @Override
    public void drive(String vehicleModel) {
        System.out.println("🏎️ [SPORT DRIVE MODE]");
        System.out.println("   Vehicle: " + vehicleModel);
        System.out.println("   ├─ SPORT MODE ENGAGED!");
        System.out.println("   ├─ Speed Limit: " + MAX_SPEED + " km/h");
        System.out.println("   ├─ Fuel Type: Premium Octane");
        System.out.println("   ├─ Turbo: ENABLED");
        System.out.println("   ├─ Suspension: STIFF");
        System.out.println("   ├─ Throttle Response: AGGRESSIVE");
        System.out.println("   └─ Launch Control: READY");
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








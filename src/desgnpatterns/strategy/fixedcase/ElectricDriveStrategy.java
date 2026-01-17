package desgnpatterns.strategy.fixedcase;

/**
 * CONCRETE STRATEGY: Electric Drive Capability
 * 
 * STRATEGY PATTERN COMPONENT: Concrete Strategy
 * -----------------------------------------------
 * Implements electric vehicle driving behavior with zero emissions.
 * 
 * USE CASES:
 * - Electric vehicles (Tesla, Rivian, Lucid)
 * - Hybrid vehicles in EV mode
 * - Eco-friendly driving
 * 
 * CHARACTERISTICS:
 * - Speed Limit: 200 km/h
 * - Power Source: Lithium-ion Battery
 * - Emission: Zero
 * - Regenerative Braking: Enabled
 */
public class ElectricDriveStrategy implements DriveStrategy {
    
    private static final int MAX_SPEED = 200;
    private static final String STRATEGY_NAME = "ELECTRIC_DRIVE";
    
    /**
     * Implements electric vehicle driving behavior
     * 
     * @param vehicleModel The model name of the vehicle
     */
    @Override
    public void drive(String vehicleModel) {
        System.out.println("⚡ [ELECTRIC DRIVE MODE]");
        System.out.println("   Vehicle: " + vehicleModel);
        System.out.println("   ├─ Silent electric propulsion activated");
        System.out.println("   ├─ Speed Limit: " + MAX_SPEED + " km/h");
        System.out.println("   ├─ Power Source: Lithium-ion Battery");
        System.out.println("   ├─ Emission: ZERO");
        System.out.println("   ├─ Regenerative Braking: ON");
        System.out.println("   ├─ Instant Torque: AVAILABLE");
        System.out.println("   └─ Autopilot: READY");
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








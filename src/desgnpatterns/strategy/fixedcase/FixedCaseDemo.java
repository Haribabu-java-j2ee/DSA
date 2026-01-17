package desgnpatterns.strategy.fixedcase;

/**
 * FIXED CASE DEMONSTRATION: Strategy Pattern in Action
 * 
 * This demo shows how the Strategy Pattern solves all the problems
 * demonstrated in the failure case.
 * 
 * Run this class to see:
 * 1. How vehicles use different strategies
 * 2. How strategies can be changed at runtime
 * 3. How new strategies can be added without modifying existing code
 */
public class FixedCaseDemo {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║      STRATEGY PATTERN - FIXED CASE DEMONSTRATION             ║");
        System.out.println("║      (Problems Solved with Strategy Pattern)                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // =====================================================================
        // SCENARIO 1: Creating vehicles with different strategies
        // =====================================================================
        System.out.println("━━━ SCENARIO 1: Creating Vehicles with Different Strategies ━━━");
        System.out.println();
        
        // Each vehicle is created with its specific drive strategy
        // Note: Vehicle class doesn't need to know about specific drive behaviors!
        Vehicle sedan = new Vehicle("Camry", "Toyota", 2024, new NormalDriveStrategy());
        Vehicle sportsCar = new Vehicle("F8 Tributo", "Ferrari", 2024, new SportDriveStrategy());
        Vehicle electricCar = new Vehicle("Model S", "Tesla", 2024, new ElectricDriveStrategy());
        Vehicle suv = new Vehicle("Defender", "Land Rover", 2024, new OffRoadDriveStrategy());
        Vehicle truck = new Vehicle("Semi", "Volvo", 2024, new HeavyVehicleDriveStrategy());
        
        // Demonstrate each vehicle driving
        System.out.println("▶ Toyota Camry (Normal Drive):");
        sedan.performDrive();
        System.out.println();
        
        System.out.println("▶ Ferrari F8 Tributo (Sport Drive):");
        sportsCar.performDrive();
        System.out.println();
        
        System.out.println("▶ Tesla Model S (Electric Drive):");
        electricCar.performDrive();
        System.out.println();
        
        // =====================================================================
        // SCENARIO 2: Runtime Strategy Change (IMPOSSIBLE in failure case!)
        // =====================================================================
        System.out.println("━━━ SCENARIO 2: Runtime Strategy Change (Key Advantage!) ━━━");
        System.out.println();
        System.out.println("✅ SOLVED: Can now change drive behavior at runtime!");
        System.out.println();
        
        // Create a hybrid vehicle that can switch modes
        Vehicle hybridSUV = new Vehicle("RAV4 Prime", "Toyota", 2024, new NormalDriveStrategy());
        
        System.out.println("▶ Toyota RAV4 Prime in NORMAL mode:");
        hybridSUV.performDrive();
        System.out.println();
        
        // Switch to electric mode - same vehicle, different behavior!
        hybridSUV.setDriveStrategy(new ElectricDriveStrategy());
        System.out.println("▶ Same vehicle after switching to ELECTRIC mode:");
        hybridSUV.performDrive();
        System.out.println();
        
        // Switch to off-road mode for terrain
        hybridSUV.setDriveStrategy(new OffRoadDriveStrategy());
        System.out.println("▶ Same vehicle after switching to OFF-ROAD mode:");
        hybridSUV.performDrive();
        System.out.println();
        
        // =====================================================================
        // SCENARIO 3: Adding New Strategy (Open-Closed Principle)
        // =====================================================================
        System.out.println("━━━ SCENARIO 3: Adding New Strategy (No Code Changes!) ━━━");
        System.out.println();
        System.out.println("✅ SOLVED: Adding HELICOPTER support requires:");
        System.out.println("   1. Create new class: FlyStrategy implements DriveStrategy");
        System.out.println("   2. That's it! No changes to Vehicle or other strategies!");
        System.out.println();
        
        // Demonstrating with a new strategy (simulated)
        Vehicle helicopter = new Vehicle("Apache", "Boeing", 2024, new FlyStrategy());
        System.out.println("▶ Boeing Apache Helicopter:");
        helicopter.performDrive();
        System.out.println();
        
        // =====================================================================
        // SCENARIO 4: Code Reuse (No Duplication)
        // =====================================================================
        System.out.println("━━━ SCENARIO 4: Code Reuse (DRY Principle) ━━━");
        System.out.println();
        System.out.println("✅ SOLVED: Multiple vehicles can share the same strategy!");
        System.out.println();
        
        // Same OffRoadDriveStrategy instance used by multiple vehicles
        DriveStrategy sharedOffRoadStrategy = new OffRoadDriveStrategy();
        
        Vehicle jeep = new Vehicle("Wrangler", "Jeep", 2024, sharedOffRoadStrategy);
        Vehicle landCruiser = new Vehicle("Land Cruiser", "Toyota", 2024, sharedOffRoadStrategy);
        Vehicle gClass = new Vehicle("G-Wagon", "Mercedes", 2024, sharedOffRoadStrategy);
        
        System.out.println("▶ All these vehicles share OFF-ROAD capability:");
        System.out.println("   - Jeep Wrangler: " + jeep.getDriveStrategy().getStrategyName());
        System.out.println("   - Toyota Land Cruiser: " + landCruiser.getDriveStrategy().getStrategyName());
        System.out.println("   - Mercedes G-Wagon: " + gClass.getDriveStrategy().getStrategyName());
        System.out.println("   No code duplication - all use same OffRoadDriveStrategy class!");
        System.out.println();
        
        // =====================================================================
        // SCENARIO 5: Easy Testing
        // =====================================================================
        System.out.println("━━━ SCENARIO 5: Easy Testing ━━━");
        System.out.println();
        System.out.println("✅ SOLVED: Each strategy can be tested independently!");
        System.out.println();
        System.out.println("   // Unit test for SportDriveStrategy");
        System.out.println("   @Test");
        System.out.println("   void testSportDriveMaxSpeed() {");
        System.out.println("       DriveStrategy strategy = new SportDriveStrategy();");
        System.out.println("       assertEquals(300, strategy.getMaxSpeed());");
        System.out.println("   }");
        System.out.println();
        System.out.println("   // Can also inject mock strategy for Vehicle tests");
        System.out.println("   @Test");
        System.out.println("   void testVehicleWithMockStrategy() {");
        System.out.println("       DriveStrategy mockStrategy = mock(DriveStrategy.class);");
        System.out.println("       Vehicle vehicle = new Vehicle(\"Test\", \"Mock\", 2024, mockStrategy);");
        System.out.println("       vehicle.performDrive();");
        System.out.println("       verify(mockStrategy).drive(anyString());");
        System.out.println("   }");
        System.out.println();
        
        // =====================================================================
        // VEHICLE INFO DISPLAY
        // =====================================================================
        System.out.println("━━━ VEHICLE INFO DISPLAY ━━━");
        System.out.println();
        sedan.displayInfo();
        System.out.println();
        sportsCar.displayInfo();
        System.out.println();
        
        // =====================================================================
        // SUMMARY
        // =====================================================================
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    SUCCESS SUMMARY                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ ✅ Open-Closed Principle: Add strategies without changes     ║");
        System.out.println("║ ✅ Single Responsibility: Each strategy has one job          ║");
        System.out.println("║ ✅ DRY Principle: Strategies are reusable across vehicles    ║");
        System.out.println("║ ✅ Runtime Flexibility: Change behavior on the fly           ║");
        System.out.println("║ ✅ Loose Coupling: Vehicle doesn't know strategy details     ║");
        System.out.println("║ ✅ Easy Testing: Strategies testable in isolation            ║");
        System.out.println("║ ✅ Composition over Inheritance: Flexible behavior mixing    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}








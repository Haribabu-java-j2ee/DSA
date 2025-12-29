package desgnpatterns.strategy.failurecase;

/**
 * FAILURE CASE DEMONSTRATION
 * 
 * This demo shows the problems that arise when Strategy Pattern is NOT used.
 * Run this class to see how the system fails or becomes unmaintainable.
 * 
 * PROBLEMS DEMONSTRATED:
 * 1. Adding new vehicle types requires code modification
 * 2. Cannot change behavior at runtime
 * 3. Violation of Open-Closed Principle
 * 4. Code duplication across similar behaviors
 */
public class FailureCaseDemo {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║     STRATEGY PATTERN - FAILURE CASE DEMONSTRATION            ║");
        System.out.println("║     (What happens WITHOUT the Strategy Pattern)              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // =====================================================================
        // SCENARIO 1: Existing vehicle types work fine
        // =====================================================================
        System.out.println("━━━ SCENARIO 1: Existing Vehicle Types (Works Fine) ━━━");
        System.out.println();
        
        Vehicle car = new Vehicle("CAR", "Toyota Camry");
        Vehicle sportsCar = new Vehicle("SPORTS_CAR", "Ferrari F8");
        Vehicle truck = new Vehicle("TRUCK", "Ford F-150");
        
        System.out.println("▶ Testing CAR:");
        car.drive();
        System.out.println();
        
        System.out.println("▶ Testing SPORTS_CAR:");
        sportsCar.drive();
        System.out.println();
        
        System.out.println("▶ Testing TRUCK:");
        truck.drive();
        System.out.println();
        
        // =====================================================================
        // SCENARIO 2: Problem - Adding a new vehicle type
        // =====================================================================
        System.out.println("━━━ SCENARIO 2: Adding New Vehicle Type (FAILURE!) ━━━");
        System.out.println();
        System.out.println("⚠️  Business Requirement: Add support for HELICOPTER");
        System.out.println("⚠️  Current Impact: Must modify Vehicle.java class!");
        System.out.println();
        
        try {
            Vehicle helicopter = new Vehicle("HELICOPTER", "Apache AH-64");
            System.out.println("▶ Testing HELICOPTER:");
            helicopter.drive(); // This will throw an exception!
        } catch (UnsupportedOperationException e) {
            System.out.println("❌ EXCEPTION: " + e.getMessage());
            System.out.println();
            System.out.println("💔 FAILURE ANALYSIS:");
            System.out.println("   - Developer must modify Vehicle.java");
            System.out.println("   - Add new case in switch statement");
            System.out.println("   - Risk breaking existing functionality");
            System.out.println("   - Requires recompilation of Vehicle class");
            System.out.println("   - Violates Open-Closed Principle!");
        }
        System.out.println();
        
        // =====================================================================
        // SCENARIO 3: Problem - Cannot change behavior at runtime
        // =====================================================================
        System.out.println("━━━ SCENARIO 3: Runtime Behavior Change (IMPOSSIBLE!) ━━━");
        System.out.println();
        System.out.println("⚠️  Requirement: Switch Toyota Camry to SPORT MODE temporarily");
        System.out.println();
        
        System.out.println("▶ Current behavior of Toyota Camry:");
        car.drive();
        System.out.println();
        
        System.out.println("▶ Attempting to change to sport mode...");
        System.out.println("❌ PROBLEM: Cannot change drive behavior without creating new Vehicle!");
        System.out.println("❌ Would need: car = new Vehicle(\"SPORTS_CAR\", \"Toyota Camry\")");
        System.out.println("❌ This loses the original vehicle identity and state!");
        System.out.println();
        
        // =====================================================================
        // SCENARIO 4: Problem - Code Duplication
        // =====================================================================
        System.out.println("━━━ SCENARIO 4: Code Duplication Issue ━━━");
        System.out.println();
        System.out.println("⚠️  Requirement: Both SUV and CROSSOVER need OFF-ROAD capability");
        System.out.println();
        System.out.println("💔 Without Strategy Pattern:");
        System.out.println("   - Must copy-paste OFF-ROAD code to both cases");
        System.out.println("   - If OFF-ROAD logic changes, must update BOTH places");
        System.out.println("   - Easy to miss one, causing inconsistent behavior");
        System.out.println("   - Violates DRY (Don't Repeat Yourself) principle!");
        System.out.println();
        
        // =====================================================================
        // SCENARIO 5: Testing Difficulty
        // =====================================================================
        System.out.println("━━━ SCENARIO 5: Testing Difficulty ━━━");
        System.out.println();
        System.out.println("💔 Problems with Unit Testing:");
        System.out.println("   - Cannot test SPORT_DRIVE behavior in isolation");
        System.out.println("   - Must create full Vehicle object to test any behavior");
        System.out.println("   - Cannot mock drive behaviors");
        System.out.println("   - Test coverage is tightly coupled");
        System.out.println();
        
        // =====================================================================
        // SUMMARY
        // =====================================================================
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    FAILURE SUMMARY                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ ❌ Open-Closed Principle Violated                            ║");
        System.out.println("║ ❌ Single Responsibility Principle Violated                  ║");
        System.out.println("║ ❌ DRY Principle Violated                                    ║");
        System.out.println("║ ❌ Cannot change behavior at runtime                         ║");
        System.out.println("║ ❌ Tight coupling between vehicle and drive behavior         ║");
        System.out.println("║ ❌ Difficult to test individual behaviors                    ║");
        System.out.println("║ ❌ Adding new behaviors requires modifying existing code     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ 💡 SOLUTION: Use Strategy Pattern! See fixedcase package     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}



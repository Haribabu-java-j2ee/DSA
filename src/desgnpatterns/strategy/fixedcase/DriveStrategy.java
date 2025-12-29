package desgnpatterns.strategy.fixedcase;

/**
 * STRATEGY INTERFACE: Defines the contract for all drive behaviors
 * 
 * STRATEGY PATTERN COMPONENT: Strategy Interface
 * ------------------------------------------------
 * This interface defines the common algorithm interface that all
 * concrete strategies must implement.
 * 
 * KEY BENEFITS:
 * 1. Provides a common interface for all drive behaviors
 * 2. Enables polymorphism - any strategy can be used interchangeably
 * 3. Allows adding new strategies without modifying existing code
 * 4. Supports dependency injection and easy testing
 * 
 * DESIGN PRINCIPLES FOLLOWED:
 * - Interface Segregation Principle (ISP): Focused, minimal interface
 * - Dependency Inversion Principle (DIP): High-level modules depend on abstraction
 * - Open-Closed Principle (OCP): Open for extension, closed for modification
 * 
 * @see NormalDriveStrategy
 * @see SportDriveStrategy
 * @see ElectricDriveStrategy
 * @see OffRoadDriveStrategy
 */
public interface DriveStrategy {
    
    /**
     * Executes the specific driving behavior
     * 
     * Each concrete strategy implements this method with its own algorithm.
     * The Vehicle class calls this method without knowing which specific
     * strategy is being used - achieving loose coupling.
     * 
     * @param vehicleModel The model name of the vehicle (for display purposes)
     */
    void drive(String vehicleModel);
    
    /**
     * Returns the name/type of this drive strategy
     * Useful for logging and debugging
     * 
     * @return String representation of the strategy type
     */
    String getStrategyName();
    
    /**
     * Returns the maximum speed allowed by this strategy
     * 
     * @return Maximum speed in km/h
     */
    int getMaxSpeed();
}



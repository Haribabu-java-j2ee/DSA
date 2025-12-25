package desgnpatterns.observer.fixedcase;

/**
 * OBSERVER INTERFACE: Defines the contract for all notification subscribers
 * 
 * OBSERVER PATTERN COMPONENT: Observer Interface
 * ------------------------------------------------
 * This interface defines the common update method that all
 * concrete observers must implement.
 * 
 * KEY BENEFITS:
 * 1. Provides a common interface for all notification channels
 * 2. Enables polymorphism - any observer can be added/removed
 * 3. Subject (Product) only knows about this interface, not implementations
 * 4. Supports dynamic subscription at runtime
 * 
 * DESIGN PRINCIPLES FOLLOWED:
 * - Interface Segregation Principle (ISP): Focused, minimal interface
 * - Dependency Inversion Principle (DIP): Subject depends on abstraction
 * - Open-Closed Principle (OCP): Add new observers without changes
 * 
 * @see EmailNotificationObserver
 * @see SMSNotificationObserver
 * @see WhatsAppNotificationObserver
 * @see InAppNotificationObserver
 */
public interface StockObserver {
    
    /**
     * Called by the Subject (Product) when stock changes
     * 
     * Each concrete observer implements this method with its own
     * notification logic. The Product doesn't know or care how
     * the notification is sent - achieving loose coupling.
     * 
     * @param productName The name of the product
     * @param message The notification message
     * @param stockCount The new stock count
     * @param price The product price
     */
    void update(String productName, String message, int stockCount, double price);
    
    /**
     * Returns the type/name of this observer
     * Useful for logging and debugging
     * 
     * @return String representation of the observer type
     */
    String getObserverType();
    
    /**
     * Returns the subscriber identifier (email, phone, user ID)
     * 
     * @return The subscriber's unique identifier
     */
    String getSubscriberId();
}


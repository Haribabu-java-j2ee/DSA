package desgnpatterns.observer.fixedcase;

/**
 * SUBJECT INTERFACE: Defines the contract for observable objects
 * 
 * OBSERVER PATTERN COMPONENT: Subject Interface
 * -----------------------------------------------
 * This interface defines methods for managing observers (subscribers).
 * Any class that wants to be "observed" should implement this interface.
 * 
 * KEY METHODS:
 * 1. subscribe() - Add a new observer
 * 2. unsubscribe() - Remove an existing observer  
 * 3. notifyObservers() - Notify all observers of state change
 * 
 * DESIGN PRINCIPLES FOLLOWED:
 * - Interface Segregation: Only observer management methods
 * - Open-Closed: New subjects can be created without modification
 * 
 * @see Product for concrete implementation
 */
public interface StockSubject {
    
    /**
     * Add a new observer to the subscription list
     * 
     * @param observer The observer to add
     */
    void subscribe(StockObserver observer);
    
    /**
     * Remove an observer from the subscription list
     * 
     * @param observer The observer to remove
     */
    void unsubscribe(StockObserver observer);
    
    /**
     * Notify all subscribed observers about state change
     * This method iterates through all observers and calls their update() method
     */
    void notifyObservers();
    
    /**
     * Get the current count of subscribers
     * 
     * @return Number of active subscribers
     */
    int getSubscriberCount();
}







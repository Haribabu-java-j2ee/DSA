package desgnpatterns.observer.fixedcase;

import java.util.ArrayList;
import java.util.List;

/**
 * CONCRETE SUBJECT: Product that notifies observers about stock changes
 * 
 * OBSERVER PATTERN COMPONENT: Concrete Subject
 * ----------------------------------------------
 * This class maintains a list of observers and notifies them
 * when the stock status changes.
 * 
 * KEY BENEFITS OF THIS DESIGN:
 * 1. LOOSE COUPLING: Product doesn't know about Email, SMS, WhatsApp, etc.
 * 2. OPEN-CLOSED: Add new notification channels without modifying Product
 * 3. SINGLE RESPONSIBILITY: Product manages inventory, observers handle notifications
 * 4. DYNAMIC SUBSCRIPTION: Observers can subscribe/unsubscribe at runtime
 * 5. EASY TESTING: Can inject mock observers for unit tests
 * 
 * COMPARISON WITH FAILURE CASE:
 * - No direct references to notification services
 * - No hardcoded notification calls
 * - Can add unlimited notification types without code changes
 * 
 * @see desgnpatterns.observer.failurecase.Product for the problematic version
 */
public class Product implements StockSubject {
    
    /**
     * List of observers (subscribers) interested in this product's stock updates
     * This is the key to Observer Pattern - we maintain a LIST of observers
     */
    private List<StockObserver> observers;
    
    /**
     * Product properties
     */
    private String productId;
    private String productName;
    private int stockCount;
    private double price;
    
    /**
     * Constructor - Initializes the observers list
     * 
     * Note: No notification services are created here!
     * Observers will subscribe themselves when needed.
     */
    public Product(String productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockCount = 0;
        this.observers = new ArrayList<>();
    }
    
    /**
     * SUBSCRIBE: Add a new observer to receive notifications
     * 
     * This method allows dynamic subscription at runtime.
     * Any class implementing StockObserver can subscribe!
     * 
     * @param observer The observer to add
     */
    @Override
    public void subscribe(StockObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("✅ Subscribed: " + observer.getObserverType() + 
                             " (" + observer.getSubscriberId() + ") to " + productName);
        }
    }
    
    /**
     * UNSUBSCRIBE: Remove an observer from the notification list
     * 
     * This method allows dynamic unsubscription at runtime.
     * Users can opt-out of notifications whenever they want!
     * 
     * @param observer The observer to remove
     */
    @Override
    public void unsubscribe(StockObserver observer) {
        if (observers.remove(observer)) {
            System.out.println("🚫 Unsubscribed: " + observer.getObserverType() + 
                             " (" + observer.getSubscriberId() + ") from " + productName);
        }
    }
    
    /**
     * NOTIFY: Inform all observers about the stock change
     * 
     * This is the heart of Observer Pattern!
     * Product doesn't know WHO the observers are or HOW they notify.
     * It just calls update() on each observer polymorphically.
     */
    @Override
    public void notifyObservers() {
        String message = String.format(
            "🎉 Great News! %s is back in stock!\n" +
            "   Price: $%.2f\n" +
            "   Available Units: %d\n" +
            "   Hurry! Limited stock available!",
            productName, price, stockCount
        );
        
        System.out.println("\n📢 Notifying " + observers.size() + " subscriber(s)...\n");
        
        // Iterate through all observers and notify them
        // Product doesn't care if it's Email, SMS, WhatsApp, or anything else!
        for (StockObserver observer : observers) {
            observer.update(productName, message, stockCount, price);
        }
    }
    
    /**
     * Get current subscriber count
     */
    @Override
    public int getSubscriberCount() {
        return observers.size();
    }
    
    /**
     * Update stock and notify observers if product is back in stock
     * 
     * Business Logic: Only notify when stock changes from 0 to positive
     * (product is "back in stock")
     * 
     * @param newStockCount The new stock count
     */
    public void updateStock(int newStockCount) {
        int previousStock = this.stockCount;
        this.stockCount = newStockCount;
        
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📦 Stock Update: " + productName);
        System.out.println("   Previous: " + previousStock + " → New: " + newStockCount);
        System.out.println("   Subscribers waiting: " + observers.size());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        // Notify observers when product is back in stock
        if (previousStock == 0 && newStockCount > 0) {
            notifyObservers();
        }
    }
    
    /**
     * Display product information
     */
    public void displayInfo() {
        System.out.println("╭─────────────────────────────────────────────╮");
        System.out.println("│ PRODUCT INFORMATION                         │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│ ID: " + padRight(productId, 40) + "│");
        System.out.println("│ Name: " + padRight(productName, 38) + "│");
        System.out.println("│ Price: $" + padRight(String.format("%.2f", price), 36) + "│");
        System.out.println("│ Stock: " + padRight(String.valueOf(stockCount), 37) + "│");
        System.out.println("│ Subscribers: " + padRight(String.valueOf(observers.size()), 31) + "│");
        System.out.println("╰─────────────────────────────────────────────╯");
    }
    
    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
    
    // Getters
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getStockCount() { return stockCount; }
    public double getPrice() { return price; }
}







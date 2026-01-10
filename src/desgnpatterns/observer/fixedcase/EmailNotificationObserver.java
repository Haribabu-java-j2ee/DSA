package desgnpatterns.observer.fixedcase;

/**
 * CONCRETE OBSERVER: Email Notification
 * 
 * OBSERVER PATTERN COMPONENT: Concrete Observer
 * -----------------------------------------------
 * Implements the StockObserver interface to send email notifications.
 * 
 * This class is completely independent of Product!
 * Product only knows about StockObserver interface.
 * 
 * USE CASES:
 * - Users who prefer email notifications
 * - Detailed product information with links
 * - Newsletter-style updates
 */
public class EmailNotificationObserver implements StockObserver {
    
    private String email;
    private String userName;
    
    /**
     * Constructor with subscriber details
     * 
     * @param email The subscriber's email address
     * @param userName The subscriber's name for personalization
     */
    public EmailNotificationObserver(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }
    
    /**
     * Receive update from Subject and send email notification
     * 
     * This method is called by Product.notifyObservers()
     * Product doesn't know this is an email - it just calls update()!
     */
    @Override
    public void update(String productName, String message, int stockCount, double price) {
        System.out.println("📧 [EMAIL NOTIFICATION]");
        System.out.println("   ┌─────────────────────────────────────────────");
        System.out.println("   │ To: " + email);
        System.out.println("   │ Subject: 🎉 " + productName + " is Back in Stock!");
        System.out.println("   │ ");
        System.out.println("   │ Dear " + userName + ",");
        System.out.println("   │ ");
        System.out.println("   │ Great news! The product you've been waiting for");
        System.out.println("   │ is now available:");
        System.out.println("   │ ");
        System.out.println("   │ Product: " + productName);
        System.out.println("   │ Price: $" + String.format("%.2f", price));
        System.out.println("   │ In Stock: " + stockCount + " units");
        System.out.println("   │ ");
        System.out.println("   │ [Shop Now] [View Product] [Unsubscribe]");
        System.out.println("   └─────────────────────────────────────────────");
        System.out.println();
    }
    
    @Override
    public String getObserverType() {
        return "EMAIL";
    }
    
    @Override
    public String getSubscriberId() {
        return email;
    }
}







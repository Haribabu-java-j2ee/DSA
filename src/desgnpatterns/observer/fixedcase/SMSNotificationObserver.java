package desgnpatterns.observer.fixedcase;

/**
 * CONCRETE OBSERVER: SMS Notification
 * 
 * OBSERVER PATTERN COMPONENT: Concrete Observer
 * -----------------------------------------------
 * Implements the StockObserver interface to send SMS notifications.
 * 
 * USE CASES:
 * - Users who want instant mobile alerts
 * - Short, concise notifications
 * - Users without smartphones
 */
public class SMSNotificationObserver implements StockObserver {
    
    private String phoneNumber;
    private String userName;
    
    /**
     * Constructor with subscriber details
     * 
     * @param phoneNumber The subscriber's phone number
     * @param userName The subscriber's name
     */
    public SMSNotificationObserver(String phoneNumber, String userName) {
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }
    
    /**
     * Receive update from Subject and send SMS notification
     * 
     * SMS messages are kept short due to character limits
     */
    @Override
    public void update(String productName, String message, int stockCount, double price) {
        System.out.println("📱 [SMS NOTIFICATION]");
        System.out.println("   ┌─────────────────────────────────────────────");
        System.out.println("   │ To: " + phoneNumber);
        System.out.println("   │ ");
        System.out.println("   │ Hi " + userName + "! " + productName);
        System.out.println("   │ is back in stock at $" + String.format("%.2f", price) + "!");
        System.out.println("   │ Only " + stockCount + " left. Shop now!");
        System.out.println("   │ ");
        System.out.println("   │ Reply STOP to unsubscribe");
        System.out.println("   └─────────────────────────────────────────────");
        System.out.println();
    }
    
    @Override
    public String getObserverType() {
        return "SMS";
    }
    
    @Override
    public String getSubscriberId() {
        return phoneNumber;
    }
}


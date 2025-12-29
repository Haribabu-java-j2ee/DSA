package desgnpatterns.observer.fixedcase;

/**
 * CONCRETE OBSERVER: WhatsApp Notification
 * 
 * OBSERVER PATTERN COMPONENT: Concrete Observer
 * -----------------------------------------------
 * Implements the StockObserver interface to send WhatsApp notifications.
 * 
 * USE CASES:
 * - Users who prefer WhatsApp for shopping alerts
 * - Rich media notifications with images
 * - Interactive messages with quick reply buttons
 */
public class WhatsAppNotificationObserver implements StockObserver {
    
    private String phoneNumber;
    private String userName;
    
    /**
     * Constructor with subscriber details
     * 
     * @param phoneNumber The subscriber's WhatsApp number
     * @param userName The subscriber's name
     */
    public WhatsAppNotificationObserver(String phoneNumber, String userName) {
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }
    
    /**
     * Receive update from Subject and send WhatsApp notification
     * 
     * WhatsApp supports rich formatting and interactive elements
     */
    @Override
    public void update(String productName, String message, int stockCount, double price) {
        System.out.println("💬 [WHATSAPP NOTIFICATION]");
        System.out.println("   ┌─────────────────────────────────────────────");
        System.out.println("   │ To: " + phoneNumber + " (WhatsApp)");
        System.out.println("   │ ");
        System.out.println("   │ 🛒 *STOCK ALERT*");
        System.out.println("   │ ");
        System.out.println("   │ Hey " + userName + "! 👋");
        System.out.println("   │ ");
        System.out.println("   │ *" + productName + "* is back in stock!");
        System.out.println("   │ ");
        System.out.println("   │ 💰 Price: *$" + String.format("%.2f", price) + "*");
        System.out.println("   │ 📦 Available: " + stockCount + " units");
        System.out.println("   │ ");
        System.out.println("   │ [🛍️ Buy Now]  [ℹ️ Details]  [❌ Unsubscribe]");
        System.out.println("   └─────────────────────────────────────────────");
        System.out.println();
    }
    
    @Override
    public String getObserverType() {
        return "WHATSAPP";
    }
    
    @Override
    public String getSubscriberId() {
        return phoneNumber;
    }
}



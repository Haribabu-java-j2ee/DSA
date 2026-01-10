package desgnpatterns.observer.fixedcase;

/**
 * CONCRETE OBSERVER: In-App Push Notification
 * 
 * OBSERVER PATTERN COMPONENT: Concrete Observer
 * -----------------------------------------------
 * Implements the StockObserver interface to send in-app push notifications.
 * 
 * USE CASES:
 * - Mobile app users
 * - Real-time alerts within the shopping app
 * - Users who have the app installed
 */
public class InAppNotificationObserver implements StockObserver {
    
    private String userId;
    private String deviceToken;
    private String userName;
    
    /**
     * Constructor with subscriber details
     * 
     * @param userId The subscriber's user ID
     * @param deviceToken The device token for push notifications
     * @param userName The subscriber's name
     */
    public InAppNotificationObserver(String userId, String deviceToken, String userName) {
        this.userId = userId;
        this.deviceToken = deviceToken;
        this.userName = userName;
    }
    
    /**
     * Receive update from Subject and send push notification
     * 
     * Push notifications appear on the device's notification tray
     */
    @Override
    public void update(String productName, String message, int stockCount, double price) {
        System.out.println("🔔 [IN-APP PUSH NOTIFICATION]");
        System.out.println("   ┌─────────────────────────────────────────────");
        System.out.println("   │ Device: " + deviceToken.substring(0, 8) + "...");
        System.out.println("   │ User ID: " + userId);
        System.out.println("   │ ");
        System.out.println("   │ ╭──────────────────────────────────╮");
        System.out.println("   │ │ 🛒 ShopNow                    ⋮ │");
        System.out.println("   │ ├──────────────────────────────────┤");
        System.out.println("   │ │ " + productName + " is back!        │");
        System.out.println("   │ │ $" + String.format("%.2f", price) + " • " + stockCount + " available      │");
        System.out.println("   │ │ Tap to view                      │");
        System.out.println("   │ ╰──────────────────────────────────╯");
        System.out.println("   └─────────────────────────────────────────────");
        System.out.println();
    }
    
    @Override
    public String getObserverType() {
        return "IN-APP PUSH";
    }
    
    @Override
    public String getSubscriberId() {
        return userId;
    }
}







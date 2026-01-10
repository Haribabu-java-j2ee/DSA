package desgnpatterns.observer.fixedcase;

/**
 * CONCRETE OBSERVER: Telegram Notification (Demonstrates Extensibility)
 * 
 * OBSERVER PATTERN COMPONENT: Concrete Observer (New Addition)
 * --------------------------------------------------------------
 * This class demonstrates how easily new observers can be added
 * without modifying any existing code!
 * 
 * In the failure case, adding Telegram support required:
 * - Modifying Product.java
 * - Adding new field and constructor initialization
 * - Adding call in notifyAllSubscribers()
 * - Risk of breaking existing code
 * 
 * With Observer Pattern:
 * - Just create this new class
 * - Implement the interface
 * - Subscribe to products
 * - That's it! Zero changes to existing code!
 * 
 * USE CASES:
 * - Telegram bot notifications
 * - Users who prefer Telegram over WhatsApp
 * - Tech-savvy users
 */
public class TelegramNotificationObserver implements StockObserver {
    
    private String chatId;
    private String userName;
    
    /**
     * Constructor with subscriber details
     * 
     * @param chatId The Telegram chat ID
     * @param userName The subscriber's Telegram username
     */
    public TelegramNotificationObserver(String chatId, String userName) {
        this.chatId = chatId;
        this.userName = userName;
    }
    
    /**
     * Receive update from Subject and send Telegram message
     * 
     * Telegram supports rich formatting with Markdown
     */
    @Override
    public void update(String productName, String message, int stockCount, double price) {
        System.out.println("✈️ [TELEGRAM NOTIFICATION]");
        System.out.println("   ┌─────────────────────────────────────────────");
        System.out.println("   │ Chat ID: " + chatId);
        System.out.println("   │ Bot: @ShopNowStockBot");
        System.out.println("   │ ");
        System.out.println("   │ 🚨 *STOCK ALERT* 🚨");
        System.out.println("   │ ");
        System.out.println("   │ @" + userName + ", ");
        System.out.println("   │ ");
        System.out.println("   │ `" + productName + "`");
        System.out.println("   │ is now *available*!");
        System.out.println("   │ ");
        System.out.println("   │ 💵 Price: `$" + String.format("%.2f", price) + "`");
        System.out.println("   │ 📊 Stock: " + stockCount + " units");
        System.out.println("   │ ");
        System.out.println("   │ /buy  /details  /unsubscribe");
        System.out.println("   └─────────────────────────────────────────────");
        System.out.println();
    }
    
    @Override
    public String getObserverType() {
        return "TELEGRAM";
    }
    
    @Override
    public String getSubscriberId() {
        return chatId;
    }
}







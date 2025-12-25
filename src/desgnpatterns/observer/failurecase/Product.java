package desgnpatterns.observer.failurecase;

/**
 * FAILURE CASE: Product class WITHOUT Observer Pattern
 * 
 * PROBLEM DEMONSTRATION:
 * ---------------------
 * This class shows what happens when we DON'T use the Observer Pattern.
 * The Product class directly knows about and calls each notification service.
 * 
 * ISSUES WITH THIS APPROACH:
 * 1. TIGHT COUPLING: Product knows about all notification services (Email, SMS, WhatsApp, etc.)
 * 2. OPEN-CLOSED VIOLATION: Adding new notification channel requires modifying Product class
 * 3. SINGLE RESPONSIBILITY VIOLATION: Product manages inventory AND notifications
 * 4. MAINTENANCE NIGHTMARE: Changes to any notification affect Product class
 * 5. TESTING DIFFICULTY: Cannot test Product without all notification services
 * 6. SCALABILITY ISSUES: As notification types grow, Product becomes bloated
 * 7. NO DYNAMIC SUBSCRIPTION: Cannot add/remove subscribers at runtime
 * 
 * @see desgnpatterns.observer.fixedcase.Product for the corrected implementation
 */
public class Product {
    
    private String productId;
    private String productName;
    private int stockCount;
    private double price;
    
    // ❌ BAD: Direct references to all notification services
    // Adding a new notification type means adding another field here!
    private EmailService emailService;
    private SMSService smsService;
    private WhatsAppService whatsAppService;
    private InAppNotificationService inAppService;
    
    public Product(String productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockCount = 0;
        
        // ❌ BAD: Product creates its own dependencies
        // Violates Dependency Inversion Principle
        this.emailService = new EmailService();
        this.smsService = new SMSService();
        this.whatsAppService = new WhatsAppService();
        this.inAppService = new InAppNotificationService();
    }
    
    /**
     * PROBLEMATIC METHOD: Directly notifies all services
     * 
     * FAILURE POINTS:
     * - Adding PushNotificationService requires modifying this method
     * - Cannot disable a notification channel without code change
     * - Cannot add new subscribers at runtime
     * - Product knows too much about notification implementations
     */
    public void updateStock(int newStockCount) {
        int previousStock = this.stockCount;
        this.stockCount = newStockCount;
        
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📦 Stock Update: " + productName);
        System.out.println("   Previous: " + previousStock + " → New: " + newStockCount);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        // Only notify if stock was 0 and now available (back in stock scenario)
        if (previousStock == 0 && newStockCount > 0) {
            notifyAllSubscribers();
        }
    }
    
    /**
     * ❌ HIGHLY PROBLEMATIC METHOD
     * 
     * This method violates multiple SOLID principles:
     * - Open-Closed: Must modify to add new notification type
     * - Single Responsibility: Product shouldn't know about notifications
     * - Dependency Inversion: Depends on concrete implementations
     */
    private void notifyAllSubscribers() {
        String message = String.format(
            "🎉 Great News! %s is back in stock!\n" +
            "   Price: $%.2f\n" +
            "   Available Units: %d\n" +
            "   Hurry! Limited stock available!",
            productName, price, stockCount
        );
        
        System.out.println("\n📢 Notifying all subscribers...\n");
        
        // ❌ BAD: Hardcoded notification calls
        // What if we need to add Telegram? Slack? Push notifications?
        // Must modify THIS class every time!
        
        emailService.sendEmail(productName, message);
        smsService.sendSMS(productName, message);
        whatsAppService.sendWhatsApp(productName, message);
        inAppService.sendInAppNotification(productName, message);
        
        // ❌ FUTURE PROBLEM: Adding new notification service
        // telegramService.sendTelegram(productName, message);  // Must add this line!
        // slackService.sendSlack(productName, message);        // And this!
        // pushNotificationService.send(productName, message);  // And this!
        
        System.out.println();
    }
    
    /**
     * ❌ NO WAY TO DYNAMICALLY SUBSCRIBE/UNSUBSCRIBE
     * 
     * What if a user wants to:
     * - Only receive email, not SMS?
     * - Stop receiving notifications temporarily?
     * - A new user wants to subscribe?
     * 
     * Current design: IMPOSSIBLE without code changes!
     */
    
    // Getters
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getStockCount() { return stockCount; }
    public double getPrice() { return price; }
}


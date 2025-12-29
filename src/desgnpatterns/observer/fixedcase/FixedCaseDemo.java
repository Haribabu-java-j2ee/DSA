package desgnpatterns.observer.fixedcase;

/**
 * FIXED CASE DEMONSTRATION: Observer Pattern in Action
 * 
 * This demo shows how the Observer Pattern solves all the problems
 * demonstrated in the failure case.
 * 
 * Run this class to see:
 * 1. How subscribers register for product notifications
 * 2. How different notification channels work independently
 * 3. How subscribers can be added/removed dynamically
 * 4. How new notification types can be added without code changes
 */
public class FixedCaseDemo {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║      OBSERVER PATTERN - FIXED CASE DEMONSTRATION             ║");
        System.out.println("║      (E-Commerce Inventory Notification System)              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // =====================================================================
        // SETUP: Create products (Subjects)
        // =====================================================================
        System.out.println("━━━ SETUP: Creating Products ━━━");
        System.out.println();
        
        Product iPhone = new Product("IPHONE-15-PRO", "iPhone 15 Pro Max", 1199.00);
        Product macBook = new Product("MACBOOK-M3", "MacBook Pro M3", 2499.00);
        
        iPhone.displayInfo();
        System.out.println();
        macBook.displayInfo();
        System.out.println();
        
        // =====================================================================
        // SCENARIO 1: Users subscribe to iPhone notifications
        // =====================================================================
        System.out.println("━━━ SCENARIO 1: Users Subscribe to iPhone 15 Pro ━━━");
        System.out.println();
        
        // Create different observers (subscribers)
        StockObserver aliceEmail = new EmailNotificationObserver("alice@gmail.com", "Alice");
        StockObserver aliceWhatsApp = new WhatsAppNotificationObserver("+1-555-0101", "Alice");
        
        StockObserver bobSMS = new SMSNotificationObserver("+1-555-0202", "Bob");
        StockObserver bobPush = new InAppNotificationObserver("user_bob_123", "device_token_abc", "Bob");
        
        StockObserver charlieAll = new EmailNotificationObserver("charlie@outlook.com", "Charlie");
        
        // Subscribe observers to iPhone
        System.out.println("▶ Alice subscribes with Email and WhatsApp:");
        iPhone.subscribe(aliceEmail);
        iPhone.subscribe(aliceWhatsApp);
        System.out.println();
        
        System.out.println("▶ Bob subscribes with SMS and In-App Push:");
        iPhone.subscribe(bobSMS);
        iPhone.subscribe(bobPush);
        System.out.println();
        
        System.out.println("▶ Charlie subscribes with Email only:");
        iPhone.subscribe(charlieAll);
        System.out.println();
        
        // =====================================================================
        // SCENARIO 2: Stock Update - All subscribers notified
        // =====================================================================
        System.out.println("━━━ SCENARIO 2: iPhone 15 Pro Back in Stock! ━━━");
        System.out.println();
        System.out.println("✅ WATCH: Each subscriber gets notified via their preferred channel!");
        System.out.println();
        
        iPhone.updateStock(50);  // This triggers notifications to all 5 observers
        
        // =====================================================================
        // SCENARIO 3: Dynamic Unsubscription
        // =====================================================================
        System.out.println("━━━ SCENARIO 3: Alice Unsubscribes from WhatsApp ━━━");
        System.out.println();
        System.out.println("✅ SOLVED: Users can unsubscribe from specific channels at runtime!");
        System.out.println();
        
        iPhone.unsubscribe(aliceWhatsApp);
        System.out.println();
        
        System.out.println("▶ New stock update - Alice gets only Email now:");
        System.out.println();
        iPhone.updateStock(0);   // Out of stock
        iPhone.updateStock(25);  // Back in stock - triggers notifications
        
        // =====================================================================
        // SCENARIO 4: Adding New Notification Channel (Telegram)
        // =====================================================================
        System.out.println("━━━ SCENARIO 4: Adding Telegram Support (Zero Code Changes!) ━━━");
        System.out.println();
        System.out.println("✅ SOLVED: Just create TelegramNotificationObserver and subscribe!");
        System.out.println("   No changes to Product.java or any other class!");
        System.out.println();
        
        StockObserver daveTelegram = new TelegramNotificationObserver("123456789", "dave_tech");
        
        macBook.subscribe(daveTelegram);
        macBook.subscribe(new EmailNotificationObserver("dave@proton.me", "Dave"));
        System.out.println();
        
        macBook.updateStock(10);  // Dave gets Telegram and Email notifications
        
        // =====================================================================
        // SCENARIO 5: Different Products, Different Subscribers
        // =====================================================================
        System.out.println("━━━ SCENARIO 5: Product-Specific Subscriptions ━━━");
        System.out.println();
        System.out.println("✅ SOLVED: Each product maintains its own subscriber list!");
        System.out.println();
        
        System.out.println("▶ iPhone subscribers: " + iPhone.getSubscriberCount());
        System.out.println("▶ MacBook subscribers: " + macBook.getSubscriberCount());
        System.out.println();
        System.out.println("   Alice and Bob are subscribed to iPhone only.");
        System.out.println("   Dave is subscribed to MacBook only.");
        System.out.println("   They only get notifications for products they care about!");
        System.out.println();
        
        // =====================================================================
        // SCENARIO 6: Easy Testing
        // =====================================================================
        System.out.println("━━━ SCENARIO 6: Easy Unit Testing ━━━");
        System.out.println();
        System.out.println("✅ SOLVED: Can test with mock observers!");
        System.out.println();
        System.out.println("   @Test");
        System.out.println("   void testStockNotification() {");
        System.out.println("       Product product = new Product(\"TEST\", \"Test Product\", 99.0);");
        System.out.println("       ");
        System.out.println("       // Create a mock observer");
        System.out.println("       StockObserver mockObserver = mock(StockObserver.class);");
        System.out.println("       product.subscribe(mockObserver);");
        System.out.println("       ");
        System.out.println("       product.updateStock(10);");
        System.out.println("       ");
        System.out.println("       // Verify notification was sent");
        System.out.println("       verify(mockObserver).update(anyString(), anyString(), eq(10), anyDouble());");
        System.out.println("   }");
        System.out.println();
        System.out.println("   ✅ No real emails/SMS sent during testing!");
        System.out.println("   ✅ Product logic tested in isolation!");
        System.out.println();
        
        // =====================================================================
        // FINAL STATE
        // =====================================================================
        System.out.println("━━━ FINAL STATE ━━━");
        System.out.println();
        iPhone.displayInfo();
        System.out.println();
        macBook.displayInfo();
        System.out.println();
        
        // =====================================================================
        // SUMMARY
        // =====================================================================
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    SUCCESS SUMMARY                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ ✅ Loose Coupling: Product only knows StockObserver interface║");
        System.out.println("║ ✅ Open-Closed: Added Telegram without changing Product      ║");
        System.out.println("║ ✅ Single Responsibility: Each class has one job             ║");
        System.out.println("║ ✅ Dynamic Subscription: Subscribe/unsubscribe at runtime    ║");
        System.out.println("║ ✅ Per-User Preferences: Each user chooses their channels    ║");
        System.out.println("║ ✅ Easy Testing: Mock observers for unit tests               ║");
        System.out.println("║ ✅ Scalability: Unlimited observers with no code changes     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}



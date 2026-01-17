package desgnpatterns.observer.failurecase;

/**
 * FAILURE CASE DEMONSTRATION
 * 
 * This demo shows the problems that arise when Observer Pattern is NOT used.
 * Run this class to see how the system fails or becomes unmaintainable.
 * 
 * PROBLEMS DEMONSTRATED:
 * 1. Product is tightly coupled to all notification services
 * 2. Cannot add new notification channels without modifying Product
 * 3. Cannot subscribe/unsubscribe dynamically
 * 4. Product class has too many responsibilities
 */
public class FailureCaseDemo {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║     OBSERVER PATTERN - FAILURE CASE DEMONSTRATION            ║");
        System.out.println("║     (What happens WITHOUT the Observer Pattern)              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // =====================================================================
        // SCENARIO 1: Stock Update - All notifications sent
        // =====================================================================
        System.out.println("━━━ SCENARIO 1: iPhone 15 Pro Stock Update ━━━");
        System.out.println();
        
        Product iPhone = new Product("IPHONE-15-PRO", "iPhone 15 Pro Max", 1199.00);
        
        System.out.println("▶ Initial stock is 0. Updating to 100 units...");
        System.out.println();
        iPhone.updateStock(100);
        
        // =====================================================================
        // SCENARIO 2: Problem - Cannot disable specific notification
        // =====================================================================
        System.out.println("━━━ SCENARIO 2: User Wants Only Email (IMPOSSIBLE!) ━━━");
        System.out.println();
        System.out.println("⚠️  User Request: 'I only want Email notifications, not SMS/WhatsApp'");
        System.out.println();
        System.out.println("❌ PROBLEM: Current design CANNOT handle this!");
        System.out.println("   - No way to unsubscribe from specific channels");
        System.out.println("   - Would need to modify Product.java");
        System.out.println("   - Add boolean flags: enableEmail, enableSMS, enableWhatsApp...");
        System.out.println("   - This leads to feature envy and bloated code!");
        System.out.println();
        
        // =====================================================================
        // SCENARIO 3: Problem - Adding new notification channel
        // =====================================================================
        System.out.println("━━━ SCENARIO 3: Add Telegram Notifications (NIGHTMARE!) ━━━");
        System.out.println();
        System.out.println("⚠️  Business Requirement: Add Telegram notification support");
        System.out.println();
        System.out.println("❌ PROBLEM: Current design requires:");
        System.out.println("   1. Create TelegramService class");
        System.out.println("   2. Add 'private TelegramService telegramService' to Product");
        System.out.println("   3. Initialize it in Product constructor");
        System.out.println("   4. Add telegramService.send() in notifyAllSubscribers()");
        System.out.println("   5. MODIFY Product.java (violates Open-Closed Principle!)");
        System.out.println();
        System.out.println("   What if we need 10 more channels? Product becomes HUGE!");
        System.out.println();
        
        // =====================================================================
        // SCENARIO 4: Problem - Different products, different subscribers
        // =====================================================================
        System.out.println("━━━ SCENARIO 4: Different Subscribers per Product (CHAOS!) ━━━");
        System.out.println();
        System.out.println("⚠️  Requirement: ");
        System.out.println("   - User A subscribes to iPhone updates (Email only)");
        System.out.println("   - User B subscribes to MacBook updates (WhatsApp only)");
        System.out.println("   - User C subscribes to BOTH (All channels)");
        System.out.println();
        System.out.println("❌ PROBLEM: Current design CANNOT handle user-specific subscriptions!");
        System.out.println("   - All products notify ALL channels");
        System.out.println("   - No way to track who subscribed to what");
        System.out.println("   - Would need massive refactoring");
        System.out.println();
        
        // =====================================================================
        // SCENARIO 5: Testing Difficulty
        // =====================================================================
        System.out.println("━━━ SCENARIO 5: Unit Testing (PAIN!) ━━━");
        System.out.println();
        System.out.println("❌ PROBLEM: Cannot test Product without real notification services!");
        System.out.println();
        System.out.println("   // Want to test stock update logic only");
        System.out.println("   @Test");
        System.out.println("   void testStockUpdate() {");
        System.out.println("       Product p = new Product(...);");
        System.out.println("       p.updateStock(100);");
        System.out.println("       // ❌ This ACTUALLY sends emails, SMS, WhatsApp!");
        System.out.println("       // ❌ Cannot mock the notification services");
        System.out.println("       // ❌ Test is slow and has side effects");
        System.out.println("   }");
        System.out.println();
        
        // =====================================================================
        // SUMMARY
        // =====================================================================
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    FAILURE SUMMARY                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ ❌ Tight Coupling: Product → All Notification Services       ║");
        System.out.println("║ ❌ Open-Closed Violated: Must modify Product for new channel ║");
        System.out.println("║ ❌ Single Responsibility Violated: Product does too much     ║");
        System.out.println("║ ❌ No Dynamic Subscription: Can't subscribe/unsubscribe      ║");
        System.out.println("║ ❌ No Per-User Preferences: Everyone gets everything         ║");
        System.out.println("║ ❌ Testing Nightmare: Can't mock dependencies                ║");
        System.out.println("║ ❌ Scalability Issues: Adding channels bloats Product        ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ 💡 SOLUTION: Use Observer Pattern! See fixedcase package     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}








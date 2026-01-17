package desgnpatterns.observer.failurecase;

/**
 * Email Notification Service - Concrete implementation
 * 
 * In the failure case, Product directly creates and calls this service.
 * This creates tight coupling between Product and EmailService.
 */
public class EmailService {
    
    public void sendEmail(String productName, String message) {
        System.out.println("📧 [EMAIL SERVICE]");
        System.out.println("   To: subscriber@example.com");
        System.out.println("   Subject: " + productName + " is back in stock!");
        System.out.println("   Body: " + message.substring(0, Math.min(50, message.length())) + "...");
        System.out.println();
    }
}








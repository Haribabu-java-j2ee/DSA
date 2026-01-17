package desgnpatterns.observer.failurecase;

/**
 * SMS Notification Service - Concrete implementation
 * 
 * In the failure case, Product directly creates and calls this service.
 * This creates tight coupling between Product and SMSService.
 */
public class SMSService {
    
    public void sendSMS(String productName, String message) {
        System.out.println("📱 [SMS SERVICE]");
        System.out.println("   To: +1-234-567-8900");
        System.out.println("   Message: " + productName + " is now available! Check your email for details.");
        System.out.println();
    }
}








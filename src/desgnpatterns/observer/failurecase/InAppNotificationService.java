package desgnpatterns.observer.failurecase;

/**
 * In-App Notification Service - Concrete implementation
 * 
 * In the failure case, Product directly creates and calls this service.
 * This creates tight coupling between Product and InAppNotificationService.
 */
public class InAppNotificationService {
    
    public void sendInAppNotification(String productName, String message) {
        System.out.println("🔔 [IN-APP NOTIFICATION]");
        System.out.println("   Notification: " + productName + " is available!");
        System.out.println("   Action: View Product");
        System.out.println();
    }
}








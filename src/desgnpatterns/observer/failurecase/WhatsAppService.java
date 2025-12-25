package desgnpatterns.observer.failurecase;

/**
 * WhatsApp Notification Service - Concrete implementation
 * 
 * In the failure case, Product directly creates and calls this service.
 * This creates tight coupling between Product and WhatsAppService.
 */
public class WhatsAppService {
    
    public void sendWhatsApp(String productName, String message) {
        System.out.println("💬 [WHATSAPP SERVICE]");
        System.out.println("   To: +1-234-567-8900");
        System.out.println("   Message: 🛒 " + productName + " is back! Tap to buy now.");
        System.out.println();
    }
}


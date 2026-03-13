public class PaymentProcessor {
    
    public void processPayment(CreditCard card) {
        System.out.println("Processing Credit Card Payment");
    }
    
    public void processPayment(PayPal paypal) {
        System.out.println("Processing PayPal Payment");
    }
    
    public void processPayment(Bitcoin bitcoin) {
        System.out.println("Processing Bitcoin Payment, Transaction ID: " + bitcoin.getTransactionId());
    }
    
    public void processPayment(ApplePay applePay) {
        System.out.println("Processing Apple Pay Payment");
    }
}

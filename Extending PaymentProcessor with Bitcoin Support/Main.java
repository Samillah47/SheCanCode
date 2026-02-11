public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        
        CreditCard creditCard = new CreditCard();
        PayPal paypal = new PayPal();
        Bitcoin bitcoin = new Bitcoin("BTC-TX-123456789");
        ApplePay applePay = new ApplePay();
        
        System.out.println("Attempting payment with Credit Card:");
        processor.processPayment(creditCard);
        
        System.out.println("\nAttempting payment with PayPal:");
        processor.processPayment(paypal);
        
        System.out.println("\nAttempting payment with Bitcoin:");
        processor.processPayment(bitcoin);
        
        System.out.println("\nAttempting payment with Apple Pay:");
        processor.processPayment(applePay);
    }
}

        
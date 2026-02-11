public class Main {

    public static void main(String[] args) {

        NotificationService emailService = new EmailNotification();
        NotificationService smsService = new SMSNotification();
        NotificationService pushService = new PushNotification("DEVICE-XR500");

        System.out.println("\n Notifications ");
        emailService.sendNotification("Your order has been confirmed.");
        smsService.sendNotification("Your verification code is 482910.");
        pushService.sendNotification("You have a new friend request!");

        System.out.println("\nDefault Priority");
        emailService.setDefaultPriority("HIGH");
        smsService.setDefaultPriority("MEDIUM");
        pushService.setDefaultPriority("HIGH");
    }
}

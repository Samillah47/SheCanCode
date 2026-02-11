public class SMSNotification implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS Notification: " + message);
    }
}

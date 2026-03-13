public class PushNotification implements NotificationService {

    private String deviceId;

    public PushNotification(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Push Notification to device [" + deviceId + "]: " + message);
    }

    @Override
    public void setDefaultPriority(String priority) {
        System.out.println("Push Notification priority set to: " + priority);
    }
}

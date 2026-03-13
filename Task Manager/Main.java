package Day12;
public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        System.out.println("=== Testing Add Task ===");
        try {
            manager.addTask("Complete Java assignment");
            manager.addTask("Study for exam");
            manager.addTask("Buy groceries");
            manager.addTask("Read a book");
            System.out.println("Tasks added successfully");
            manager.displayTasks();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Testing Add Empty Task (Invalid) ===");
        try {
            manager.addTask("");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Testing Sort Tasks ===");
        manager.sortTasks();
        System.out.println("Tasks sorted alphabetically:");
        manager.displayTasks();

        System.out.println("\n=== Testing Search Task ===");
        try {
            int index = manager.searchTask("Study for exam");
            if (index != -1) {
                System.out.println("Task found at index: " + index);
            } else {
                System.out.println("Task not found");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Testing Search Non-Existent Task ===");
        int index = manager.searchTask("Non-existent task");
        if (index == -1) {
            System.out.println("Task not found");
        }

        System.out.println("\n=== Testing Find Longest Task ===");
        try {
            String longest = manager.findLongestTask();
            System.out.println("Longest task: " + longest);
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Testing Get Task By Index ===");
        try {
            String task = manager.getTaskByIndex(1);
            System.out.println("Task at index 1: " + task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Testing Get Task By Invalid Index ===");
        try {
            String task = manager.getTaskByIndex(99);
            System.out.println("Task: " + task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Index access attempt completed");
        }

        System.out.println("\n=== Testing Remove Task ===");
        try {
            manager.removeTask("Buy groceries");
            System.out.println("Task removed successfully");
            manager.displayTasks();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Testing Remove Non-Existent Task (Invalid) ===");
        try {
            manager.removeTask("Non-existent task");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Final Task Count ===");
        System.out.println("Total tasks: " + manager.getTaskCount());
    }
}

package Day12;
import java.util.ArrayList;
import java.util.Collections;

public class TaskManager {
    private ArrayList<String> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        if (task == null || task.trim().isEmpty()) {
            throw new IllegalArgumentException("Task cannot be empty");
        }
        tasks.add(task.trim());
    }

    public void sortTasks() {
        Collections.sort(tasks);
    }

    public int searchTask(String task) {
        return tasks.indexOf(task);
    }

    public void removeTask(String task) {
        if (!tasks.remove(task)) {
            throw new IllegalArgumentException("Task not found");
        }
    }

    public String findLongestTask() {
        if (tasks.isEmpty()) {
            throw new IllegalStateException("No tasks available");
        }
        String longest = tasks.get(0);
        for (String task : tasks) {
            if (task.length() > longest.length()) {
                longest = task;
            }
        }
        return longest;
    }

    public String getTaskByIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return tasks.get(index);
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ": " + tasks.get(i));
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }
}

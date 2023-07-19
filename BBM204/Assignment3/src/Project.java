import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Project {
    private final String name;
    private final List<Task> tasks;

    public Project(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    /**
     * Schedule all tasks within this project such that they will be completed as early as possible.
     *
     * @return An integer array consisting of the earliest start days for each task.
     */
    public int[] getEarliestSchedule() {
        // TODO: YOUR CODE HERE
        int[] schedule = new int[tasks.size()];

        int[] sorted = topologicalSort();
        for (int i = 0; i < sorted.length; i++) {
            int max = 0;
            List<Integer> dependecies = tasks.get(sorted[i]).getDependencies();
            for (int j = 0; j < dependecies.size(); j++) {
                if (max < schedule[dependecies.get(j)] + tasks.get(dependecies.get(j)).getDuration()) {
                    max = schedule[dependecies.get(j)] + tasks.get(dependecies.get(j)).getDuration();
                }
            }
            schedule[sorted[i]] = max;
        }
        return schedule;
    }

    class Node {
        Task task;
        List<Node> nextNodes = new ArrayList<>();
        boolean visited = false;

        Node() {}
        Node(Task task) {this.task = task;}
    }
    public int[] topologicalSort() {
        Node[] taskNodes = new Node[tasks.size()];
        for (int i = 0; i < taskNodes.length; i++) {
            taskNodes[i] = new Node();
        }

        for (int i = 0; i < tasks.size(); i++) {
            taskNodes[i].task = tasks.get(i);
            for (int j : tasks.get(i).getDependencies()) {
                taskNodes[j].nextNodes.add(taskNodes[i]);
            }
        }
        Stack<Integer> stack = new Stack<>();
        topologicalRecursion(taskNodes[0], stack);
        int[] toReturn = new int[tasks.size()];
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = stack.pop();
        }
        return toReturn;
    }
    public void topologicalRecursion(Node head, Stack<Integer> stack) {
        for (Node node : head.nextNodes) {
            if (!node.visited)  topologicalRecursion(node, stack);
        }
        stack.push(head.task.getTaskID());
        head.visited = true;
    }

    /**
     * @return the total duration of the project in days
     */
    public int getProjectDuration() {
        int projectDuration = 0;
        // TODO: YOUR CODE HERE
        int[] schedule = getEarliestSchedule();
        projectDuration = tasks.get(schedule.length - 1).getDuration() + schedule[schedule.length - 1];
        return projectDuration;
    }

    public static void printlnDash(int limit, char symbol) {
        for (int i = 0; i < limit; i++) System.out.print(symbol);
        System.out.println();
    }

    public void printSchedule(int[] schedule) {
        int limit = 65;
        char symbol = '-';
        printlnDash(limit, symbol);
        System.out.println(String.format("Project name: %s", name));
        printlnDash(limit, symbol);

        // Print header
        System.out.println(String.format("%-10s%-45s%-7s%-5s", "Task ID", "Description", "Start", "End"));
        printlnDash(limit, symbol);
        for (int i = 0; i < schedule.length; i++) {
            Task t = tasks.get(i);
            System.out.println(String.format("%-10d%-45s%-7d%-5d", i, t.getDescription(), schedule[i], schedule[i] + t.getDuration()));
        }
        printlnDash(limit, symbol);
        System.out.println(String.format("Project will be completed in %d days.", tasks.get(schedule.length - 1).getDuration() + schedule[schedule.length - 1]));
        printlnDash(limit, symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;

        int equal = 0;

        for (Task otherTask : ((Project) o).tasks) {
            if (tasks.stream().anyMatch(t -> t.equals(otherTask))) {
                equal++;
            }
        }

        return name.equals(project.name) && equal == tasks.size();
    }

}

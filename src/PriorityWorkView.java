import java.util.Scanner;

/**
 * MVC-based Implementation for a Priority-based grading system.
 * @implSpec This is the View of the Priority Work program.
 * Hence, this displays the information of the model in a relevant manner to the user.
 * This also receives input from the user.
 * @author Faycal Kilali
 * @version 1.0
 */
public class PriorityWorkView {

    private Scanner scanner;
    private PriorityWorkController controller;

    public PriorityWorkView(){
        controller = new PriorityWorkController();
        scanner = new Scanner(System.in);
    }

    public void introduction(){
        System.out.println("Welcome to a priority-based tasking system developed by Faycal Kilali."
                + "\n" + "This system utilizes a Doubly-Linked List implementation of a Priority Queue under the hood to organize your tasks." +
                "\n" + "Any file called tasks.pri within the same directory as this file is automatically loaded when this method is executed." + "\n" + "You may save and load your tasks at any time for future use.");
        interact();
    }

    /**
     * Recursive method, also allows user to save to file then read it later.
     */
    public void interact(){
        int userInput = 0;
        System.out.println("Current list of work ordered based on due date:" + viewData(controller.retrieveModel()));
        System.out.println("1. Add a task" + "\n" + "2. Remove a task" + "\n" + "3. View highest priority item" + "\n" + "8. load previously saved tasks to file." + "\n" + "9. Save current tasks to file."); // Note the values 8, 9 for load and save to reduce the risk of the user accidentaly loading or saving their data.
        userInput = scanner.nextInt();
        while (controller.processUserInput(userInput) == false){
            userInput = scanner.nextInt();
        }

         // Recurse
        interact();
    }


    /**
     * Retrieves the current log of work. Top-items have the most priority, ascending in priority, so the most important are at the bottom.
     * @param inModel the model we are dealing with
     * @return the log of work in ascending order of priority
     */
    private String viewData(PriorityQueue<PriorityTask> inModel) {
        StringBuilder formattedModel = new StringBuilder();

        // Now we will convert the Priority Queue into an array of PriorityTask[].
        PriorityTask[] tasksArray = inModel.toArray(); // Some more casting

        // We can iterate through an array
        for (PriorityTask task : tasksArray) {

            // The special cases that we are obligated to handle will be dealt with here
            String daysLeftString = getDaysLeftString(task.getValue());
            System.out.println("TASK IS:" + task.getDescription());

            // Append the formatted task to the results
            formattedModel.append(task.getDescription()
                    )
                    .append(", due in: ")
                    .append(daysLeftString)
                    .append("\n");
        }

        // Return the formatted String
        return formattedModel.toString();
    }

    /**
     * Helper method to get the representation of days left based on inDueDays.
     * @param inDueDays the number of days until the task is due
     * @return a string representation of days left
     */
    private String getDaysLeftString(int inDueDays) {
        if (inDueDays == 0) {
            return "Now!!!"; // Assuming this is the format for tasks due today
        } else if (inDueDays < 0) {
            return "I Missed It!"; // Not really a fan of the incorrect spelling that's tasked to us
        } else {
            return inDueDays + " days left";
        }
    }

    public static void main(String[] args) {
        PriorityWorkView priorityWorkView = new PriorityWorkView();
        priorityWorkView.introduction();
    }






}

import java.util.Scanner;
/**
 * MVC-based Implementation for a Priority-based grading system.
 * @implSpec This is the Controller of the Priority Work program.
 * The controller acts as an intermediary between the model and the View.
 * It receives user inputs from the View, processes them, and updates the model accordingly.
 * The controller is also capable of handling the communications between the model and the view.
 * @implNote For example, if the user clicks requests to add a priority work item in View, the controller would process that input, determine that it must instruct the Model to create the priority work item.
 * The mode would create the priority work item, then return a notifcation to the controller whether it was successful or not
 * The controller would then notify the View about the update.
 * @author Faycal Kilali
 * @version 1.0
 */
public class PriorityWorkController {

    private PriorityWorkModel model;

    public PriorityWorkController(){
        model = new PriorityWorkModel();
    }

    /**
     * Processes legality of user input.
     * @param userInput the input of the user
     * @return true if processed successfully, false otherwise.
     */
    public boolean processUserInput(int userInput) {
        switch (userInput) {
            case 1:
                if (addTask() == true){
                    System.out.println("Task added successfully.");
                }
                else{
                    System.err.println("Task list is full! Can't add more tasks.");
                }
                break;
            case 2:
                if( removeTask() == true){
                    System.out.println("Task removed successfully.");
                }
                else{
                    System.err.println("Task list is empty! Can't remove a task that does not exist.");
                }
                break;
            case 3:
                viewTopOnly(retrieveModel());
                break;
            case 8:
                if (loadModel() == true){
                    System.out.println("Model loaded successfully.");
                }
                else{
                    System.err.println("Failed to load Model.");
                }
                break;
            case 9:
                if(saveModel() == true){
                    System.out.println("Model saved successfully.");
                }
                else{
                    System.err.println("Failed to load Model.");
                }
                break;
            default:
                return false; // when none of the cases are true
        }
        return true; // when at least one of the cases are true (excluding default case)
    }


    /**
     * Method to communicate with Model from Controller for adding tasks
     * @implNote This method is actually breaking the MVC. Specifically, it is doing tasks that View should be doing
     * by requesting input from the user. However, due to time constraints, this'll be used primarily for now until a refactorizaiton occurs.
     * @return true if the task was added, false otherwise.
     */
    public boolean addTask() {
        System.out.println("Input Description of task");
        String description = new Scanner(System.in).nextLine();
        System.out.println("Input duration before task is due in days");
        int duration = new Scanner(System.in).nextInt();
        return model.addTask(description, duration);
    }


    /**
     * Method for testing the communication with the Model from the controller for adding tasks
     * @param inDescription the description of the task to add
     * @param inDuration the duration in days of the task to dadd
     * @return true if task was added successfully, false otherwise
     */
    public boolean addTask(String inDescription, int inDuration) {
        return model.addTask(inDescription, inDuration);
    }

    /**
     * Communicates with Model to remove a particular task
     * @return the removed task
     */
    public boolean removeTask() {
        return model.removeTask();
    }


    /**
     * Retrieves the highest priority work only
     * @implSpec this method breaks MVC, it should not display a String. Again, time constraints.
     * @param inModel the model we are dealing with
     * @return the most important work
     */
    private void viewTopOnly(PriorityQueue<PriorityTask> inModel) {


        StringBuilder formattedModel = new StringBuilder();

        PriorityTask task = null;
        try {
            task = inModel.peek();
        } catch (PriQueueUnderflowException e) {
            throw new RuntimeException(e);
        }

        // The special cases that we are obligated to handle will be dealt with here
        String daysLeftString = "";
        int inDueDays = task.getValue();
        if (inDueDays == 0) {
            daysLeftString = ( "Now!!!"); // Assuming this is the format for tasks due today
        } else if (inDueDays < 0) {
            daysLeftString = ( "I Missed It!"); // Not really a fan of the incorrect spelling that's tasked to us
        } else {
            daysLeftString = ( inDueDays + " days left");
        }

        // Append the formatted task to the results
        formattedModel.append(task.getDescription()
                )
                .append(", due in: ")
                .append(daysLeftString)
                .append("\n");


        System.out.println(formattedModel.toString());
    }

    /**
     * Communicate with the Model to load the Model in storage.
     * @implNote Incomplete feature due to time constraints
     * @return true if model was laoded correctly, false otherwise
     */

    public boolean loadModel() {
        System.out.println("Input absolute path of model to load, or leave blank to load tasks.pri in same directory");
        String modelName = new Scanner(System.in).nextLine();
        if (modelName == ""){
            return model.loadModel("tasks.pri");
        }
        else{
            return model.loadModel(modelName);
        }
    }

    /**
     * Communicate with the Model to save a copy of the Model in storage.
     * @implNote incomplete feature due to time constraints
     * @return true if the Model was saved successfully, false otherwise.
     */
    public boolean saveModel() {
        System.out.println("Input name of model to save in same directory, or leave blank to save as tasks.pri in same directory");
        String modelName = new Scanner(System.in).nextLine();
        if (modelName == ""){
            return model.saveModel("tasks.pri");
        }
        else{
            return model.saveModel(modelName);
        }
    }

    /**
     * Retrieves the model and passes it on
     * @implSpec functions as a bridge between view and model for representation purposes
     * @return the model
     */
    public PriorityQueue<PriorityTask> retrieveModel() {
        return model.retrieveModel();
    }




}

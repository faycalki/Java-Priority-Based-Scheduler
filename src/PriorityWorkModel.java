import java.io.File;

/**
 * MVC-based Implementation for a Priority-based grading system.
 * @implSpec This is the Model of the Priority Work program.
 * Hence, this handles the data logic, and stores the data.
 * @implNote Due to time constraints, loadModel and saveModel have not been implemented.
 * @author Faycal Kilali
 * @version 1.0
 */
public class PriorityWorkModel {

    protected File file;
    private PriorityQueue<PriorityTask> queue;

    public PriorityWorkModel(){
        File file = new File("tasks.pri");
        queue = new PriorityQueue<PriorityTask>();
    }

    /**
     * Adds a task at the appropriate position
     * @param inDescription the description of the task
     * @param inDueDays the duration of the task until its due
     * @return true if successful, false otherwise
     */
    public boolean addTask(String inDescription, int inDueDays){
        PriorityTask addPriority = new PriorityTask(inDescription, inDueDays);
        try {
            queue.enqueue(addPriority);
        } catch (PriQueueOverflowException e) {

            return false;
        }
        return true; // communicate with controller
    }


    /**
     * Removes the least important task
     * @return true if successfully removed, false otherwise
     */
    public boolean removeTask(){
        try {
            // For now, this only removes the least important task
            queue.dequeue();

            // In the future, maybe we can remove a particular task
            // queue.list.removeFirstInstance(queue.list.traverseLinkedList()); Something along those lines
        } catch (PriQueueUnderflowException e) {

            return false;
        }
        return true;
    }

    /**
     * Loads the model
     * @param inNameOfFile name of model to load
     * @return true if successful, false otherwise
     */
    public boolean loadModel(String inNameOfFile){
        return true;
    }

    /**
     * Saves the model
     * @param inNameOfFile name to save Mode as
     * @return true if successful, false otherwise
     */
    public boolean saveModel(String inNameOfFile){
        return true;
    }


    /**
     * Accessor method for the model's data
     * @return the model's data
     */
    public PriorityQueue<PriorityTask> retrieveModel(){
        return queue;
    }


}

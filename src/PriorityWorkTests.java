import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PriorityWorkTests {

    @Test
    public void testAddTask() {
        PriorityWorkModel model = new PriorityWorkModel();
        assertTrue(model.addTask("Task 1", 3));
        assertFalse(model.addTask("Task 2", 1)); // Assuming the queue capacity is 1
    }

    @Test
    public void testRemoveTask() {
        PriorityWorkModel model = new PriorityWorkModel();
        assertFalse(model.removeTask()); // Removing from an empty queue
        model.addTask("Task 1", 3);
        assertTrue(model.removeTask());
    }

    @Test
    public void testLoadAndSaveModel() {
        PriorityWorkModel model = new PriorityWorkModel();
        model.addTask("Task 1", 3);
        model.addTask("Task 2", 2);

        assertTrue(model.saveModel("test_model.pri"));

        // Creating a new model to simulate loading
        PriorityWorkModel loadedModel = new PriorityWorkModel();
        assertTrue(loadedModel.loadModel("test_model.pri"));

        // Checking if the loaded model has the same tasks
        assertEquals(model.retrieveModel(), loadedModel.retrieveModel());
    }

    @Test
    public void testController() {
        PriorityWorkController controller = new PriorityWorkController();

        // Assuming the model has a capacity of at least 2
        assertTrue(controller.addTask("Task 1", 3));
        assertTrue(controller.addTask("Task 2", 2));

        // Assuming the model has at least 2 tasks
        assertTrue(controller.removeTask());
    }
}

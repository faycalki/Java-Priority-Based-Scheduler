
/**
 * Ordering based on Integers
 * @author Faycal Kilali
 * @version Oct 20, 2023
 */
public class PriorityTask implements Priority<PriorityTask>
{

    private Integer value;
    private String description = "";

    public PriorityTask(String inDescription, Integer inValue){
        value = value.valueOf(inValue);
        description = inDescription;
    }

    /**
     * Comparison of value as Integers
     * @param other
     * @return true if our int has a higher value, false otherwise.
     */

    public boolean isMoreImportant(PriorityTask other){
        if (getValue() > other.getValue()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Obtain value of int
     * @return
     */
    public int getValue(){
        return value;
    }

    public String getDescription(){
        return description;
    }


    @Override
    public String toString() {
        return description + ", " + String.valueOf(value);
    }



}

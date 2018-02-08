package directedstudy.georgiacentral.Objects;


public class Condition {

    private int conditionID;
    private String condition;

    public Condition(int conditionID, String condition) {
        this.conditionID    = conditionID;
        this.condition      = condition;
    }//Condition

    public int getConditionID() {
        return conditionID;
    }//getConditionID

    public void setConditionID(int conditionID) {
        this.conditionID = conditionID;
    }//setConditionID

    public String getCondition() {
        return condition;
    }//getCondition

    public void setCondition(String condition) {
        this.condition = condition;
    }//setCondition

}//class

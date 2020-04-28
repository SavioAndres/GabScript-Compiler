package compiler;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author savio
 */
public class Functions {
    private Stack<Elements> parameter;
    private ArrayList<ExecuteAction> listActions;

    public Functions(Stack<Elements> parameter, ArrayList<ExecuteAction> listActions) {
        this.parameter = parameter;
        this.listActions = listActions;
    }

    public Functions(ArrayList<ExecuteAction> listActions) {
        this.listActions = listActions;
    }

    
    
    public Stack<Elements> getParameter() {
        return parameter;
    }

    public void setParameter(Stack<Elements> parameter) {
        this.parameter = parameter;
    }

    public ArrayList<ExecuteAction> getListActions() {
        return listActions;
    }

    public void setListActions(ArrayList<ExecuteAction> listActions) {
        this.listActions = listActions;
    }

    
    
    
}

package compiler;

import java.util.ArrayList;
import static main.Main.function;

/**
 *
 * @author savio
 */
public class FirstSemanticPass implements Constants {

    private ArrayList<ExecuteAction> listActions;
    private boolean detectedFunction;
    private String funcId;

    public FirstSemanticPass() {
        detectedFunction = false;
    }
    
    public void executeAction(int action, Token token) {
        if (action == 37) {
            detectedFunction = true;
            funcId = token.getLexeme();
            listActions = new ArrayList<>();
        }
        if (action == 63) {
            detectedFunction = false;
            function.put(funcId, new Functions(listActions));
        }
        if (detectedFunction && action != 37) {
            listActions.add(new ExecuteAction(action, token));
        }
    }
    
}

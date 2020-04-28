package compiler;

/**
 *
 * @author savio
 */
public class ExecuteAction {
    private int action;
    private Token token;

    public ExecuteAction(int action, Token token) {
        this.action = action;
        this.token = token;
    }
    
    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
    
}

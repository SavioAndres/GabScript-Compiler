package compiler;

/**
 *
 * @author savio
 */
public class Variables {
    private String variable;
    private Elements element;
    private int scopeLevel;

    public Variables(String variable, Elements element, int scopeLevel) {
        this.variable = variable;
        this.element = element;
        this.scopeLevel = scopeLevel;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Elements getElement() {
        return element;
    }

    public void setElement(Elements element) {
        this.element = element;
    }

    public int getScopeLevel() {
        return scopeLevel;
    }

    public void setScopeLevel(int scopeLevel) {
        this.scopeLevel = scopeLevel;
    }
    
}

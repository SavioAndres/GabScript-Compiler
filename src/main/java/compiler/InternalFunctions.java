package compiler;

import static compiler.Types.*;
import structures.ArrayVector;

/**
 *
 * @author savio
 */
public class InternalFunctions {

    private Elements element;
    private ArrayVector vector;

    public InternalFunctions(int index, Elements element) throws SemanticError {
        this.element = element;
        vector = new ArrayVector();
        
        if (index >= 0 && index <= 10) {
            math(index);
        }
        
    }
    
    public ArrayVector answer() {
        return vector;
    }

    private void math(int index) throws SemanticError {
        switch (index) {
            case 0:
                if (element.getType() == INT) {
                    vector.add(new Elements(INT, fact(Integer.parseInt(element.getValue().toString()))));
                    vector.add(new Elements(INT, 2 + Integer.parseInt(element.getValue().toString())));
                } else {
                    throw new SemanticError("O valor não é númerico");
                }
                break;
            case 1:
                vector.add(new Elements(INT, 32));
                break;
        }
    }
    
    private int fact(int a) {
        if (a == 0) {
            return 1;
        }
        return a * fact(a - 1);
    }
    
}

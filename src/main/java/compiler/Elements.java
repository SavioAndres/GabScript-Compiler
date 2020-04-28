package compiler;

/**
 *
 * @author Sávio Andres
 * @param <E>
 */
public class Elements <E> {
    private Types type;
    private E value;

    public Elements(Types type, E value) {
        this.type = type;
        this.value = value;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    
}

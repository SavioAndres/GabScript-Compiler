package structures;

import compiler.Elements;
import compiler.SemanticError;

/**
 *
 * @author Sávio Andres
 */
public class ArrayVector {
    private Elements[][] elements;
    private int sizeX, sizeY, capacityX, capacityY;

    public ArrayVector() {
        this.capacityX = 16;
        this.capacityY = 1;
        this.sizeX = 0;
        this.sizeY = 0;
        this.elements = new Elements[capacityX][capacityY];
    }
    
    public void add(Elements element) {
        capacity();
        elements[sizeX][0] = element;
        sizeX++;
    }
    
    public void addM(Elements element) {
        capacity();
        elements[sizeX][0] = element;
        sizeX++;
    }
    
    public void add(int position, Elements element) throws SemanticError {
        capacity();
        if (position < 0 && (sizeX + position) >= 0) {
            //throw new SemanticError("Não é permitido posição negativa");
            position = sizeX + position;
        } else if (position > sizeX) {
            throw new SemanticError("A posição é maior que o tamanho do vetor");
        }
        for (int i = sizeX; i > position; i--) {
            elements[i] = elements[i - 1];
        }
        elements[position][0] = element;
        sizeX++;
    }
    
    public Elements get(int position) throws SemanticError {
        if (position >= 0 && position < sizeX) {
            return elements[position][0];
        } else if (position < 0 && (sizeX + position) >= 0) {
            return elements[sizeX + position][0];
        } else {
            if (position > sizeX) {
                throw new SemanticError("Posição inválida. A posição informada é maior que o vetor, as posições desse vetor começam em 0 e vão até " + (sizeX - 1) + ". O tamanho do vetor é " + sizeX);
            } else if (position == sizeX) {
                throw new SemanticError("Posição inválida. A posição informada é do tamanho do vetor, as posições desse vetor começam em 0 e vão até " + (sizeX - 1));
            } else {
                throw new SemanticError("Posição inválida. Não é aceito uma posição negativa, a menor posição de um vetor é 0");
            }
        }
    }
    
    public Elements getM(int positionX, int positionY) throws SemanticError {
        if (positionX >= 0 && positionX < sizeX && positionY >= 0 && positionY < sizeY) {
            return elements[positionX][positionY];
        } else {
            if (positionX > sizeX) {
                throw new SemanticError("Posição inválida. A posição informada é maior que o vetor, as posições desse vetor começam em 0 e vão até " + (sizeX - 1) + ". O tamanho do vetor é " + sizeX);
            } else if (positionX == sizeX) {
                throw new SemanticError("Posição inválida. A posição informada é do tamanho do vetor, as posições desse vetor começam em 0 e vão até " + (sizeX - 1));
            } else {
                throw new SemanticError("Posição inválida. Não é aceito uma posição negativa, a menor posição de um vetor é 0");
            }
        }
    }
    
    public int size() {
        return sizeX;
    }
    
    public void remove(int position) throws SemanticError {
        if (position >= 0 && position < sizeX) {
            for (int i = position; i < sizeX - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[sizeX - 1] = null;
            sizeX--;
        } else {
            if (position >= sizeX) {
                throw new SemanticError("Posição inválida. A posição informada é maior que o vetor, as posições desse vetor começam em 0 e vai até " + (sizeX - 1));
            } else {
                throw new SemanticError("Posição inválida. Não é aceito uma posição negativa, a menor posição de um vetor é 0");
            }
        }
    }
    
    public boolean empity() {
        return sizeX == 0;
    }
    
    public void clear() {
        sizeX = 0;
        capacityX = 16;
        elements = null;
    }
    
    private void capacity() {
        if (capacityX == sizeX) {
            capacityX = capacityX + 16;
            Elements[][] newArray = new Elements[capacityX][capacityY];
            System.arraycopy(elements, 0, newArray, 0, sizeX);
            elements = newArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringConc = new StringBuilder();
        for (int i = 0; i < sizeX; i++) {
            stringConc.append(elements[i][0].getValue()).append(", ");
        }
        if (sizeX == 0) {
            return "[]";
        }
        return "[" + stringConc.toString().substring(0, stringConc.toString().length() - 2) + "]";
    }
    
}

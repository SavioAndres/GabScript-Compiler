package structures;

import compiler.Elements;

/**
 *
 * @author Sávio Andres
 */
public class ArrayElements {
    private Elements[][] elements;
    private int sizeX, sizeY, capacityX, capacityY;
    
    public ArrayElements() {
        this.capacityX = 10;
        this.capacityY = 1;
        this.sizeX = 0;
        this.sizeY = 0;
        this.elements = new Elements[capacityX][capacityY];
    }
    
    public void add(int positionX, int positionY, Elements data) {
        capacityX();
        capacityY();
        explodeXPosition(positionX);
        explodeYPosition(positionY);
        if (elements[positionX][positionY] == null) {
            sizeX++;
            sizeY++;
        }
        elements[positionX][positionY] = data;
    }
    
    public void addX(int position, Elements data) {
        capacityX();
        explodeXPosition(position);
        if (elements[position][sizeY] == null) 
            sizeX++;
        elements[position][sizeY] = data;
    }
    
    public void addX(Elements data) {
        capacityX();
        elements[sizeX][sizeY] = data;
        sizeX++;
    }
    
    public void addY(int position, Elements data) {
        capacityY();
        explodeYPosition(position);
        if (elements[sizeX][position] == null)
            sizeY++;
        elements[sizeX][position] = data;
    }
    
    public void addY(Elements data) {
        capacityY();
        elements[sizeX][sizeY] = data;
        sizeY++;
    }
    
    public Elements get(int x, int y) {
        return elements[x][y];
    }
    
    public Elements getX(int x) {
        return elements[x][0];
    }
    
    public Elements getY(int y) {
        return elements[0][y];
    }
    
    private void capacityX() {
        if (sizeX == capacityX) {
            capacityX = capacityX + 10;
            Elements[][] newArray = new Elements[capacityX][capacityY];
            for (int i = 0; i < sizeX; i++) {
                System.arraycopy(elements[i], 0, newArray[i], 0, sizeY);
            }
            elements = newArray;
        }
    }
    
    private void capacityY() {
        if (sizeY == capacityY) {
            capacityY = capacityY + 10;
            Elements[][] newArray = new Elements[capacityX][capacityY];
            for (int i = 0; i < sizeX; i++) {
                System.arraycopy(elements[i], 0, newArray[i], 0, sizeY);
            }
            elements = newArray;
        }
    }
    
    public int sizeX() {
        return sizeX;
    }
    
    public int sizeY() {
        return sizeY;
    }
    
    public int sizeAll() {
        return sizeX * sizeY;
    }
    
    private void explodeXPosition(int position) {
        if (position >= capacityX) {
            capacityX = position + 1;
            Elements[][] newArray = new Elements[capacityX][capacityY];
            for (int i = 0; i < sizeX; i++) {
                System.arraycopy(elements[i], 0, newArray[i], 0, sizeY);
            }
            elements = newArray;
        }
    }
    
    private void explodeYPosition(int position) {
        if (position >= capacityY) {
            capacityY = position + 1;
            Elements[][] newArray = new Elements[capacityX][capacityY];
            for (int i = 0; i < sizeX; i++) {
                System.arraycopy(elements[i], 0, newArray[i], 0, sizeY);
            }
            elements = newArray;
        }
    }
    
}

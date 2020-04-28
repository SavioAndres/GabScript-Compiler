/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author savio
 */
public class For {
    private For node;
    private LinkedList<ExecuteAction> execution;
    private int a, b;

    public For(For node, LinkedList<ExecuteAction> execution, int a, int b) {
        this.node = node;
        this.execution = execution;
        this.a = a;
        this.b = b;
    }

    public For getNode() {
        return node;
    }

    public void setNode(For node) {
        this.node = node;
    }

    public LinkedList<ExecuteAction> getExecution() {
        return execution;
    }
    
    public void setExecution(LinkedList<ExecuteAction> execution) {
        this.execution = execution;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
    public Iterator iterator() {
        return new IteradorList();
    }

    private class IteradorList implements Iterator {

        private For next;

        public IteradorList() {
            this.next = node;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public For next() {
            For element = this.next;
            this.next = this.next.getNode();
            return element;
        }

    }
    
}

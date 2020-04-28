/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author savio
 */
public class If {
    private int scope;
    private boolean bool;

    public If(int scope, boolean bool) {
        this.scope = scope;
        this.bool = bool;
    }

    public int getScope() {
        return scope;
    }

    public boolean isBool() {
        return bool;
    }

    @Override
    public String toString() {
        return "If{" + "scope=" + scope + ", bool=" + bool + '}';
    }
    
    
    
}

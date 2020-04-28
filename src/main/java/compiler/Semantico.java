package compiler;

import static compiler.Types.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import static main.Main.*;
import structures.ArrayMatrix;
import structures.ArrayVector;

public class Semantico implements Constants {

    private final Stack<Elements> stack, parameter, functionParameter;
    private final Queue<Elements> addDataVector;
    private final Stack<String> logicOp;
    private final Queue<String> vectorVariable;
    private LinkedList<ExecuteAction> forListActions;
    private ArrayVector vectorGet;
    private final Stack<Boolean> scopesIf, scopesElse;
    private final ArrayList<ExecuteAction> listActionsFunction, listActionsWhile;
    //private final HashMap<String, Variables> id;
    //private final HashMap<String, Functions> function;
    //private final HashMap<String, Queue<ExecuteAction>> _listsActions;
    //private final HashMap<String, ArrayVector> array;
    private final StringBuilder stringConc;
    private String variable, _const, variablePointing, functionVariable, idFunc, string, input;
    private boolean bool, print, analyze, analyzeWhile, analyzerMemory, onWhile;
    private boolean execWhile;
    private int scopeLevel, positionVector, positionMatrixX, positionMatrixY;
    private Token token22;

    public Semantico() {
        stack = new Stack<>();
        parameter = new Stack<>();
        functionParameter = new Stack<>();
        addDataVector = new LinkedList<>();
        logicOp = new Stack<>();
        vectorVariable = new LinkedList<>();
        forListActions = new LinkedList<>();
        scopesIf = new Stack<>();
        scopesElse = new Stack<>();
        //forActions = new LinkedList<>();
        listActionsFunction = new ArrayList<>();
        listActionsWhile = new ArrayList<>();
        //id = new HashMap<>();
        //function = new HashMap<>();
        //_listsActions = new HashMap<>();
        //array = new HashMap<>();
        stringConc = new StringBuilder();
        analyze = true;
        analyzeWhile = true;
        analyzerMemory = true;
        onWhile = false;
        scopeLevel = 0;
        execWhile = true;
        onFor = false;
        outroFor = false;

    }

    public void executeAction(int action, Token token) throws SemanticError, FileNotFoundException, LexicalError, SyntaticError, IOException {
        //System.out.println("id: " + id + " - level: " + scopeLevel);
        //if (id.containsKey("r"))
        //    System.out.println(" - " + id.get("r").getElement().getValue());
        //System.out.println(variablePointing);
        //System.out.println("Action: " + action + " - " + activateFunctions);
        //System.out.println(function);
        //System.out.println(Arrays.toString(stack.toArray()));
        //System.out.println("Action: " + action);
        /*if (!listActionsWhile.isEmpty()) {
            for (int i = 0; i < listActionsWhile.size(); i++) {
                System.out.print(listActionsWhile.get(i).getAction() + ", ");
            }
            System.out.println("");
        }
         */
        if (onFor) {
            //stackFors.peek().add(new ExecuteAction(action, token));
            forListActions.add(new ExecuteAction(action, token));
        }

        if (analyzeWhile && onWhile && execWhile) {
            listActionsWhile.add(new ExecuteAction(action, token));
        }

        if (analyze) {
            if (action >= 0 && action <= 10) {
                primaryTerminals(action, token);
            } else if (action >= 11 && action <= 16) {
                dataVectorMatriz(action, token);
            } else if (action >= 17 && action <= 18) {
                _for(action, token);
            } else if (action >= 20 && action <= 25) {
                commands(action, token);
            } else if (action >= 26 && action <= 27) {
                increment(action, token);
            } else if (action == 28) {
                //functionParameter(token);
            } else if (action >= 29 && action <= 34) {
                vector(action, token);
            } else if (action >= 35 && action <= 36) {
                functionGet(action, token);
            } else if (action >= 38 && action <= 43) {
                calculation(action, token);
            } else if (action >= 44 && action <= 45) {
                print(action, token);
            } else if (action >= 46 && action <= 51) {
                stackTypes(action, token);
            } else if (action >= 52 && action <= 53) {
                elementVecFunc(action, token);
            } else if (action >= 54 && action <= 56) {
                conferLogical(action, token);
            } else if (action >= 57 && action <= 58) {
                input(action, token);
            } else if (action >= 59 && action <= 60 || action == 82) {
                concatenation(action, token);
            } else if (action >= 73 && action <= 78) {
                vectorAdd(action, token);
            } else if (action == 80) {
                numericSignal(token);
            } else if (action == 81) {
                constants(token);
            }
        }
        //Desbloqueadores do analyze
        if (action == 19) {
            _for(action, token);
        }

        if (action == 37 || (action >= 61 && action <= 63)) {
            function(action, token);
        } else if (action >= 64 && action <= 69 || action == 79) {
            _if(action, token);
        } else if (action >= 70 && action <= 72) {
            _while(action, token);
        }
        //System.out.println("ação : "+action  + " - Analize: " + analyze + " - token: " + token.getLexeme());
        //if(!stack.empty()) {
        //    System.out.println("Pilha: " + stack.peek().getValue());
        //}

    }

    private void primaryTerminals(int action, Token token) {
        switch (action) {
            case 0:
                variable = token.getLexeme();
                break;
            case 1:
                print = true;
                break;
            case 2:
                print = false;
                break;
            case 3:
                bool = true;
                break;
            case 4:
                bool = false;
                break;
            case 5:
                stack.push(new Elements(INT, token.getLexeme()));
                break;
            case 6:
                stack.push(new Elements(FLOAT, token.getLexeme()));
                break;
            case 7:
                //stack.push(new Elements(FLOAT, Math.PI));
                _const = token.getLexeme();
                break;
            case 8:
                //stack.push(new Elements(FLOAT, Math.E));
                stack.push(id.get(token.getLexeme()).getElement());
                break;
            case 9:
                String a = token.getLexeme().substring(1);
                a = a.substring(0, a.length() - 1);
                string = a;
                break;
            case 10:
                logicOp.push(token.getLexeme());
                break;
        }
    }

    private void dataVectorMatriz(int action, Token token) {
        switch (action) {
            case 11:
                addDataVector.add(stack.pop());
                break;
            case 12:
                stringConc.append(array.get(vectorVariable.poll()).toString());
                break;
            case 13:
                functionParameter.push(new Elements(ID, variable));
                break;
            case 14:
                functionParameter.push(new Elements(VECTOR, vectorVariable.poll()));
                break;
            case 15:
                //functionParameter.push(new Elements(MATRIX, token.getLexeme()));
                break;
            case 16:
                break;
        }
    }

    private String variableFor;
    private int forInt1, forInt2;
    private boolean onFor, outroFor;
    private For forAction;
    private LinkedList<ExecuteAction> forListActionsAux = new LinkedList<>();
    private Queue<For> stackFors = new LinkedList<>();
    //private Stack<LinkedList<ExecuteAction>> StackFors = new Stack<>();

    private void _for(int action, Token token) throws SemanticError, LexicalError, SyntaticError, IOException {
        //System.out.println("Action: " + action);
        //System.out.println(id);
        switch (action) {
            case 17:
                variableFor = token.getLexeme();
                //forListActionsAux.addAll(forListActions);
                //forListActions = new LinkedList<>();
                break;
            case 18:
                scopeLevel++;
                Elements element2 = stack.pop();
                Elements element1 = stack.pop();
                if (element1.getType() != INT || element2.getType() != INT) {
                    //apresente erro
                }
                if (!id.containsKey(variableFor)) {
                    id.put(variableFor, new Variables(variableFor, element1, scopeLevel));
                } else {
                    //apresente erro
                }
                forInt1 = Integer.parseInt(element1.getValue().toString());
                forInt2 = Integer.parseInt(element2.getValue().toString());
                //forAction.setA(forInt1);
                //forAction.setB(forInt2);
                onFor = true;
                analyze = false;
                break;
            case 19:
                forListActions.removeLast();
                forAction = new For(forAction, forListActions, forInt1, forInt2);
                onFor = false;
                analyze = true;
                int a, b;
                /*if (forAction.getA() < forAction.getB()) {
                    a = forAction.getA();
                    b = forAction.getB();
                } else {
                    b = forAction.getA();
                    a = forAction.getB();
                }*/
                //LinkedList<ExecuteAction> fActions = stackFors.pop();
                //fActions.removeLast();
                //forActions.removeLast();
                for (Iterator<For> iterator = forAction.iterator(); iterator.hasNext();) {
                    
                        System.out.println("eeeeeeeeee");
                        For nextFor = iterator.next();
                        //nextFor.getExecution().removeLast();
                        if (nextFor.getA() < nextFor.getB()) {
                            a = nextFor.getA();
                            b = nextFor.getB();
                        } else {
                            b = nextFor.getA();
                            a = nextFor.getB();
                        }
                        for (int i = a; i < b; i++) {
                            for (ExecuteAction executeAction : nextFor.getExecution()) {
                                executeAction(executeAction.getAction(), executeAction.getToken());
                            }
                            if (nextFor.getA() < nextFor.getB()) {
                                id.get(variableFor).getElement().setValue(Integer.parseInt(id.get(variableFor).getElement().getValue().toString()) + 1);
                            } else {
                                id.get(variableFor).getElement().setValue(Integer.parseInt(id.get(variableFor).getElement().getValue().toString()) - 1);
                            }
                        }
                    }
        
                /*
                for (int i = a; i < b; i++) {
                    
                    
                    
                    for (ExecuteAction forAction2 : fActions) {
                        executeAction(forAction2.getAction(), forAction2.getToken());
                    }

                    if (forInt1 < forInt2) {
                        id.get(variableFor).getElement().setValue(Integer.parseInt(id.get(variableFor).getElement().getValue().toString()) + 1);
                    } else {
                        id.get(variableFor).getElement().setValue(Integer.parseInt(id.get(variableFor).getElement().getValue().toString()) - 1);
                    }

                }*/
                //forListActions.clear();
                removeScopeVariables(scopeLevel);
                break;
        }
    }

    private void commands(int action, Token token) throws LexicalError, SyntaticError, FileNotFoundException, SemanticError, MalformedURLException, IOException {
        String path;
        BufferedReader p1_reader, p2_reader;
        Lexico p1_lex, p2_lex;
        FirstSyntacticPass p1_sym;
        Sintatico p2_sym;
        FirstSemanticPass p1_sem;
        Semantico p2_sem;
        switch (action) {
            case 20:
                string = string.toLowerCase();
                try {
                    if (!string.contains(".txt")) {
                        string = string + ".txt";
                    }
                    path = getClass().getResource("../lib/" + string).getFile();
                } catch (Exception e) {
                    System.err.println("Você não tem a biblioteca \"" + string + "\", portanto estou procurando-a e possivelmente fazendo download");
                    URL url = new URL("https://tensionless-spool.000webhostapp.com/wp-content/uploads/2019/07/" + string);
                    System.err.println("Download...");
                    File file = new File(getClass().getResource("../lib/").getFile() + string);
                    try (ReadableByteChannel rbc = Channels.newChannel(url.openStream()); FileOutputStream fos = new FileOutputStream(file)) {
                        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                        path = getClass().getResource("../lib/" + string).getFile();
                        System.err.println("Download concluído com sucesso");
                    } catch (FileNotFoundException h) {
                        throw new SemanticError("Erro na importação. A biblioteca \"" + string + "\" não existe", token.getPosition());
                    } catch (UnknownHostException h) {
                        throw new SemanticError("Erro na importação. A biblioteca \"" + string + "\" não pode ser baixada porque você não está conectado a internet ou o servidor não está disponível", token.getPosition());
                    } catch (Exception h) {
                        throw new SemanticError("Erro na importação. A biblioteca \"" + string + "\" não pode ser baixada por não existe ou por você não está conectado a internet ou ainda por problemas no servidor", token.getPosition());
                    }
                }
                //reader = new FileReader(path);
                p1_reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "latin1"));
                p2_reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "latin1"));
                p1_lex = new Lexico(p1_reader);
                p2_lex = new Lexico(p2_reader);
                p1_sym = new FirstSyntacticPass();
                p2_sym = new Sintatico();
                p1_sem = new FirstSemanticPass();
                p2_sem = new Semantico();
                try {
                    p1_sym.parse(p1_lex, p1_sem);
                    p2_sym.parse(p2_lex, p2_sem);
                } catch (LexicalError | SyntaticError | SemanticError e) {
                    analyze = false;
                    //System.err.println("Biblioteca \"" + string + "\" erro: " + e.getMessage());
                    throw new SemanticError("Biblioteca \"" + string + "\" erro: " + e.toString(), token.getPosition());
                    //e.printStackTrace();
                }
                break;
            case 21:
                string = string.toLowerCase();
                if (!string.contains(".txt")) {
                    string = string + ".txt";
                }
                File reader1 = new File(string);
                File reader2 = new File(pathMain);
                File reader3 = new File(reader2.getParent() + "\\" + string);
                if (reader3.exists()) {
                    path = reader3.getPath();
                } else if (reader1.exists()) {
                    path = reader1.getPath();
                } else {
                    throw new SemanticError("Erro na inclusão do arquivo \"" + string + "\", o caminho está incorreto", token.getPosition());
                }
                p1_reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "latin1"));
                p2_reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "latin1"));
                p1_lex = new Lexico(p1_reader);
                p2_lex = new Lexico(p2_reader);
                p1_sym = new FirstSyntacticPass();
                p2_sym = new Sintatico();
                p1_sem = new FirstSemanticPass();
                p2_sem = new Semantico();
                try {
                    p1_sym.parse(p1_lex, p1_sem);
                    p2_sym.parse(p2_lex, p2_sem);
                } catch (LexicalError | SyntaticError | SemanticError e) {
                    analyze = false;
                    //System.err.println("Arquivo \"" + string + "\" erro: " + e.getMessage());
                    throw new SemanticError("Arquivo \"" + string + "\" erro: " + e.getMessage(), token.getPosition());
                }
                break;
            case 22:
                //System.out.println("vv: " + variable + " - pont: " + variablePointing);
                token22 = token;
                if (id.containsKey(variablePointing)) {
                    id.get(variablePointing).setElement(stack.pop());
                } else {
                    id.put(variablePointing, new Variables(variablePointing, stack.pop(), scopeLevel));
                }
                break;
            case 23:
                //id.put(variable, new Variables(variable, new Elements(getType(input), input), scopeLevel));
                stack.push(new Elements(getType(input), input));
                break;
            case 24:
                Elements value = stack.pop();
                int index = Integer.parseInt(stack.pop().getValue().toString());
                String vecVal = vectorVariable.poll();
                if (!vectorVariable.isEmpty()) {
                    vecVal = vectorVariable.poll();
                }
                InternalFunctions inFunc = new InternalFunctions(index, value);
                array.put(vecVal, inFunc.answer());
                //No final da lib tenho que destruir o vetor
                break;
            case 25:
                double versionFile = Double.parseDouble(token.getLexeme());
                if (versionFile > version) {
                    throw new SemanticError("A versão desse arquivo é superior ao compilador, por favor faça o download da nova versão. Arquivo declarado com versão: " + versionFile + ". Versão do seu compilador: " + version, token.getPosition());
                }
                break;
        }
    }

    private void increment(int action, Token token) throws SemanticError {
        if (id.containsKey(variable)) {
            Variables val = id.get(variable);
            switch (action) {
                case 26:
                    switch (val.getElement().getType()) {
                        case INT: {
                            int value = Integer.parseInt(val.getElement().getValue().toString());
                            value++;
                            val.getElement().setValue(value);
                            break;
                        }
                        case FLOAT: {
                            double value = Double.parseDouble(val.getElement().getValue().toString());
                            value++;
                            val.getElement().setValue(value);
                            break;
                        }
                        default:
                            throw new SemanticError("A variável " + variable + " não é do tipo numérica para ser acrescentada", token.getPosition());
                    }
                    break;
                case 27:
                    switch (val.getElement().getType()) {
                        case INT: {
                            int value = Integer.parseInt(val.getElement().getValue().toString());
                            value--;
                            val.getElement().setValue(value);
                            break;
                        }
                        case FLOAT: {
                            double value = Double.parseDouble(val.getElement().getValue().toString());
                            value--;
                            val.getElement().setValue(value);
                            break;
                        }
                        default:
                            throw new SemanticError("A variável " + variable + " não é do tipo numérica para ser reduzida", token.getPosition());
                    }
                    break;
            }
        } else {
            throw new SemanticError("A variável " + variable + " não foi declarada", token.getPosition());
            //id.put(variable, new Variables(variable, new Elements(INT, 0), scopeLevel));
        }
    }

    /*private void functionParameter(Token token) {
        functionParameter.push(new Elements(ID, token.getLexeme()));
    }*/
    private void vector(int action, Token token) throws SemanticError {
        switch (action) {
            case 29:
                String vecVal = vectorVariable.peek();
                if (!array.containsKey(vecVal)) {
                    array.put(vecVal, new ArrayVector());
                }
                break;
            case 30:
                positionVector = Integer.parseInt(stack.pop().getValue().toString());
                if (array.containsKey(vectorVariable.peek())) {
                    try {
                        stack.push(array.get(vectorVariable.peek()).get(positionVector));
                    } catch (SemanticError e) {
                        throw new SemanticError(e.toString(), token.getPosition());
                    }
                } else {
                    throw new SemanticError("O vetor \"" + vectorVariable.peek() + "\" não contem nada, pois ainda não foi iniciado", token.getPosition());
                }
                break;
            case 31:
                if (!array.containsKey(vectorVariable.peek())) {
                    array.put(vectorVariable.peek(), new ArrayVector());
                }
                break;
            case 32:
                positionMatrixY = Integer.parseInt(stack.pop().getValue().toString());
                positionMatrixX = Integer.parseInt(stack.pop().getValue().toString());
                if (array.containsKey(vectorVariable.peek())) {
                    try {
                        stack.push(array.get(vectorVariable.peek()).getM(positionMatrixX, positionMatrixY));
                    } catch (SemanticError e) {
                        throw new SemanticError(e.toString(), token.getPosition());
                    }
                } else {
                    throw new SemanticError("O vetor \"" + vectorVariable.peek() + "\" não contem nada, pois ainda não foi iniciado", token.getPosition());
                }
                break;
            case 33:
                break;
            case 34:
                break;
        }
    }

    private void functionGet(int action, Token token) throws SemanticError, LexicalError, SyntaticError, IOException {
        switch (action) {
            case 35:
                functionVariable = variable;
                break;
            case 36:
                analyze = true;
                //System.out.println("------> " + functionVariable);
                if (function.containsKey(functionVariable)) {
                    Functions fun = function.get(functionVariable);
                    //System.out.println(Arrays.toString(fun.getListActions().toArray()));
                    //while (activateFunctions) {
                    for (int i = 0; i < fun.getListActions().size(); i++) {
                        //System.out.println("action: " + fun.getListActions().get(i).getAction() + " - Token: " + fun.getListActions().get(i).getToken());
                        executeAction(fun.getListActions().get(i).getAction(), fun.getListActions().get(i).getToken());
                    }
                    //}
                    //System.out.println("Passou por aqui - " + functionVariable);
                    //stack.push(returnFunc);
                    //activateFunctions = false;
                } else {
                    throw new SemanticError("A função " + functionVariable + "(...) não foi criada", token.getPosition());
                }
                break;
        }
    }

    private void calculation(int action, Token token) throws SemanticError {
        Elements a, b;
        int a1, b1;
        double a2, b2;

        switch (action) {
            case 38:
                a = stack.pop();
                b = stack.pop();

                if (a.getType() == INT && b.getType() == INT) {
                    a1 = Integer.parseInt(a.getValue().toString());
                    b1 = Integer.parseInt(b.getValue().toString());
                    stack.push(new Elements(INT, (b1 + a1)));
                } else if ((a.getType() == INT || a.getType() == FLOAT) && (b.getType() == INT || b.getType() == FLOAT)) {
                    a2 = Double.parseDouble(a.getValue().toString());
                    b2 = Double.parseDouble(b.getValue().toString());
                    pushNumericType(b2 + a2);
                } else {
                    if (b.getType() != INT && b.getType() != FLOAT) {
                        throw new SemanticError("\"" + b.getValue() + "\" não é um valor numérico para ser somado", token.getPosition());
                    }
                    throw new SemanticError("\"" + a.getValue() + "\" não é um valor numérico para ser somado", token.getPosition());
                }
                break;
            case 39:
                a = stack.pop();
                b = stack.pop();

                if (a.getType() == INT && b.getType() == INT) {
                    a1 = Integer.parseInt(a.getValue().toString());
                    b1 = Integer.parseInt(b.getValue().toString());
                    stack.push(new Elements(INT, (b1 - a1)));
                } else if ((a.getType() == INT || a.getType() == FLOAT) && (b.getType() == INT || b.getType() == FLOAT)) {
                    a2 = Double.parseDouble(a.getValue().toString());
                    b2 = Double.parseDouble(b.getValue().toString());
                    pushNumericType(b2 - a2);
                } else {
                    if (b.getType() != INT && b.getType() != FLOAT) {
                        throw new SemanticError("\"" + b.getValue() + "\" não é um valor numérico para ser subtraído", token.getPosition());
                    }
                    throw new SemanticError("\"" + a.getValue() + "\" não é um valor numérico para subtrair", token.getPosition());
                }
                break;
            case 40:
                a = stack.pop();
                b = stack.pop();

                if (a.getType() == INT && b.getType() == INT) {
                    a1 = Integer.parseInt(a.getValue().toString());
                    b1 = Integer.parseInt(b.getValue().toString());
                    stack.push(new Elements(INT, (b1 * a1)));
                } else if ((a.getType() == INT || a.getType() == FLOAT) && (b.getType() == INT || b.getType() == FLOAT)) {
                    a2 = Double.parseDouble(a.getValue().toString());
                    b2 = Double.parseDouble(b.getValue().toString());
                    pushNumericType(b2 * a2);
                } else {
                    if (b.getType() != INT && b.getType() != FLOAT) {
                        throw new SemanticError("\"" + b.getValue() + "\" não é um valor numérico para ser multiplicado", token.getPosition());
                    }
                    throw new SemanticError("\"" + a.getValue() + "\" não é um valor numérico para ser multiplicado", token.getPosition());
                }
                break;
            case 41:
                a = stack.pop();
                b = stack.pop();

                if ((a.getType() == INT || a.getType() == FLOAT) && (b.getType() == INT || b.getType() == FLOAT)) {
                    a2 = Double.parseDouble(a.getValue().toString());
                    b2 = Double.parseDouble(b.getValue().toString());
                    pushNumericType(b2 / a2);
                } else {
                    if (b.getType() != INT && b.getType() != FLOAT) {
                        throw new SemanticError("\"" + b.getValue() + "\" não é um valor numérico para ser dividido", token.getPosition());
                    }
                    throw new SemanticError("\"" + a.getValue() + "\" não é um valor numérico para divitir", token.getPosition());
                }
                break;
            case 42:
                a = stack.pop();
                b = stack.pop();

                if (a.getType() == INT && b.getType() == INT) {
                    a1 = Integer.parseInt(a.getValue().toString());
                    b1 = Integer.parseInt(b.getValue().toString());
                    stack.push(new Elements(INT, (int) Math.pow(b1, a1)));
                } else if ((a.getType() == INT || a.getType() == FLOAT) && (b.getType() == INT || b.getType() == FLOAT)) {
                    a2 = Double.parseDouble(a.getValue().toString());
                    b2 = Double.parseDouble(b.getValue().toString());
                    pushNumericType(Math.pow(b2, a2));
                } else {
                    if (b.getType() != INT && b.getType() != FLOAT) {
                        throw new SemanticError("\"" + b.getValue() + "\" não é um valor numérico para ser base da potenciação", token.getPosition());
                    }
                    throw new SemanticError("\"" + a.getValue() + "\" não é um valor numérico para ser expoente da potenciação", token.getPosition());
                }
                break;
            case 43:
                if (id.containsKey(variable)) {
                    stack.push(id.get(variable).getElement());
                } else {
                    throw new SemanticError("A variável \"" + variable + "\" não foi declarada", token.getPosition());
                }
                break;
        }
    }

    private void pushNumericType(double c2) {
        if (c2 == Math.floor(c2)) {
            stack.push(new Elements(INT, (int) c2));
        } else {
            stack.push(new Elements(FLOAT, c2));
        }
    }

    private void print(int action, Token token) {
        switch (action) {
            case 44:
                if (print) {
                    System.out.print(stringConc.toString().replaceAll("/ln", ""));
                } else {
                    System.out.println(stringConc.toString().replaceAll("/ln", ""));
                }
                if (stringConc.toString().contains("/ln")) {
                    System.out.println("");
                }
                stringConc.setLength(0);
                break;
            case 45:
                if (print) {
                    System.out.print(string.replaceAll("/ln", ""));
                } else {
                    System.out.println(string.replaceAll("/ln", ""));
                }
                if (string.contains("/ln")) {
                    System.out.println("");
                }
                break;
        }
    }

    private void stackTypes(int action, Token token) throws SemanticError {
        switch (action) {
            case 46:
                stack.push(new Elements(STRING, string));
                break;
            case 47:
                stack.push(new Elements(BOOLEAN, bool));
                break;
            case 48:
                //stack.push(stack.pop());
                break;
            case 49:

                break;
            case 50:

                break;
            case 51:
                if (id.containsKey(variable)) {
                    Elements element = id.get(variable).getElement();
                    if (element.getType() == INT) {
                        stack.push(element);
                    } else {
                        throw new SemanticError("A variável " + variable + " não é do tipo inteira", token.getPosition());
                    }
                } else {
                    throw new SemanticError("A variável " + variable + " não foi inicializada", token.getPosition());
                }
                break;
        }
    }

    private void elementVecFunc(int action, Token token) {
        switch (action) {
            case 52:
                vectorVariable.add(token.getLexeme());
                break;
            case 53:
                variablePointing = token.getLexeme();
                break;
        }
    }

    private void conferLogical(int action, Token token) throws SemanticError {
        switch (action) {
            case 54:
            case 55:
            case 56:
                stack.push(new Elements(BOOLEAN, opr(token)));
                break;
        }
    }

    private boolean opr(Token token) throws SemanticError {
        //String op = logicOp.pop();
        Elements element1, element2;
        //if (!logicOp.empty()) {
//            System.out.println("logic - " + logicOp.peek());
        //}
        switch (logicOp.pop()) {
            case "<":
                element1 = stack.pop();
                element2 = stack.pop();
                if ((element1.getType() == INT || element1.getType() == FLOAT) && (element2.getType() == INT || element2.getType() == FLOAT)) {
                    return Double.parseDouble(element2.getValue().toString()) < Double.parseDouble(element1.getValue().toString());
                } else {
                    throw new SemanticError("Não é possível verificar o valor numérioco em <", token.getPosition());
                }
            case ">":
                element1 = stack.pop();
                element2 = stack.pop();
                if ((element1.getType() == INT || element1.getType() == FLOAT) && (element2.getType() == INT || element2.getType() == FLOAT)) {
                    return Double.parseDouble(element2.getValue().toString()) > Double.parseDouble(element1.getValue().toString());
                } else {
                    throw new SemanticError("Não é possível verificar o valor numérioco em >", token.getPosition());
                }
            case "<=":
                element1 = stack.pop();
                element2 = stack.pop();
                if ((element1.getType() == INT || element1.getType() == FLOAT) && (element2.getType() == INT || element2.getType() == FLOAT)) {
                    return Double.parseDouble(element2.getValue().toString()) <= Double.parseDouble(element1.getValue().toString());
                } else {
                    throw new SemanticError("Não é possível verificar o valor numérioco em <=", token.getPosition());
                }
            case ">=":
                element1 = stack.pop();
                element2 = stack.pop();
                if ((element1.getType() == INT || element1.getType() == FLOAT) && (element2.getType() == INT || element2.getType() == FLOAT)) {
                    return Double.parseDouble(element2.getValue().toString()) >= Double.parseDouble(element1.getValue().toString());
                } else {
                    throw new SemanticError("Não é possível verificar o valor numérioco em >=", token.getPosition());
                }
            case "==":
                element1 = stack.pop();
                element2 = stack.pop();
                return element2.getValue().equals(element1.getValue());
            case "===":
                element1 = stack.pop();
                element2 = stack.pop();
                if (element1.getType() == INT && element2.getType() == INT) {
                    return element2.getValue().equals(element1.getValue());
                } else if (element1.getType() == FLOAT && element2.getType() == FLOAT) {
                    return element2.getValue().equals(element1.getValue());
                } else if (element1.getType() == STRING && element2.getType() == STRING) {
                    return element2.getValue().equals(element1.getValue());
                } else {
                    return false;
                }
            case "!=":
            case "<>":
                element1 = stack.pop();
                element2 = stack.pop();
                return !(element2.getValue().equals(element1.getValue()));
            case "!":
            case "~":
            case "not":
            case "nao":
                element1 = stack.pop();
                if (element1.getType() == BOOLEAN) {
                    return !((boolean) element1.getValue());
                } else {
                    throw new SemanticError("Não há uma construção lógica para utilizar a expressão de negação", token.getPosition());
                }
            case "&&":
            case "&":
            case "and":
            case "e":
                element1 = stack.pop();
                element2 = stack.pop();
                System.out.println(element2.getValue() + " - " + element1.getValue());
                if (element1.getType() == BOOLEAN && element2.getType() == BOOLEAN) {
                    return (boolean) element2.getValue() && (boolean) element1.getValue();
                } else {
                    throw new SemanticError("Não há uma construção lógica para utilizar a expressão [and, e, &, &&]", token.getPosition());
                }
            case "||":
            case "|":
            case "or":
            case "ou":
                element1 = stack.pop();
                element2 = stack.pop();
                if (element1.getType() == BOOLEAN && element2.getType() == BOOLEAN) {
                    return (boolean) element2.getValue() || (boolean) element1.getValue();
                } else {
                    throw new SemanticError("Não há uma construção lógica para utilizar a expressão [or, ou, |, ||]", token.getPosition());
                }
            case "xor":
                element1 = stack.pop();
                element2 = stack.pop();
                if (element1.getType() == BOOLEAN && element2.getType() == BOOLEAN) {
                    return (boolean) element2.getValue() ^ (boolean) element1.getValue();
                } else {
                    throw new SemanticError("Não há uma construção lógica para utilizar a expressão [xor]", token.getPosition());
                }
        }
        return false;
    }

    private void input(int action, Token token) {
        Scanner sc = new Scanner(System.in, "latin1");
        print = true;
        switch (action) {
            case 57:
                print(44, token);
                break;
            case 58:
                print(45, token);
                break;
        }
        input = sc.next();
    }

    private void concatenation(int action, Token token) {
        switch (action) {
            case 59:
                stringConc.append(stack.pop().getValue());
                break;
            case 60:
                parameter.push(stack.pop());
                break;
            case 82:

                break;
        }
    }

    private void function(int action, Token token) throws SemanticError, LexicalError, SyntaticError, IOException {
        //for (Elements elements : parameter) {
        //scopeId.put(input, elements)
        //}
        switch (action) {
            case 37:
                //idFunc = token.getLexeme();
                analyze = false;
                //activateFunctions = false;
                //if (!activateFunctions) {
                //    analyze = false;
                //}
                //activateFunctions = false;
                //analyze = activateFunctions;
                scopeLevel = 1;
                break;
            case 61:
                //scopeLevel++;
                Elements fun,
                 par;
                if (functionParameter.size() != parameter.size()) {
                    throw new SemanticError("O parametro da função \"" + functionVariable + "\" está incorreto", token.getPosition());
                }
                String auxVariablePointing = variablePointing;
                while (!functionParameter.empty() && !parameter.empty()) {
                    fun = functionParameter.pop();
                    par = parameter.pop();
                    variablePointing = (String) fun.getValue();
                    stack.push(par);
                    scopeLevel = 1;
                    executeAction(22, token22);
                }
                variablePointing = auxVariablePointing;
            case 62:
                //function.put(variable, stack.pop());
                //stack.pop();
                //stack.push(par)
                if (!stack.empty()) {
                    //returnFunc = stack.pop();
                }
                //returnFunc = stack.pop();
                //System.out.println(stack.peek());
                break;
            case 63:
                removeScopeVariables(scopeLevel);
                analyze = true;
                //analyze = true;
                break;
        }
    }
    
    private void _if(int action, Token token) throws SemanticError {
        Elements element;
        boolean r, w;
        switch (action) {
            case 64:
                scopeLevel++;
                if (analyze) {
                    element = stack.pop();
                    if (element.getType() == BOOLEAN) {
                        analyze = (boolean) element.getValue();
                    } else {
                        throw new SemanticError("A expressão do [se, if] está incorreta, resultado da espressão: " + element.getValue(), token.getPosition());
                    }
                }
                scopesIf.push(analyze);
                scopesElse.push(analyze);
                break;
            case 65:
                removeScopeVariables(scopeLevel);
                w = scopesIf.pop();
                if (!scopesIf.empty()) {
                    r = scopesIf.peek();
                    if (!r) {
                        scopesIf.push(false);
                        analyze = false;
                    } else {
                        scopesIf.push(w);
                        analyze = r;
                    }
                } else {
                    scopesIf.push(w);
                    analyze = true;
                }
                break;
            case 66:
                scopeLevel++;
                if (analyze) {
                    element = stack.pop();
                    if (element.getType() == BOOLEAN) {
                        analyze = (boolean) element.getValue();
                    } else {
                        throw new SemanticError("A expressão do [senao se, else if, elif] está incorreta, resultado da espressão: " + element.getValue(), token.getPosition());
                    }
                }
                scopesIf.pop();
                scopesIf.push(analyze);
                if (!scopesElse.peek()) {
                    scopesElse.pop();
                    scopesElse.push(analyze);
                }
                break;
            case 67:
                removeScopeVariables(scopeLevel);
                w = scopesIf.pop();
                if (!scopesIf.empty()) {
                    r = scopesIf.peek();
                    if (!r) {
                        scopesIf.push(false);
                        analyze = false;
                    } else {
                        scopesIf.push(w);
                        analyze = r;
                    }
                } else {
                    scopesIf.push(w);
                    analyze = true;
                }
                break;
            case 68:
                scopeLevel++;
                w = scopesIf.pop();
                if (!scopesIf.empty()) {
                    r = scopesIf.peek();
                    if (!r) {
                        analyze = false;
                    } else {
                        analyze = !scopesElse.peek();
                    }
                } else {
                    analyze = !scopesElse.peek();
                }
                scopesIf.push(w);
                break;
            case 69:
                removeScopeVariables(scopeLevel);
                analyze = true;
                scopesIf.pop();
                scopesElse.pop();
                break;
            case 79:
                analyze = true;
                scopesIf.pop();
                scopesElse.pop();
                break;
        }
    }

    int keysWhile = 0;
    
    private void _while(int action, Token token) throws SemanticError, FileNotFoundException, LexicalError, SyntaticError, IOException {
        switch (action) {
            case 70:
                onWhile = true;
                break;
            case 71:
                scopeLevel++;
                keysWhile++;
                Elements element = stack.pop();
                if (element.getType() == BOOLEAN) {
                    onWhile = (boolean) element.getValue();
                    analyzeWhile = onWhile;
                } else {
                    throw new SemanticError("A expressão do [enquanto, while] está incorreta, resultado da espressão: " + element.getValue(), token.getPosition());
                }
                break;
            case 72:
                keysWhile--;
                ExecuteAction ac;
                while (onWhile) {
                    execWhile = false;
                    for (int i = 0; i < listActionsWhile.size(); i++) {
                        ac = listActionsWhile.get(i);
                        executeAction(ac.getAction(), ac.getToken());
                    }
                    execWhile = true;
                }
                //listActionsWhile.clear();
                if (keysWhile == 0) {
                    onWhile = false;
                    analyzeWhile = true;
                } else {
                   onWhile = true;
                   analyzeWhile = false;
                }
                
                removeScopeVariables(scopeLevel);
                break;
        }
    }

    private void removeScopeVariables(int level) {
        /*System.out.println("level a ser excluido: " + level);
        for (Variables val : id.values()) {
            System.out.println("variavel: " + val.getVariable() + " - level: " + val.getScopeLevel());
        }*/

        for (Variables val : id.values()) {
            if (val.getScopeLevel() == level) {
                id.remove(val.getVariable());
            }
        }
        scopeLevel--;
    }

    private Types getType(String data) {
        if (data.matches("[0-9]+")) {
            return INT;
        } else if (data.matches("[0-9]+.[0-9]+")) {
            return FLOAT;
        } else {
            return STRING;
        }
    }

    private void vectorAdd(int action, Token token) throws SemanticError {
        String vecVal1, vecVal2;
        ArrayVector vector1, vector2;
        switch (action) {
            case 73:
                vecVal1 = vectorVariable.poll();
                if (!vectorVariable.isEmpty()) {
                    vecVal1 = vectorVariable.poll();
                }
                if (array.containsKey(vecVal1)) {
                    array.get(vecVal1).add(stack.pop());
                } else {
                    vector1 = new ArrayVector();
                    vector1.add(stack.pop());
                    array.put(vecVal1, vector1);
                }
                break;
            case 74:
                vecVal2 = vectorVariable.poll();
                vecVal1 = vectorVariable.poll();
                if (array.containsKey(vecVal1)) {
                    vector1 = array.get(vecVal1);
                    if (array.containsKey(vecVal2)) {
                        vector2 = array.get(vecVal2);
                    } else {
                        vector2 = new ArrayVector();
                    }
                    for (int i = 0; i < vector1.size(); i++) {
                        vector2.add(vector1.get(i));
                    }
                    array.put(vecVal2, vector2);
                } else {
                    throw new SemanticError("O vetor \"" + vecVal1 + "\" não contém nada", token.getPosition());
                }

                /*
                token22 = token;
                if (id.containsKey(variablePointing)) {
                    id.get(variablePointing).setElement(stack.pop());
                } else {
                    id.put(variablePointing, new Variables(variablePointing, stack.pop(), scopeLevel));
                }*/
                break;
            case 75:
                vecVal1 = vectorVariable.poll();
                if (!vectorVariable.isEmpty()) {
                    vecVal1 = vectorVariable.poll();
                }
                if (array.containsKey(vecVal1)) {
                    vector1 = array.get(vecVal1);
                } else {
                    vector1 = new ArrayVector();
                }
                vector1.add(positionVector, stack.pop());
                array.put(vecVal1, vector1);
                break;
            case 76:
                array.get(vectorVariable.poll()).remove(positionVector);
                break;
            case 77:
                vecVal1 = vectorVariable.poll();
                if (!vectorVariable.isEmpty()) {
                    vecVal1 = vectorVariable.poll();
                }
                if (array.containsKey(vecVal1)) {
                    array.get(vecVal1).add(stack.pop());
                } else {
                    vector1 = new ArrayVector();
                    vector1.add(stack.pop());
                    array.put(vecVal1, vector1);
                }
                break;
            case 78:
                vecVal1 = vectorVariable.poll();
                if (!vectorVariable.isEmpty()) {
                    //vecVal1 = vectorVariable.pop();
                }

                while (!addDataVector.isEmpty()) {
                    //System.out.println("subindo para o array: " + addDataVector.peek().getValue() + " - no: " + vecVal1);
                    array.get(vecVal1).add(addDataVector.poll());
                }
                /*for (int i = 0; i < array.get(vecVal1).size(); i++) {
                    System.out.println("=h: " + array.get(vecVal1).get(i).getValue());
                }*/

                break;
        }
    }

    private void numericSignal(Token token) {
        Elements element = stack.peek();
        switch (element.getType()) {
            case INT:
                stack.peek().setValue(-1 * Integer.parseInt(element.getValue().toString()));
                //stack.peek().setValue(-1 * (int) element.getValue());
                break;
            case FLOAT:
                //System.out.println(element.getValue());
                stack.peek().setValue(-1 * Double.parseDouble(element.getValue().toString()));
                break;

        }
    }

    private void constants(Token token) throws SemanticError {
        if (!id.containsKey(_const)) {
            id.put(_const, new Variables(_const, new Elements(getType(token.getLexeme()), token.getLexeme()), 0));
        } else {
            throw new SemanticError("Constante: \"" + _const + "\". Não é possível atribuir mais de um valor à uma constante", token.getPosition());
        }
    }

}

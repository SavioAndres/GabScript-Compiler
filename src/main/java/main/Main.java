package main;

import compiler.*;
import static compiler.Types.STRING;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import structures.ArrayMatrix;
import structures.ArrayVector;
import structures.ReadFile;


/**
 *
 * @author Sávio Andres
 */
public class Main {
    
    public static HashMap<String, Variables> id = new HashMap<>();
    public static HashMap<String, ArrayVector> array = new HashMap<>();
    public static HashMap<String, Functions> function = new HashMap<>();
    public static String pathMain;
    public static double version;
    
    public static void main(String[] args) throws LexicalError, SyntaticError, FileNotFoundException, UnsupportedEncodingException, IOException, SemanticError {
        version = 0.01;
        Scanner sc = new Scanner(System.in, "latin1");
        //String cmd = sc.nextLine();
        String cmd = "C:\\Users\\savio\\Documents\\Projects\\Java\\GabScript\\files\\teste.txt";
        pathMain = cmd;
        String cmd2 = "", cmd3 = "";
        switch (cmd) {
            case "aqui":
                boolean continuel = true;
                while (continuel) {
                    if (cmd2.contains("print") || cmd2.contains("imprima") || cmd2.contains("println") || cmd2.contains("imprimaln"))
                        System.out.print("\n>>> ");
                    else
                        System.out.print(">>> ");
                    cmd2 = sc.nextLine();
                    Lexico lex2 = new Lexico(cmd2);
                    Sintatico sym2 = new Sintatico();
                    Semantico sem2 = new Semantico();
                    try {
                        sym2.parse(lex2, sem2);
                        cmd3 += cmd2 + "\r\n";
                        //cmd4 = "";
                    } catch (LexicalError | SyntaticError | SemanticError e) {
                        if (cmd2.equals("terminal"))
                            continuel = false;
                        else if (cmd2.equals("salvar"))
                            escritaArq(cmd3);
                        else
                            System.err.println(e.getMessage());//e.printStackTrace();
                    }
                }
                break;
            default:
                //Reader reader = new FileReader("C:\\Users\\savio\\Documents\\NetBeansProjects\\MCompiler_v0.01\\files\\teste.txt");
                BufferedReader p1_reader = new BufferedReader(new InputStreamReader(new FileInputStream(cmd), "latin1"));
                BufferedReader p2_reader = new BufferedReader(new InputStreamReader(new FileInputStream(cmd), "latin1"));
                long tempoInicio = System.currentTimeMillis();
                Lexico p1_lex = new Lexico(p1_reader);
                Lexico p2_lex = new Lexico(p2_reader);
                FirstSyntacticPass p1_sym = new FirstSyntacticPass();
                Sintatico p2_sym = new Sintatico();
                FirstSemanticPass p1_sem = new FirstSemanticPass();
                Semantico p2_sem = new Semantico();

                try {
                    p1_sym.parse(p1_lex, p1_sem);
                    //System.out.println(function);
                    //System.out.println("Passss: " + function.get("gh2").getListActions().toString());
                    //ArrayList<ExecuteAction> hj = ;
                    //System.out.println("Segunda passada");
                    p2_sym.parse(p2_lex, p2_sem);
                    System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));
                } catch (LexicalError | SyntaticError | SemanticError e) {
                    e.printStackTrace();
                    //System.err.println(e.toString());
                }
                break;
        }
    }
    
    private static void escritaArq(String text) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        Scanner sc = new Scanner(System.in, "latin1");
        System.out.print("Digite o caminho onde o arquivo será salvo: ");
        String path = sc.nextLine();
        System.out.print("Dê um nome para o arquivo: ");
        String name = sc.nextLine();
        OutputStreamWriter bufferOut = new OutputStreamWriter(new FileOutputStream(path + "\\" + name + ".txt"), "UTF-8");
        bufferOut.write(text);
        bufferOut.close();
        System.out.println("Salvo com sucesso");
    }

    
}

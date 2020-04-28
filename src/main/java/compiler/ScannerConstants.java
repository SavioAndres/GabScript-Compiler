package compiler;

public interface ScannerConstants
{

    int[] TOKEN_STATE = { 0,  0, 29, -1,  0, 26, -1,  8,  9,  4,  2, 38,  3, 39,  6, 43, 14, 15, 22, 16, 21, -1, 42, 10, 11,  7, 41, 41, 34, 41, 41, 41, 12, 28, 13, 30, 20, 45, 25,  5, -2,  0, -1, 24, 17, 18, 23, -1, 41, 41, 41, 35, 36, 41, 27, -2,  0, 44, 19, -1, 33, 32, 31, 37,  0, -1, -1, 40 };

    int[] SPECIAL_CASES_INDEXES =
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37 };

    String[] SPECIAL_CASES_KEYS =
        {  "elif", "else", "enquanto", "entrada", "false", "falso", "for", "func", "funcao", "funcinternal", "function", "if", "import", "importe", "imprima", "imprimaln", "in", "include", "incluir", "input", "intervalo", "no", "null", "nulo", "para", "print", "println", "privado", "private", "range", "retorne", "return", "se", "senao", "true", "verdadeiro", "while" };

    int[] SPECIAL_CASES_VALUES =
        {  61, 62, 65, 54, 76, 77, 66, 52, 51, 78, 50, 59, 46, 47, 56, 58, 68, 48, 49, 53, 71, 69, 79, 80, 67, 55, 57, 82, 81, 70, 73, 72, 60, 63, 74, 75, 64 };

    String[] SCANNER_ERROR =
    {
        "Caractere não esperado",
        "",
        "",
        "Erro identificando text",
        "",
        "",
        "Erro identificando text",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando \"@vers:\"",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando <ignorar>",
        "",
        "Erro identificando float",
        "",
        "",
        "",
        "",
        "Erro identificando \"@vers:\"",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando <ignorar>",
        "",
        "",
        "",
        "Erro identificando \"@vers:\"",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando \"@vers:\"",
        "Erro identificando \"@vers:\"",
        ""
    };

}

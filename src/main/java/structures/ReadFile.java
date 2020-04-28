package structures;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author savio
 */
public class ReadFile {

    public static String readError(String path, int position) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "latin1"))) {
            String line;
            int nPosition = 0;
            int nLine = 1;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (nPosition == position) {
                        return "Erro na linha " + nLine + ": \"" + line + "\" | ";
                    }
                    nPosition++;
                }
                nPosition = nPosition + 2;
                nLine++;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return "";
    }

}

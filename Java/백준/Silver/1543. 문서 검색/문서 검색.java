import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String doc = in.readLine();
        String pat = in.readLine();

        int dLen = doc.length();
        int pLen = pat.length();

        doc = doc.replace(pat, "");
        System.out.println((dLen - doc.length()) / pLen);
    }
}
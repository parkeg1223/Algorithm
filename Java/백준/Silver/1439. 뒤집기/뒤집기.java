import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();

        int[] n = new int[2];
        char curr = s.charAt(0);
        n[curr-'0']++;
        char c;
        for (int i = 1; i < s.length(); i++) {
            c = s.charAt(i);
            if (c != curr) {
                n[c-'0']++;
                curr = c;
            }
        }
        System.out.println(Math.min(n[0], n[1]));
    }
}
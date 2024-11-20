import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        while (!((s = in.readLine()).equals("end"))) {
            boolean valid = true, hasVowel = false;
            int contC = 0, contV = 0;
            char prev = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    hasVowel = true;
                    contC = 0;
                    if (++contV == 3) {
                        valid = false;
                        break;
                    }
                } else {
                    contV = 0;
                    if (++contC == 3) {
                        valid = false;
                        break;
                    }
                }

                if (prev == c && (c != 'e' && c != 'o')) {
                    valid = false;
                    break;
                }
                prev = c;
            }

            if (!valid || !hasVowel) {
                validate(s, false);
                continue;
            }

            validate(s, true);
        }

        System.out.print(sb);
    }

    public static void validate(String str, boolean isValid) {
        sb.append("<").append(str).append("> is ").append(isValid ? "acceptable.\n" : "not acceptable.\n");
    }
}
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(in.readLine());
        while (N-- > 0) {
            String s = in.readLine();
            sb.append(s.charAt(0) >= 'A' && s.charAt(0) <= 'Z' ?
                    s : (char)(s.charAt(0)-32) + s.substring(1)).append("\n");
        }
        System.out.print(sb);
    }
}
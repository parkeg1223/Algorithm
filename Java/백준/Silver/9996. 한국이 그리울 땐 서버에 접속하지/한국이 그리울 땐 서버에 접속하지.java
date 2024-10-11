import java.io.*;

public class Main {

    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(in.readLine());

        String pat = in.readLine().replace("*", ".*");
        for (int i = 0; i < n; i++) {
            String filename = in.readLine();
            if (filename.matches(pat)) sb.append("DA").append("\n");
            else sb.append("NE").append("\n");
        }
        System.out.print(sb);
    }
}
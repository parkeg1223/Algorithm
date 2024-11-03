import java.io.*;
import java.util.*;

public class Main {

    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            int y = 0, k = 0;
            for (int i = 0; i < 9; i++) {
                st = new StringTokenizer(in.readLine());
                y += Integer.parseInt(st.nextToken());
                k += Integer.parseInt(st.nextToken());
            }
            if (y > k) sb.append("Yonsei").append("\n");
            else if (y < k) sb.append("Korea").append("\n");
            else sb.append("Draw").append("\n");
        }
        System.out.print(sb);
    }
}
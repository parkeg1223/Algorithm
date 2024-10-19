import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        StringTokenizer st;
        int[] ans = new int[5];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (x == 0 || y == 0) ans[4]++;
            else if (x > 0) {
                if (y > 0) ans[0]++;
                else ans[3]++;
            } else {
                if (y > 0) ans[1]++;
                else ans[2]++;
            }
        }

        String sb = "Q1: " + ans[0] + "\n" +
                "Q2: " + ans[1] + "\n" +
                "Q3: " + ans[2] + "\n" +
                "Q4: " + ans[3] + "\n" +
                "AXIS: " + ans[4] + "\n";

        System.out.print(sb);
    }
}
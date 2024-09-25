import java.io.*;
import java.util.*;

public class Main {

    static int[] ans , prev;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        ans = new int[N+1];
        prev = new int[N+1];
        for (int i = 1; i <= N; i++) {
            if (i * 3 <= N) {
                if (ans[i*3] == 0) {
                    ans[i*3] = ans[i] + 1;
                    prev[i*3] = i;
                } else {
                    ans[i*3] = Math.min(ans[i*3], ans[i] + 1);
                    if (ans[i*3] == ans[i] + 1) prev[i*3] = i;
                }
            }
            if (i * 2 <= N) {
                if (ans[i*2] == 0) {
                    ans[i*2] = ans[i] + 1;
                    prev[i*2] = i;
                }
                else {
                    ans[i*2] = Math.min(ans[i*2], ans[i] + 1);
                    if (ans[i*2] == ans[i] + 1) prev[i*2] = i;
                }
            }

            if (i + 1 <= N) {
                if (ans[i+1] == 0) {
                    ans[i+1] = ans[i] + 1;
                    prev[i+1] = i;
                } else {
                    ans[i+1] = Math.min(ans[i+1], ans[i] + 1);
                    if (ans[i+1] == ans[i] + 1) prev[i+1] = i;
                }
            }
        }

        sb.append(ans[N]).append("\n");
        while (N > 0) {
            sb.append(N).append(" ");
            N = prev[N];
        }
        System.out.println(sb);
    }
}
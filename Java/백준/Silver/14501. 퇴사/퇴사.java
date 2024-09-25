import java.io.*;
import java.util.*;

public class Main {

    static int[] ans, T, P;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        ans = new int[N+2];
        T = new int[N+1];
        P = new int[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            if (i + T[i] > N+1) {
                ans[i] = ans[i+1];
            } else {
                ans[i] = Math.max(ans[i+1], P[i] + ans[i+T[i]]);
            }
        }

        System.out.println(ans[1]);
    }
}
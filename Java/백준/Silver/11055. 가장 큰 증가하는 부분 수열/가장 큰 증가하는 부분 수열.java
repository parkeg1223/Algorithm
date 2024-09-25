import java.io.*;
import java.util.*;

public class Main {

    static int[] ans, num;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        ans = new int[N];
        num = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            ans[i] = num[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (num[i] > num[j]) {
                    ans[i] = Math.max(ans[i], ans[j] + num[i]);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, ans[i]);
        }
        System.out.println(max);
    }
}
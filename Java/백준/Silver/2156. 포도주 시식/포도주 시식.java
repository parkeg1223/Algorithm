import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        int[] wine = new int[n+1];
        int[] ans = new int[n+1];
        for (int i = 1; i <= n; i++) {
            wine[i] = Integer.parseInt(in.readLine());
        }

        ans[1] = wine[1];
        if (n >= 2) ans[2] = ans[1]+wine[2];
        for (int i = 3; i <= n; i++) {
            // i번째 와인을 먹은 경우 중 최대 = Math.max(i-1번째 와인을 먹은 경우, i-1번째 와인을 먹지 않은 경우) + i번째 와인
            ans[i] = Math.max(ans[i-3] + wine[i-1], ans[i-2]) + wine[i];
            // i번째 와인까지 최대로 먹는 경우 = Math.max(i번쨰 와인을 먹은 경우, i번쨰 와인을 먹지 않은 경우)
            ans[i] = Math.max(ans[i], ans[i-1]);
        }

        System.out.println(ans[n]);
    }
}
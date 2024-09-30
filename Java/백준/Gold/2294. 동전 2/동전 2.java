import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[0] = 0;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(Integer.parseInt(in.readLine()));
        }
        List<Integer> curr = new ArrayList<>(set);

        for (int i = 0; i < curr.size(); i++) {
            int c = curr.get(i);
            for (int j = c; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j-c]+1);
            }
        }
        System.out.println(dp[k] == Integer.MAX_VALUE-1 ? -1 : dp[k]);
    }
}
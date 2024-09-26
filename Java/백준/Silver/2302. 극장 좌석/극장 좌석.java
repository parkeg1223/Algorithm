import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());

        int[] dp = new int[N+1];
        int[] vip = new int[M+1];
        List<Integer> nPerson = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            vip[i] = Integer.parseInt(in.readLine());
        }
        for (int i = 1; i <= M; i++) {
            nPerson.add(vip[i] - vip[i-1] - 1);
        }
        if (N != vip[M]) nPerson.add(N - vip[M]);

        // dp[i] = (VIP가 없을 때 i개의 좌석에 앉을 수 있는 방법의 수)
        dp[0] = 1;
        dp[1] = 1;
        if (N >= 2) dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            // i번쨰 사람이 자리를 바꾸지 않았을 경우 + i번쨰 사람이 자리를 바꿨을 경우
            dp[i] = dp[i-1] + dp[i-2];
        }

        int answer = 1;
        for (Integer p : nPerson) {
            answer *= dp[p];
        }
        System.out.println(answer);
    }
}
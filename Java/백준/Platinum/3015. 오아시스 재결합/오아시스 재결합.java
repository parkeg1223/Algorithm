import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Stack<int[]> stack = new Stack<>();

        int N = Integer.parseInt(in.readLine());
        long[] cnt = new long[N];
        long answer = 0;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(in.readLine());
            while (!stack.isEmpty()) {
                if (stack.peek()[1] < n) {
                    answer++;
                    stack.pop();
                } else if (stack.peek()[1] == n){
                    int idx = stack.peek()[0];
                    answer += (++cnt[idx]);
                    cnt[i] = cnt[idx];
                    break;
                } else {
                    answer++;
                    cnt[i] = 1;
                    break;
                }
            }
            stack.push(new int[]{i, n});
        }
        System.out.println(answer);
    }
}
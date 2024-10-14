import java.io.*;
import java.util.*;

public class Main {

    static int N, T;
    static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(in.readLine());
            int[] nArr = new int[N];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++) {
                nArr[i] = Integer.parseInt(st.nextToken());
            }

            int curMax = 0;
            for (int i = N-1; i >= 0; i--) {
                if (nArr[i] > curMax) curMax = nArr[i];
                else answer += curMax - nArr[i];
            }
            sb.append(answer).append("\n");
            answer = 0;
        }
        System.out.print(sb);
    }
}
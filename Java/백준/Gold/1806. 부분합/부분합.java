import java.util.*;
import java.io.*;

public class Main {

    static int N, S, answer = Integer.MAX_VALUE;
    static int[] nArr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nArr = new int[N];
        st = new StringTokenizer(in.readLine());

        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0, e = 0, sum = nArr[0];
        while (s < N && e < N) {
            if (sum >= S) {
                answer = Math.min(answer, e-s+1);
                sum -= nArr[s++];
                if (s > e) {
                    if (e < N-1) sum += nArr[++e];
                }
            } else {
                if (e < N-1) sum += nArr[++e];
                else break;
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
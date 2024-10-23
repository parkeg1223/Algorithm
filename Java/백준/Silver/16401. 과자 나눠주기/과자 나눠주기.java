import java.io.*;
import java.util.*;

public class Main {

    static int m, n;
    static int[] snack;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        snack = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            snack[i] = Integer.parseInt(st.nextToken());
        }

        int start = 1, end = Arrays.stream(snack).max().getAsInt(), mid, answer = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (possible(mid)) {
                start = mid+1;
                answer = Math.max(answer, mid);
            }
            else end = mid-1;
        }
        System.out.println(answer);
    }

    public static boolean possible(int num) {
        int temp = 0;
        for (int i = 0; i < n; i++) {
            temp += snack[i] / num;
        }
        return temp >= m;
    }
}
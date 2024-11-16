import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0, max = arr[N-1];
        for (int i = N-1; i >= 0; i--) {
            ans = Math.max(ans, max - arr[i]);
            max = Math.max(max, arr[i]);
        }

        System.out.println(ans);
    }
}
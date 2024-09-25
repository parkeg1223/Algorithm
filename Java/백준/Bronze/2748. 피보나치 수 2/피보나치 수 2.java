import java.io.*;

public class Main {

    static long[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        ans = new long[n+1];
        ans[1] = 1;
        for (int i = 2; i <= n; i++) {
            ans[i] = ans[i-1] + ans[i-2];
        }
        System.out.println(ans[n]);
    }
}
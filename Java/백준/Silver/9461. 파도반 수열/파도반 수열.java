import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long[] nArr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(in.readLine());

        nArr = new long[101];
        Arrays.fill(nArr, 1, 4, 1);
        Arrays.fill(nArr, 4, 6, 2);
        for (int i = 6; i <= 100; i++) {
            nArr[i] = nArr[i-5] + nArr[i-1];
        }

        for (int i = 0; i < N; i++) {
            sb.append(nArr[Integer.parseInt(in.readLine())]).append("\n");
        }

        System.out.print(sb);
    }
}
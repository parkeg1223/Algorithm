import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map<String, String> pwMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            pwMap.put(st.nextToken(), st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            sb.append(pwMap.get(in.readLine())).append("\n");
        }

        System.out.print(sb);
    }
}
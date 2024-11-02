import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int pMin = Integer.MAX_VALUE, eMin = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int pack = Integer.parseInt(st.nextToken());
            int each = Integer.parseInt(st.nextToken());
            pMin = Math.min(pMin, pack);
            pMin = Math.min(pMin, each*6);
            eMin = Math.min(eMin, each);
        }
        System.out.println(Math.min((N/6+1)*pMin, (N/6)*pMin + (N%6)*eMin));
    }
}
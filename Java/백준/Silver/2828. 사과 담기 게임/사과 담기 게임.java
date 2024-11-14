import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int j = Integer.parseInt(in.readLine());

        int curr = 1;
        int dist = 0;
        while (j-- > 0) {
            int p = Integer.parseInt(in.readLine());
            if (curr == p) continue;
            if (curr < p) {
                if (curr+M-1 >= p) continue;
                dist += p-(curr+M)+1;
                curr = p-M+1;
            } else {
                dist += curr-p;
                curr = p;
            }
        }
        System.out.println(dist);
    }
}
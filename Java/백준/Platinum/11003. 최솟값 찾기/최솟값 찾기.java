import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            while (!queue.isEmpty() && queue.peekLast()[1] > n) queue.pollLast();
            
            queue.offer(new int[] {i, n});
            if (queue.peek()[0] < i-L+1) queue.poll();
            sb.append(queue.peek()[1]).append(" ");
        }
        System.out.println(sb);
    }
}
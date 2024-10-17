import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(in.readLine());
        while (n-- > 0) {
            pq.offer(Long.parseLong(st.nextToken()));
        }

        while (m-- > 0) {
            long min1 = pq.poll();
            long min2 = pq.poll();
            pq.offer(min1+min2);
            pq.offer(min1+min2);
        }

        long sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        System.out.println(sum);
    }
}
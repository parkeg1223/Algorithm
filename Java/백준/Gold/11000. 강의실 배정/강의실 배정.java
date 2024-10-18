import java.io.*;
import java.util.*;

public class Main {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());

        List<int[]> lectures = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            lectures.add(new int[] {s, t});
        }
        
        lectures.sort((o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int nRoom = 0;
        for (int i = 0; i < N; i++) {
            if (pq.isEmpty() || pq.peek() > lectures.get(i)[0]) nRoom++;
            else pq.poll();
            pq.offer(lectures.get(i)[1]);
        }

        System.out.println(nRoom);
    }
}
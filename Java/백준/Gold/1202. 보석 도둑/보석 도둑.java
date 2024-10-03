import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static class Jewel implements Comparable<Jewel>{
        int m, v;

        Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) {
            return o.v - this.v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Jewel> pq = new PriorityQueue<>();
        TreeSet<Integer> bag = new TreeSet<>();
        Map<Integer, Integer> nBag = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            pq.add(new Jewel(m, v));
        }

        for (int i = 0; i < K; i++) {
            int c = Integer.parseInt(in.readLine());
            nBag.put(c, nBag.getOrDefault(c, 0)+1);
            bag.add(c);
        }

        long maxValue = 0;
        while (!pq.isEmpty()) {
            Jewel j = pq.poll();
            Integer i;
            if ((i = bag.ceiling(j.m)) != null) {
                nBag.put(i, nBag.get(i)-1);
                if (nBag.get(i) == 0) bag.remove(i);
                maxValue += j.v;
            }
        }
        System.out.println(maxValue);
    }
}
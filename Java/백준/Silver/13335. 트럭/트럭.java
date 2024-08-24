import java.util.*;
import java.io.*;

public class Main {

    static int n, w, L, currN;
    static ArrayDeque<Integer> trucks = new ArrayDeque<>();
    static ArrayDeque<Integer> bridge = new ArrayDeque<>(w);

    public static void main(String[] args) throws IOException {
        input();
        int time = 0, nSum = 0;
        while (currN > 0) {
            time++;
            int out = bridge.poll();
            if (out > 0) {
                currN--;
                nSum -= out;
            }
            if (trucks.isEmpty() || nSum + trucks.peek() > L) {
                bridge.offer(0);
            } else {
                int in = trucks.poll();
                bridge.offer(in);
                nSum += in;
            }
        }
        System.out.println(time);
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        currN = n;
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            trucks.offer(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < w; i++) {
            bridge.offer(0);
        }
    }
}
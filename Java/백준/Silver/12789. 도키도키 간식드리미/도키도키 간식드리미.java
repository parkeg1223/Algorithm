import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<Integer> waiting = new ArrayDeque<>();
        ArrayDeque<Integer> space = new ArrayDeque<>();

        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            waiting.add(Integer.parseInt(st.nextToken()));
        }

        int next = 1;
        while (next <= N) {
            if (!waiting.isEmpty() && waiting.peek() == next) {
                waiting.poll();
                next++;
            } else if (!space.isEmpty() && space.peek() == next) {
                space.poll();
                next++;
            } else if (!waiting.isEmpty()) {
                space.addFirst(waiting.poll());
            } else {
                System.out.println("Sad");
                return;
            }
        }
        System.out.println("Nice");
    }
}
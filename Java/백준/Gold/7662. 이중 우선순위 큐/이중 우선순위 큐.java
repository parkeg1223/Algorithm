import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st;

        while (T-- > 0) {
            int k = Integer.parseInt(in.readLine());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> count = new HashMap<>();
            int size = 0;

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(in.readLine());
                char op = st.nextToken().charAt(0);
                int n = Integer.parseInt(st.nextToken());
                if (op == 'I') {
                    // 삽입
                    maxHeap.add(n);
                    minHeap.add(n);
                    size++;
                    if (count.containsKey(n) && count.get(n) > 0) {
                        count.put(n, count.get(n) + 1);
                    } else {
                        count.put(n, 1);
                    }
                } else {
                    if (size == 0) continue;
                    if (n == 1) {
                        // 최댓값 삭제
                        int max;
                        while (!maxHeap.isEmpty()) {
                            max = maxHeap.peek();
                            if (count.get(max) > 0) {
                                maxHeap.poll();
                                count.put(max, count.get(max) - 1);
                                break;
                            }
                            maxHeap.poll();
                        }
                    } else {
                        // 최솟값 삭제
                        int min;
                        while (!minHeap.isEmpty()) {
                            min = minHeap.peek();
                            if (count.get(min) > 0) {
                                minHeap.poll();
                                count.put(min, count.get(min) - 1);
                                break;
                            }
                            minHeap.poll();
                        }
                    }
                    size--;
                }
            }

            if (size == 0) sb.append("EMPTY\n");
            else {
                int max;
                while (!maxHeap.isEmpty()) {
                    max = maxHeap.peek();
                    if (count.get(max) > 0) {
                        sb.append(max).append(" ");
                        break;
                    }
                    maxHeap.poll();
                }

                int min;
                while (!minHeap.isEmpty()) {
                    min = minHeap.peek();
                    if (count.get(min) > 0) {
                        sb.append(min).append("\n");
                        break;
                    }
                    minHeap.poll();
                }
            }
        }

        System.out.print(sb);
    }
}
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        Map<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(in.readLine());
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else map.put(num, 1);
        }

        int maxVal = 0;
        long max = 0;
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= maxVal) {
                if (entry.getValue() == maxVal && entry.getKey() > max) continue;
                max = entry.getKey();
                maxVal = entry.getValue();
            }
        }
        System.out.println(max);
    }
}
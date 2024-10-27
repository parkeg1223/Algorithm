import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] height = new int[9];
        boolean[] h = new boolean[101];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            height[i] = Integer.parseInt(in.readLine());
            h[height[i]] = true;
            sum += height[i];
        }
        Arrays.sort(height);

        for (int i = 0; i < 9; i++) {
            if (sum-100-height[i] <= 100 && h[sum-100-height[i]]) {
                h[sum-100-height[i]] = false;
                h[height[i]] = false;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (h[height[i]]) sb.append(height[i]).append("\n");
        }
        System.out.println(sb);
    }
}
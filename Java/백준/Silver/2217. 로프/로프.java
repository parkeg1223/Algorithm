import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        Integer[] nArr = new Integer[N];

        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(in.readLine());
        }

        Arrays.sort(nArr, Collections.reverseOrder());

        int maxWeight = 0;
        for (int i = 0; i < N; i++) {
            maxWeight = Math.max(maxWeight, nArr[i] * (i+1));
        }

        System.out.println(maxWeight);
    }
}
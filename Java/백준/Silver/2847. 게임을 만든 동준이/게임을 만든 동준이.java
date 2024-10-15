import java.io.*;
import java.util.*;

public class Main {

    static int N, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        int[] score = new int[N];
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(in.readLine());
        }

        int prev = score[N-1];
        for (int i = N-2; i >= 0; i--) {
            if (prev <= score[i]) {
                answer += score[i]-prev+1;
                prev--;
            } else prev = score[i];
        }
        System.out.println(answer);
    }
}
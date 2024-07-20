import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int answer = 0;

        for (int i = 0; i < N; i++) {
            String s = in.readLine();
            Stack<Character> stack = new Stack<>();
            for (char c: s.toCharArray()) {
                if (!stack.isEmpty() && stack.peek() == c) stack.pop();
                else stack.push(c);
            }
            if (stack.isEmpty()) answer++;
        }

        System.out.println(answer);
    }
}
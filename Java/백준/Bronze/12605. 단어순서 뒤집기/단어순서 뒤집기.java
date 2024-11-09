import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(in.readLine());

        for (int tc = 1; tc <= N; tc++) {
            sb.append("Case #").append(tc).append(": ");
            String[] input = in.readLine().split(" ");
            for (int i = input.length-1; i >= 0; i--) {
                sb.append(input[i]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
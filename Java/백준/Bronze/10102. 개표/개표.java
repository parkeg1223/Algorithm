import java.io.*;

public class Main {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        String s = in.readLine();
        int[] vote = new int[2];
        for (int i = 0; i < N; i++) {
            vote[s.charAt(i) - 'A']++;
        }

        if (vote[0] > vote[1]) System.out.println("A");
        else if (vote[0] < vote[1]) System.out.println("B");
        else System.out.println("Tie");
    }
}
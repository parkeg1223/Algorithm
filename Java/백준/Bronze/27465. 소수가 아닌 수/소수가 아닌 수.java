import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        if (N <= 2) System.out.println(4);
        else if (N % 2 == 0) System.out.println(N);
        else System.out.println(N+1);
    }
}
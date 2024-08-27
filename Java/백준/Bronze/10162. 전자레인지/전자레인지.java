import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int _300, _60, _10 = 0;

        if (N % 10 != 0) {
            System.out.println(-1);
            return;
        }
        _300 = N/300;
        _60 = (N%300)/60;
        _10 = (N%60)/10;

        System.out.println(_300 + " " + _60 + " " + _10);
    }
}
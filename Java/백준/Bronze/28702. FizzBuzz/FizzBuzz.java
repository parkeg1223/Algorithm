import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        for (int i = 0; i < 3; i++) {
            s = in.readLine();
            if (s.charAt(0) > '9') continue;

            int answer = Integer.parseInt(s) + 3-i;
            if (answer % 15 == 0) System.out.println("FizzBuzz");
            else if (answer % 3 == 0) System.out.println("Fizz");
            else if (answer % 5 == 0) System.out.println("Buzz");
            else System.out.println(answer);
            return;
        }
    }
}
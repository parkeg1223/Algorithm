import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Integer.parseInt(in.readLine()) % 2 == 0 ? "CY" : "SK");
    }
}
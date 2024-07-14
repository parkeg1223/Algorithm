import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] nList = new int[10];
		String s = in.readLine();

		for (char c: s.toCharArray()) {
			nList[c - '0']++;
		}

		int min = Math.min(nList[6], nList[9]);

		int maxVal = nList[0];
		for (int i = 1; i < 10; i++) {
			if (i == 6 || i == 9) continue;
			if (maxVal < nList[i]) {
				maxVal = nList[i];
			}
		}

		int temp = min + (int)Math.ceil(Math.abs(nList[6] - nList[9]) / 2.0);
		System.out.println(Math.max(maxVal, temp));
	}
}
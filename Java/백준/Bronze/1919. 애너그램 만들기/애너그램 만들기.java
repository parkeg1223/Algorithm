import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		String s1 = in.readLine();
		String s2 = in.readLine();

		int[][] alphas = new int[2][26];
		for (char c: s1.toCharArray()) {
			alphas[0][c - 'a']++;
		}

		for (char c: s2.toCharArray()) {
			alphas[1][c - 'a']++;
		}

		for (int j = 0; j < 26; j++) {
			answer += Math.abs(alphas[0][j] - alphas[1][j]);
		}

		System.out.println(answer);
	}
}
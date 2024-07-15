import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String s = in.readLine();
			List<Character> cList = new LinkedList<>();
			int cursor = 0;

			for (char c: s.toCharArray()) {
				if (c == '<') {
					 if (cursor > 0) cursor--;
				} else if (c == '>') {
					if (cursor < cList.size()) cursor++;
				} else if (c == '-') {
					if (!cList.isEmpty() && cursor > 0) {
						cList.remove(cursor-1);
						cursor--;
					}
				} else {
					cList.add(cursor, c);
					cursor++;
				}
			}

            for (Character character : cList) {
                sb.append(character);
            }
			sb.append("\n");


		}
		System.out.println(sb.toString());
	}
}
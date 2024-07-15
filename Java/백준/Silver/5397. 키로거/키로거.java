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
			ListIterator<Character> iter = cList.listIterator();

			for (char c: s.toCharArray()) {
				if (c == '<') {
					 if (iter.hasPrevious()) iter.previous();
				} else if (c == '>') {
					if (iter.hasNext()) iter.next();
				} else if (c == '-') {
					if (iter.hasPrevious()) {
						iter.previous();
						iter.remove();
					}
				} else {
					iter.add(c);
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
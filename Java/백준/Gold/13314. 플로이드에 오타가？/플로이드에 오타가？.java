public class Main {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(100).append("\n");

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (i == j) sb.append("0 ");
                else if (i == 100 | j == 100) sb.append("1 ");
                else sb.append("100 ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
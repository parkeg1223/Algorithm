import java.io.*;
import java.util.*;

public class Main {

    static int n, maxQuality;
    static List<Square[][]> ingrList = new ArrayList<>();

    public static class Square {
        int quality;
        char color;

        public Square(int quality, char color) {
            this.quality = quality;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        simulate();
        System.out.println(maxQuality);
    }

    public static void simulate() {
        int iSize = ingrList.size();
        for (int i = 0; i < iSize; i++) {
            for (int j = 0; j < iSize; j++) {
                if (i/4 == j/4) continue;
                for (int k = 0; k < iSize; k++) {
                    if (k/4 == i/4 || k/4 == j/4) continue;
                    insert(new int[] {i, j, k});
                }
            }
        }
    }

    public static void insert(int[] ingredients) {
        Square[][] kiln = new Square[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                kiln[i][j] = new Square(0, 'W');
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    int[] pos = new int[] {i, j, k};
                    for (int l = 0; l < 3; l++) {
                        Square[][] ingr = ingrList.get(ingredients[l]);
                        int x = pos[l] / 2;
                        int y = pos[l] % 2;
                        for (int m = x; m < x + 4; m++) {
                            for (int n = y; n < y + 4; n++) {
                                kiln[m][n].quality += ingr[m-x][n-y].quality;
                                if (kiln[m][n].quality < 0) kiln[m][n].quality = 0;
                                else if (kiln[m][n].quality > 9) kiln[m][n].quality = 9;
                                if (ingr[m-x][n-y].color != 'W') kiln[m][n].color = ingr[m-x][n-y].color;
                            }
                        }
                    }
                    renewMaxQuality(kiln);

                    for (int l = 0; l < 5; l++) {
                        for (int m = 0; m < 5; m++) {
                            kiln[l][m].quality = 0;
                            kiln[l][m].color = 'W';
                        }
                    }
                }
            }
        }
    }

    public static void renewMaxQuality(Square[][] kiln) {
        int r = 0, b = 0, g = 0, y = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                switch (kiln[i][j].color) {
                    case 'R': r += kiln[i][j].quality; break;
                    case 'B': b += kiln[i][j].quality; break;
                    case 'G': g += kiln[i][j].quality; break;
                    case 'Y': y += kiln[i][j].quality; break;
                    default: break;
                }
            }
        }
//        System.out.println(r + " " + b + " " + g + " " + y);
//        System.out.println(7*r + 5*b + 3*g + 2*y);
        maxQuality = Math.max(maxQuality, 7*r + 5*b + 3*g + 2*y);
    }

    public static Square[][] rotate(Square[][] ingredient, int angle) {
        Square[][] rotated = new Square[4][4];
        if (angle == 90) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    rotated[i][j] = ingredient[3-j][i];
                }
            }
        } else if (angle == 180) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    rotated[i][j] = ingredient[3-i][3-j];
                }
            }
        } else if (angle == 270) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    rotated[i][j] = ingredient[j][3-i];
                }
            }
        }
        return rotated;
    }

    public static void input() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(in.readLine());

        for (int i = 0; i < n; i++) {
            Square[][] ingredient = new Square[4][4];

            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    ingredient[j][k] = new Square(0, 'W');
                }
            }

            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(in.readLine());
                for (int k = 0; k < 4; k++) {
                    ingredient[j][k].quality = Integer.parseInt(st.nextToken());
                }
            }

            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(in.readLine());
                for (int k = 0; k < 4; k++) {
                    ingredient[j][k].color = st.nextToken().charAt(0);
                }
            }

            ingrList.add(ingredient);
            ingrList.add(rotate(ingredient, 90));
            ingrList.add(rotate(ingredient, 180));
            ingrList.add(rotate(ingredient, 270));
        }
    }
}
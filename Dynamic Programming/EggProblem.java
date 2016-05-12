import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Meemaw on 12/05/16.
 */
public class EggProblem {

    private static int formater;

    // used to set formater by number of digits in last floor
    static int numDigits(int x) {
        int count = 0;
        while (x > 0) {
            count++;
            x /= 10;
        }
        return count;
    }


    static void formattedPrint(int[][] tabela) {
        // initial whitespaces
        for (int i = 0; i <= formater; i++) {
            System.out.print(" ");
        }
        int x = 0;
        // prints column numbers
        while (x < tabela[0].length && tabela[tabela.length - 1][x] >= x) {
            System.out.format("%" + formater + "d ", x++);
        }
        System.out.println();
        for (int i = 0; i < tabela.length; i++) {
            // print row numbers
            System.out.format("%" + formater + "d ", tabela[i][0]);
            for (int j = 0; j < tabela[i].length; j++) {
                if (tabela[i][j] >= j)
                    System.out.format("%" + formater + "d ", tabela[i][j]);
            }
            System.out.println();
        }
    }

    static int findNum(int floor, int j, int[][] table) {
        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < floor; i++) {
            int x = table[i][j - 1];
            int y = table[floor - i - 1][j];
            minimum = Math.min(minimum, Math.max(x, y));
        }
        return minimum + 1;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numFloors = sc.nextInt() + 1;
        int k = sc.nextInt();
        formater = numDigits(numFloors);

        int[][] table = new int[numFloors][k];
        Arrays.fill(table[1], 1);
        for (int i = 0; i < numFloors; i++) {
            table[i][0] = i;
        }

        for (int i = 2; i < numFloors; i++) {
            for (int j = 1; j < k; j++) {
                table[i][j] = findNum(i, j, table);
            }
        }

        formattedPrint(table);

    }
}

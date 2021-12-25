import java.util.Scanner;

public class Board {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m ,n;
        m = scanner.nextInt();
        n = scanner.nextInt();
        m+=2;
        n+=2;
        int [][] board = new int[m][n];
        board[0][0] = 100;
        board[0][1] = 100;
        board[1][0] = 100;
        board[1][1] = -100;

        for (int i = 2; i <m ; i++) {
            board[i][0] = scanner.nextInt();
        }

        for (int i = 2; i <m ; i++) {
            board[i][1] = scanner.nextInt();
        }

        for (int i = 2; i <n ; i++) {
            board[0][i] = scanner.nextInt();
        }

        for (int i = 2; i <n ; i++) {
            board[1][i] = scanner.nextInt();
        }

        for (int i = 2; i <m ; i++) {
            for (int j = 2; j <n ; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

        CSP csp = new CSP();
        csp.csp(board , m ,n);

    }
}

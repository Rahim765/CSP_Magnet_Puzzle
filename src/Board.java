import java.util.Scanner;

public class Board {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m ,n;
        m = scanner.nextInt();
        n = scanner.nextInt();
        int [][] board = new int[m+2][n+2];
        board[0][0] = 100;
        board[0][1] = 100;
        board[1][0] = 100;
        board[1][1] = -100;

        for (int i = 2; i <m+2 ; i++) {
            board[i][0] = scanner.nextInt();
        }

        for (int i = 2; i <m+2 ; i++) {
            board[i][1] = scanner.nextInt();
        }

        for (int i = 2; i <n+2 ; i++) {
            board[0][i] = scanner.nextInt();
        }

        for (int i = 2; i <n+2 ; i++) {
            board[1][i] = scanner.nextInt();
        }

        for (int i = 2; i <m+2 ; i++) {
            for (int j = 2; j <n+2 ; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i <m+2 ; i++) {
            for (int j = 0; j <n+2 ; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }




    }
}

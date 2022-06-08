import java.util.Scanner;

public class Board {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m ,n;
        m = scanner.nextInt();
        n = scanner.nextInt();
        m+=2;
        n+=2;
        State [][] board = new State[m][n];
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                board[i][j] = new State();
            }
        }
        board[0][0].price = 100;
        board[0][1].price = 100;
        board[1][0].price = 100;
        board[1][1].price = -100;

        for (int i = 2; i <m ; i++) {
            board[i][0].price = scanner.nextInt();
        }

        for (int i = 2; i <m ; i++) {
            board[i][1].price = scanner.nextInt();
        }

        for (int i = 2; i <n ; i++) {
            board[0][i].price = scanner.nextInt();
        }

        for (int i = 2; i <n ; i++) {
            board[1][i].price = scanner.nextInt();
        }

        for (int i = 2; i <m ; i++) {
            for (int j = 2; j <n ; j++) {
                board[i][j].price = scanner.nextInt();
            }
        }
        System.out.println("salam");

        CSP csp = new CSP();
        csp.csp(board , m ,n);

    }
}

import java.util.Stack;

public class MRV_LCV {

    public static int mrv(State[][] board , int m , int n){

        boolean [][] intial = new boolean[m][n];

        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if (i>=2 && j>=2 && (board[i][j].price!=1 && board[i][j].price!=0)){
                    intial[i][j] = true;
                }
                else{
                    intial[i][j]= false;
                }
            }
        }



        int min = Integer.MAX_VALUE;
        for (int i = 2; i <m ; i++) {
            for (int j = 2; j < n; j++) {
                if (intial[i][j] == false) {
                    if (board[i][j].price == 1) {
                        if (board[i][j].legal_values.size() + board[i + 1][j].legal_values.size() <= min) {
                            min = board[i][j].legal_values.size() + board[i + 1][j].legal_values.size();
                        }
                        intial[i][j] = true;
                        intial[i + 1][j] = true;
                    } else if (board[i][j].price == 0) {
                        if (board[i][j].legal_values.size() + board[i][j + 1].legal_values.size() <= min) {
                            min = board[i][j].legal_values.size() + board[i][j + 1].legal_values.size();
                        }
                        intial[i][j] = true;
                        intial[i][j + 1] = true;
                    }
                }
            }
        }
        return min;
    }

    public static Stack<State[][]> lcv(State[][] board , int m , int n , int index1 , int index2 , Stack<Integer> integerStack){
        Stack<State [][] > states = new Stack<>();

        if (board[index1][index2].price==1){
            int plus = board[index1][0].price;
            int neg = board[index1][1].price;
            if (plus<=neg){
                if (board[index1][index2].legal_values.contains("-") && board[index1+1][index2].legal_values.contains("+")) {
                    board[index1][index2].price = -100;
                    board[index1+1][index2].price = 100;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }
                if (board[index1][index2].legal_values.contains(" ") && board[index1+1][index2].legal_values.contains(" ")) {
                    board[index1][index2].price = 200;
                    board[index1+1][index2].price = 200;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }
                if (board[index1][index2].legal_values.contains("+") && board[index1+1][index2].legal_values.contains("-")) {
                    board[index1][index2].price = 100;
                    board[index1+1][index2].price = -100;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }

                board[index1][index2].price =1;
                board[index1+1][index2].price =1;
            }else{
                if (board[index1][index2].legal_values.contains("+") && board[index1+1][index2].legal_values.contains("-")) {
                    board[index1][index2].price = 100;
                    board[index1+1][index2].price = -100;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }
                if (board[index1][index2].legal_values.contains(" ") && board[index1+1][index2].legal_values.contains(" ")) {
                    board[index1][index2].price = 200;
                    board[index1+1][index2].price = 200;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }
                if (board[index1][index2].legal_values.contains("-") && board[index1+1][index2].legal_values.contains("+")) {
                    board[index1][index2].price = -100;
                    board[index1+1][index2].price = 100;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }
                board[index1][index2].price =1;
                board[index1+1][index2].price =1;
            }
            return states;
        }
        else if (board[index1][index2].price==0){
            int plus = board[0][index2].price;
            int neg = board[1][index2].price;

            if (plus<=neg){
                if (board[index1][index2].legal_values.contains("-") && board[index1][index2+1].legal_values.contains("+")) {
                    board[index1][index2].price = -100;
                    board[index1][index2+1].price = 100;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }
                if (board[index1][index2].legal_values.contains(" ") && board[index1][index2+1].legal_values.contains(" ")) {
                    board[index1][index2].price = 200;
                    board[index1][index2+1].price = 200;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }
                if (board[index1][index2].legal_values.contains("+") && board[index1][index2+1].legal_values.contains("-")) {
                    board[index1][index2].price = 100;
                    board[index1][index2+1].price = -100;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }

                board[index1][index2].price =0;
                board[index1][index2+1].price =0;
            }else{
                if (board[index1][index2].legal_values.contains("+") && board[index1][index2+1].legal_values.contains("-")) {
                    board[index1][index2].price = 100;
                    board[index1][index2+1].price = -100;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }
                if (board[index1][index2].legal_values.contains(" ") && board[index1][index2+1].legal_values.contains(" ")) {
                    board[index1][index2].price = 200;
                    board[index1][index2+1].price = 200;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }
                if (board[index1][index2].legal_values.contains("-") && board[index1][index2+1].legal_values.contains("+")) {
                    board[index1][index2].price = -100;
                    board[index1][index2+1].price = 100;

                    State[][] temp3 = new State[m][n];
                    CSP.copy(board, temp3, m, n);
                    states.push(temp3);
                    integerStack.push(index2);
                    integerStack.push(index1);
                }
                board[index1][index2].price =0;
                board[index1][index2+1].price =0;
            }
            return states;
        }
        return states;
    }



}

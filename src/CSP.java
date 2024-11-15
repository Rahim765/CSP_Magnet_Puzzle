import java.util.ArrayList;
import java.util.Stack;

public class CSP {

    public  void csp(State [][] board , int m, int n){

        Stack<State[][] > arrayList = new Stack<>();
        Stack< Integer > indexes = new Stack<>();
        arrayList =successor(board,m,n,indexes);

        while (!arrayList.isEmpty()){

            State [][] temp = arrayList.pop();
            int index1 , index2;
            index1 = indexes.pop();
            index2 = indexes.pop();

            if (is_winner(temp, m,n) ){
                print_winner(temp, m ,n);
                return;
            }
            else if (ForwardChecking.forward_cheking(temp,m,n, index1 , index2) && not_fail(temp,m,n)){
//for using ac3 you can delete "ForwardChecking.forward_cheking(temp,m,n, index1 , index2)"  from if
                // and writ "AC3.arc_consistancy(board, m, n)"

                Stack<State[][]> stack = new Stack<>();
                stack = successor(temp,m,n, indexes);
                for (int i = 0; i <stack.size() ; i++) {
                    arrayList.push(stack.get(i));
                }
            }
        }


    }
/*
6 6
1 2 3 1 2 1
1 2 1 3 1 2
2 1 2 2 2 1
2 1 2 2 1 2
1 0 0 1 0 0
1 0 0 1 0 0
1 0 0 0 0 1
1 1 0 0 1 1
1 1 0 0 1 1
1 0 0 0 0 1

4 4
1 0 0 0
1 0 0 0
1 0 0 0
0 1 0 0
1 1 0 0
1 1 1 1
0 0 1 1
0 0 0 0
 */

    public static void copy(State[][] board , State [][] board2  , int m , int n){
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                board2[i][j] = new State();
            }
        }
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                board2[i][j].price = board[i][j].price;
                board2[i][j].legal_values.remove("+");
                board2[i][j].legal_values.remove("-");
                board2[i][j].legal_values.remove(" ");
                for (int k = 0; k <board[i][j].legal_values.size() ; k++) {
                    board2[i][j].legal_values.add(board[i][j].legal_values.get(k));
                }
            }
        }
    }


    public Stack<State [][] > successor(State [][] board , int m , int n , Stack<Integer> integerStack ){
        Stack<State [][] > states = new Stack<>();
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

        for (int i = 2; i <m ; i++) {

            for (int j = 2; j <n ; j++) {


                if (board[i][j].price == 1 && intial[i][j] == false) {
                    if (board[i][j].legal_values.size() + board[i+1][j].legal_values.size() ==
                            MRV_LCV.mrv(board,m,n)) {

                        states = MRV_LCV.lcv(board,m,n,i,j,integerStack);
                        intial[i][j] = true;
                        intial[i + 1][j] = true;

                        return states;
                    }else{
                        intial[i][j] = true;
                        intial[i + 1][j] = true;
                    }
                }else if (board[i][j].price == 0 && intial[i][j] == false) {

                    if (board[i][j].legal_values.size() + board[i][j+1].legal_values.size() ==
                            MRV_LCV.mrv(board, m, n)) {

                        states = MRV_LCV.lcv(board,m,n,i,j,integerStack);

                        intial[i][j] = true;
                        intial[i][j + 1] = true;

                        return states;
                    }else {
                        intial[i][j] = true;
                        intial[i][j + 1] = true;

                    }
                }
            }
        }

        return states;
    }

    public boolean is_winner(State [][] board , int m , int n){
        for (int i = 2; i <m ; i++) {
            int plus=0 , neg=0;
            for (int j = 2; j <n ; j++) {
                if (board[i][j].price==100){
                    plus++;
                }
                else if (board[i][j].price==-100){
                    neg++;
                }
            }
            if (plus!=board[i][0].price || neg != board[i][1].price)
                return false;
        }

        for (int i = 2; i <m ; i++) {
            int plus=0 , neg=0;
            for (int j = 2; j <n ; j++) {
                if (board[j][i].price==100){
                    plus++;
                }
                else if (board[j][i].price==-100){
                    neg++;
                }
            }
            if (plus!=board[0][i].price || neg != board[1][i].price)
                return false;
        }

        return true;
    }

    public void print_winner(State [][] board , int m ,int n ){
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if (board[i][j].price==200){
                    System.out.print("@ ");
                }else if (board[i][j].price==100){
                    System.out.print("+ ");
                }else if (board[i][j].price==-100){
                    System.out.print("- ");
                }
                else {
                    System.out.print(board[i][j].price+ " ");
                }
            }
            System.out.println();
        }
    }

    public boolean not_fail(State [][] board , int m ,int n ){

        for (int i = 2; i <m ; i++) {
            int plus=0 , neg=0, init =0;
            for (int j = 2; j <n ; j++) {
                if (board[i][j].price==0 || board[i][j].price==1){
                    init++;
                }
                if (board[i][j].price==100){
                    plus++;
                }
                else if (board[i][j].price==-100){
                    neg++;
                }
            }
            if ( (plus>board[i][0].price || neg > board[i][1].price) || (plus<board[i][0].price && init==0 )
                    || (neg <board[i][1].price  && init==0 )  )
                return false;
        }

        for (int i = 2; i <n ; i++) {
            int plus=0 , neg=0 , init =0;
            for (int j = 2; j <m ; j++) {
                if (board[j][i].price==0 || board[j][i].price==1){
                    init++;
                }
                if (board[j][i].price==100){
                    plus++;
                }
                else if (board[j][i].price==-100){
                    neg++;
                }
            }
            if( (plus>board[0][i].price || neg > board[1][i].price) || (plus<board[0][i].price && init==0) ||
                    (neg<board[1][i].price && init==0))
                return false;
        }


        return true;
    }


}
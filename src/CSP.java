import java.util.ArrayList;
import java.util.Stack;

public class CSP {

    public  void csp(int [][] board , int m, int n){

            Stack<int[][] > arrayList = new Stack<>();
            arrayList =successor(board,m,n);

            while (!arrayList.isEmpty()){

                int [][] temp = arrayList.pop();

                if (is_winner(temp, m,n) ){
                    print_winner(temp, m ,n);
                    return;
                    }
                else if (not_fail(temp,m,n)){
                   Stack<int[][]> stack = new Stack<>();
                   stack = successor(temp,m,n);
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
 */

    public void copy(int[][] board , int [][] board2  , int m , int n){
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                board2[i][j] = board[i][j];
            }
        }
    }


    public Stack<int [][] > successor(int [][] board , int m , int n ){
        Stack<int [][] > states = new Stack<>();
        boolean [][] intial = new boolean[m][n];

        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if (i>=2 && j>=2 && (board[i][j]!=1 && board[i][j]!=0)){
                    intial[i][j] = true;
                }
                else{
                    intial[i][j]= false;
                }
            }
        }

        for (int i = 2; i <m ; i++) {

            for (int j = 2; j <n ; j++) {

                if (board[i][j]==1 && intial[i][j]==false){

                    board[i][j] = 200;
                    board[i+1][j] = 200;
                    int [][] temp3 = new int[m][n];
                    copy(board , temp3 , m ,n);
                    states.push(temp3);

                    board[i][j] = 100;
                    board[i+1][j] =-100;
                    int [][] temp = new int[m][n];
                    copy(board , temp , m ,n);
                    states.push(temp);

                    board[i][j] = -100;
                    board[i+1][j] =100;
                    int [][] temp2 = new int[m][n];
                    copy(board , temp2 , m ,n);
                    states.push(temp2);


                    board[i][j] = 1;
                    board[i+1][j] = 1;

                    intial[i][j] = true;
                    intial[i+1][j] = true;

                    return states;

                }
                else if (board[i][j]==0 && intial[i][j]==false){


                    board[i][j] = 200;
                    board[i][j+1] = 200;
                    int [][] temp3 = new int[m][n];
                    copy(board , temp3 , m ,n);
                    states.push(temp3);


                    board[i][j] = 100;
                    board[i][j+1] =-100;
                    int [][] temp = new int[m][n];
                    copy(board , temp , m ,n);
                    states.push(temp);

                    board[i][j] = -100;
                    board[i][j+1] =100;
                    int [][] temp2 = new int[m][n];
                    copy(board , temp2 , m ,n);
                    states.push(temp2);




                    board[i][j] = 0;
                    board[i][j+1] = 0;

                    intial[i][j] = true;
                    intial[i][j+1] = true;

                    return states;
                }
            }
        }

        return states;
    }



    public boolean is_winner(int [][] board , int m , int n){
        for (int i = 2; i <m ; i++) {
            int plus=0 , neg=0;
            for (int j = 2; j <n ; j++) {
                if (board[i][j]==100){
                    plus++;
                }
                else if (board[i][j]==-100){
                    neg++;
                }
            }
            if (plus!=board[i][0] || neg != board[i][1])
                return false;
        }

        for (int i = 2; i <m ; i++) {
            int plus=0 , neg=0;
            for (int j = 2; j <n ; j++) {
                if (board[j][i]==100){
                    plus++;
                }
                else if (board[j][i]==-100){
                    neg++;
                }
            }
            if (plus!=board[0][i] || neg != board[1][i])
                return false;
        }

        return true;
    }

    public void print_winner(int [][] board , int m ,int n ){
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                    if (board[i][j]==200){
                        System.out.print("@ ");
                    }else if (board[i][j]==100){
                        System.out.print("+ ");
                    }else if (board[i][j]==-100){
                        System.out.print("- ");
                    }
                    else {
                    System.out.print(board[i][j] + " ");
                    }
            }
            System.out.println();
        }
    }

    public boolean not_fail(int [][] board , int m ,int n ){

        for (int i = 2; i <m ; i++) {
            int plus=0 , neg=0, init =0;
            for (int j = 2; j <n ; j++) {
                if (board[i][j]==0 || board[i][j]==1){
                    init++;
                }
                if (board[i][j]==100){
                    plus++;
                }
                else if (board[i][j]==-100){
                    neg++;
                }
            }
            if ( (plus>board[i][0] || neg > board[i][1]) || (plus<board[i][0] && init==0 )
                    || (neg <board[i][1]  && init==0 )  )
                return false;
        }

        for (int i = 2; i <m ; i++) {
            int plus=0 , neg=0 , init =0;
            for (int j = 2; j <n ; j++) {
                if (board[j][i]==0 || board[j][i]==1){
                    init++;
                }
                if (board[j][i]==100){
                    plus++;
                }
                else if (board[j][i]==-100){
                    neg++;
                }
            }
            if( (plus>board[0][i] || neg > board[1][i]) || (plus<board[0][i] && init==0) ||
                    (neg<board[1][i] && init==0))
                return false;
        }


        return true;
    }

}

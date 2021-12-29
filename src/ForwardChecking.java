public class ForwardChecking {

    public static boolean forward_cheking(State [][] board , int m , int n){
        int index1=2 , index2=2;
        outerloop: for (int i = 2; i < m ; i++) {
            for (int j = 2; j < n; j++) {
                if (board[i][j].price==0 || board[i][j].price==1){
                    index1 = i ;
                    index2 = j-1;
                    break outerloop;
                }
            }
        }

        if (check_satr(board, m, n , index1 , index2)==false){
            return false;
        }else if (check_saton(board,m,n,index1,index2)==false){
            return false;
        }else if(index1<m-1 && (board[index1+1][index2].price !=0 || board[index1+1][index2].price !=1 )
                 && check_satr(board, m,n,index1+1, index2) ==false){
            return false;
        }else if (index1<m-1 && (board[index1+1][index2].price ==0 || board[index1+1][index2].price ==1 )&&
                check_saton(board, m, n, index1, index2-1)==false) {
            return false;
        }else if (index1<m-1 && (board[index1+1][index2].price ==0 || board[index1+1][index2].price ==1 )&&
                check_saton(board, m, n, index1, index2-2)==false) {
            return false;
        }


        return true;
    }

    public static boolean check_satr(State [][] board , int m , int n , int index1 , int index2){

        int plus=0 , neg =0;
        for (int i = 2; i < n ; i++) {
            if (board[index1][i].price==100){
                plus++;
            }else if (board[index1][i].price== -100 ){
                neg++;
            }
        }

        if (board[index1][0].price - plus <=0 ){
            for (int i = 2; i <n ; i++) {
                if (board[index1][i].price==0 || board[index1][i].price==1) {
                    board[index1][i].legal_values.remove("+");
                    if (board[index1][i].legal_values.isEmpty()) {
                        return false;
                    }
                }
            }
        }
        if (board[index1][1].price - neg <=0){
            for (int i = 2; i <n ; i++) {
                if (board[index1][i].price==0 || board[index1][i].price==1) {
                    board[index1][i].legal_values.remove("-");
                    if (board[index1][i].legal_values.isEmpty()) {
                        return false;
                    }
                }
            }
        }

//        if ((n-2) - ((index2+1)-2) <= board[index1][0].price - plus ||
//                (n-2) - ((index2+1)-2) <= board[index1][1].price - neg){
//            for (int i = 2; i <n ; i++) {
//                if (board[index1][i].price==0 || board[index1][i].price==1 ) {
//                    board[index1][i].legal_values.remove(" ");
//                    if (board[index1][i].legal_values.isEmpty()) {
//                        return false;
//                    }
//                }
//            }
//        }

        if ( (board[index1][0].price + board[index1][1].price ) -(plus+neg)
                >= ((n-2) - plus+neg)  ){
            for (int i = 2; i <n ; i++) {
                if (board[index1][i].price==0 || board[index1][i].price==1 ) {
                    board[index1][i].legal_values.remove(" ");
                    if (board[index1][i].legal_values.isEmpty()) {
                        return false;
                    }
                }
            }


        }

        return true;
    }

    public static boolean check_saton(State [][] board , int m , int n , int index1 , int index2){
//
        int plus=0 , neg =0;
//        if (index1<m-1 && (board[index1+1][index2].price!=1 || board[index1+1][index2].price!=0 ) ){
//            index1++;
//        }

        for (int i = 2; i < m ; i++) {
            if (board[i][index2].price==100){
                plus++;
            }else if (board[i][index2].price==-100){
                neg++;
            }
        }

        if (board[0][index2].price - plus <=0 ){
            for (int i = 2; i <m ; i++) {
                if (board[i][index2].price==0 || board[i][index2].price==1) {
                    board[i][index2].legal_values.remove("+");
                    if (board[i][index2].legal_values.isEmpty()) {
                        return false;
                    }
                }
            }
        }

        if (board[1][index2].price - neg <=0){
            for (int i = 2; i <m ; i++) {
                if (board[i][index2].price==0 || board[i][index2].price==1) {
                    board[i][index2].legal_values.remove("-");
                    if (board[i][index2].legal_values.isEmpty()) {
                        return false;
                    }
                }
            }
        }

//        if ((m-2) - ((index1+1)-2) <= board[0][index2].price - plus ||
//                (m-2) - ((index1+1)-2) <= board[1][index2].price - neg ){
//            for (int i = 2; i <m ; i++) {
//                if (board[i][index2].price==0 || board[i][index2].price==1) {
//                    board[i][index2].legal_values.remove(" ");
//                    if (board[i][index2].legal_values.isEmpty()) {
//                        return false;
//                    }
//                }
//            }
//        }

        if ( (board[0][index2].price + board[1][index2].price ) -(plus+neg)
                >= ((m-2) - plus+neg)  ){
            for (int i = 2; i <m ; i++) {
                if (board[i][index2].price==0 || board[i][index2].price==1 ) {
                    board[i][index2].legal_values.remove(" ");
                    if (board[i][index2].legal_values.isEmpty()) {
                        return false;
                    }
                }
            }


        }

        return true;

    }


}

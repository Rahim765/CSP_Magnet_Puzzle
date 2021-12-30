public class AC3 {


    public static boolean arc_consistancy(State[][] board , int m ,int n){
        for (int i = 2; i <m ; i++) {
            for (int j = 2; j <n ; j++) {
//here
                if (board[i][j].price==0 ||board[i][j].price==1 ) {
                    int plus = 0, neg = 0 , empty=0;
                    for (int k = 2; k < j; k++) {
                        if (board[i][k].price == 100) {
                            plus++;
                        } else if (board[i][k].price == -100) {
                            neg++;
                        }else if (board[i][k].price==1||board[i][k].price==0){
                            empty++;
                        }
                    }


                    plus = board[i][0].price - plus;
                    neg = board[i][1].price - neg;
                    if (plus < 0 || neg < 0) {
                        return false;
                    }

                    plus--;
                    if (plus == 0) {
                        for (int k = j + 1; k < n; k++) {
                            board[i][k].legal_values.remove("+");
                            if (board[i][k].legal_values.isEmpty()) {
                                return false;
                            }
                        }
                    }
                    for (int k = j + 1; k < n; k++) {
                        board[i][k].legal_values.add("+");
                    }
                    plus++;

                    neg--;

                    if (neg == 0) {
                        for (int k = j + 1; k < n; k++) {
                            board[i][k].legal_values.remove("-");
                            if (board[i][k].legal_values.isEmpty()) {
                                return false;
                            }
                        }
                    }
                    for (int k = j + 1; k < n; k++) {
                        board[i][k].legal_values.add("-");
                    }
                    neg++;

                    empty--;
                    if (empty == plus+neg) {
                        for (int k = j + 1; k < n; k++) {
                            board[i][k].legal_values.remove(" ");
                            if (board[i][k].legal_values.isEmpty()) {
                                return false;
                            }
                        }
                    }
                    for (int k = j + 1; k < n; k++) {
                        board[i][k].legal_values.add(" ");
                    }
                    empty++;



                    int plus2 = 0, neg2 = 0 , empty2=0;

                    for (int k = 2; k < i; k++) {
                        if (board[k][j].price == 100) {
                            plus2++;
                        } else if (board[k][j].price == -100) {
                            neg2++;
                        }else if (board[k][j].price==0 || board[k][j].price==1){
                            empty2++;
                        }
                    }

                    plus2 = board[0][j].price - plus2;
                    neg2 = board[1][j].price - neg2;
                    if (plus2 < 0 || neg2 < 0) {
                        return false;
                    }

                    plus2--;
                    if (plus2 == 0) {
                        for (int k = i + 1; k < m; k++) {
                            board[k][j].legal_values.remove("+");
                            if (board[k][j].legal_values.isEmpty()) {
                                return false;
                            }
                        }
                    }
                    for (int k = i + 1; k < m; k++) {
                        board[k][j].legal_values.add("+");
                    }
                    plus2++;

                    neg2--;

                    if (neg2 == 0) {
                        for (int k = i + 1; k < m; k++) {
                            board[k][j].legal_values.remove("-");
                            if (board[k][j].legal_values.isEmpty()) {
                                return false;
                            }
                        }
                    }
                    for (int k = i + 1; k < m; k++) {
                        board[k][j].legal_values.add("-");
                    }
                    neg2++;

                    empty2--;
                    if (empty2 <=plus2+neg2) {
                        for (int k = i + 1; k < m; k++) {
                            board[k][j].legal_values.remove(" ");
                            if (board[k][j].legal_values.isEmpty()) {
                                return false;
                            }
                        }
                    }
                    for (int k = i + 1; k < m; k++) {
                        board[k][j].legal_values.add(" ");
                    }
                    empty2++;

                }

            }

        }
        return true;

    }
}

class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] rowset=new HashSet[9];
        HashSet<Character>[] colset=new HashSet[9];
        HashSet<Character>[] boxset=new HashSet[9];

        for(int i=0;i<9;i++){
            rowset[i]=new HashSet<>();
            colset[i]=new HashSet<>();
            boxset[i]=new HashSet<>();

        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                char c=board[i][j];
                if(c=='.'){
                    continue;
                }
                if(rowset[i].contains(c)|| colset[j].contains(c)||boxset[(i/3)*3+(j/3)].contains(c)){
                    return false;
                }
                rowset[i].add(c);
                colset[j].add(c);
                boxset[(i/3)*3+(j/3)].add(c);
            }
        }
        return true;
    }
}
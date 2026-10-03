class Solution {
    int res=0;
    int empty=1;

    public void dfs(int[][] grid,int x,int y,int count){
        if(x<0|| x>=grid.length || y<0 || y>=grid[0].length || grid[x][y]==-1){
            return;
        }

        if(grid[x][y]==2){
            if(empty==count){
                res++;
            }
            return;
        }
        grid[x][y]=-1;

        dfs(grid,x+1,y,count+1);
        dfs(grid,x-1,y,count+1);
        dfs(grid,x,y+1,count+1);
        dfs(grid,x,y-1,count+1);

        grid[x][y]=0;
    }
    public int uniquePathsIII(int[][] grid) {
        int start_x=0,start_y=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    start_x=i;
                    start_y=j;
                }
                else if(grid[i][j]==0){
                    empty++;
                }
            }
        }
        dfs(grid,start_x,start_y,0);
        return res;
    }
}
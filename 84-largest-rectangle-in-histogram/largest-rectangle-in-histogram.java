class Solution {
    public int largestRectangleArea(int[] height) {
        int maxArea=0;
        int nsr[] =new int[height.length];
        int nsl[] =new int[height.length];

        Stack<Integer> s=new Stack<>();

        for(int i=height.length-1;i>=0;i--){
            while(!s.isEmpty() && height[s.peek()]>=height[i]){
                s.pop();
            }
            if(s.isEmpty()){
                nsr[i]=height.length;
            }else{
                nsr[i]=s.peek();
            }
            s.push(i);
        }

        s=new Stack<>();

        for(int i=0;i<height.length;i++){
            while(!s.isEmpty() && height[s.peek()]>=height[i]){
                s.pop();
            }
            if(s.isEmpty()){
                nsl[i]=-1;
            }else{
                nsl[i]=s.peek();
            }
            s.push(i);
        }
        for(int i=0;i<height.length;i++){
            int ht=height[i];
            int wt=nsr[i]-nsl[i]-1;
            int currArea=ht*wt;
            maxArea=Math.max(currArea,maxArea);
        }
        return maxArea;
    }
}
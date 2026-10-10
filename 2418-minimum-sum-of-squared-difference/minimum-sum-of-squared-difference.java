class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int[] freq=new int[100001];
        int maxdiff=0;
        long totdiff=0;

        for(int i=0;i<nums1.length;i++){
            int diff=Math.abs(nums1[i]-nums2[i]);
           freq[diff]++;
            totdiff+=diff;

            maxdiff=Math.max(maxdiff,diff);
        }
        long k=(long) k1+k2;

        if(totdiff<=k){
            return 0;
        }
        for(int d=maxdiff;d>0&&k>0;d--){
            int moves=(int)Math.min(k,(long) freq[d]);
            freq[d]-=moves;
            freq[d-1]+=moves;
            k-=moves;
        }
        long answer=0;
        for(int d=1;d<=maxdiff;d++){
            answer+=(long) d*d*freq[d];
        }
        return answer;
    }
}
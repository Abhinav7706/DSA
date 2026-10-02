class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int add) {
        List<Integer> diff=new ArrayList<>();
        int count=0;
        int n=rocks.length;
        for(int i=0;i<n;i++){
            diff.add(capacity[i]-rocks[i]);
        }
        Collections.sort(diff);
        for(int i=0;i<n;i++){
            if(diff.get(i)<=add){
                add=add-diff.get(i);
                count++;
            }
        }
        return count;
    }
}
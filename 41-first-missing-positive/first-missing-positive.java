class Solution {
    public int firstMissingPositive(int[] a) {
        int n=a.length;
        for(int i=0;i<n;i++){
            while(a[i]>0 && a[i]<=n && a[a[i]-1]!=a[i]){
                swap(a,i,a[i]-1);
            }
        }

        for(int i=0;i<n;i++){
                if(a[i]!=i+1){
                    return i+1;
                }
            }
        
        return n+1;
    }
    private void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
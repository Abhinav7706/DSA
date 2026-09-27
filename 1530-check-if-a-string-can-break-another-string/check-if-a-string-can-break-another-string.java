class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        int n=s1.length();
        char[] arr1=s1.toCharArray();
        char[] arr2=s2.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        boolean s1canBreaks2=true;
        boolean s2canBreaks1=true;
        for(int i=0;i<n;i++){
            if(arr1[i]<arr2[i]){
                s1canBreaks2=false;
            }
            if(arr2[i]<arr1[i]){
                s2canBreaks1=false;
            }
        }

        return s2canBreaks1||s1canBreaks2;

    }
}
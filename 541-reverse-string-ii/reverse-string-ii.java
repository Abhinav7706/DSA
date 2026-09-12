class Solution {
    private static void reverse(char[] arr,int start,int end){
            while(start<end){
                char temp=arr[start];
                arr[start]=arr[end-1];
                arr[end-1]=temp;

                start++;
                end--;
            }
    }
    public String reverseStr(String s, int k) {
        int l=0;
        int r=Math.min(k,s.length());
        char[] charArr=s.toCharArray();

        while(l<s.length()){
            reverse(charArr,l,r);
            l=l+2*k;
            r=Math.min(l+k,s.length());
        }
        return new String(charArr);

         
        }
    }

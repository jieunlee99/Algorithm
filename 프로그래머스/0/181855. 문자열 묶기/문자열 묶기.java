class Solution {
    public int solution(String[] strArr) {
        int[] arr = new int[100_001];
        
        int maxValue = 0;
        
        for(String str:strArr) {
            arr[str.length()]++;
            
            if(arr[str.length()] > maxValue) {
                maxValue = arr[str.length()];
            }
        }
        
       return maxValue;
    }
}
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for(int i=0; i<n; i++) {
            String str = Integer.toBinaryString(arr1[i] | arr2[i]);
            if(str.length() < n) {
                str = "0".repeat(n - str.length()) + str;
            }            
            
            StringBuilder sb = new StringBuilder();
            
            for(int j=0; j<n; j++) {
                if(str.charAt(j) == '1') {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();
        }

        return answer;
    }
}
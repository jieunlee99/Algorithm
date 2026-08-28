class Solution {
    public int[] solution(int brown, int yellow) {
        
        int sum = brown + yellow;
        
        for(int i=1; i<=sum; i++) {
            for(int j=1; j<=sum; j++) {
                if(i*j == sum && 2*(i+j-2) == brown) {
                    return new int[] {j, i};
                }
            }
        }
        
        return null;
    }
}
class Solution {
    public int[] solution(int brown, int yellow) {
        int sum = brown + yellow;
        
        for(int i=1; i<=sum; i++) {
            for(int j=1; j<=i; j++) {
                if(sum == i*j && brown == 2*i + 2*(j-2)) {
                    return new int[] {i, j};
                }
            }
        }
        
        return null;
    }
}
class Solution {
    public long solution(int w, int h) {
        long totalArea = (long) w * h;
        long cutSquares = (long) w + h - gcd(w, h);
        
        return totalArea - cutSquares;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
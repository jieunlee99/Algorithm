class Solution {
    public String solution(int a, int b) {
        int day = 0;
        
        if(a > 1) {
            day += 31;
        }
        if(a > 2) {
            day += 29;
        }
        if(a > 3) {
            day += 31;
        }
        if(a > 4) {
            day += 30;
        }
        if(a > 5) {
            day += 31;
        }
        if(a > 6) {
            day += 30;
        }
        if(a > 7) {
            day += 31;
        }
        if(a > 8) {
            day += 31;
        }
        if(a > 9) {
            day += 30;
        }
        if(a > 10) {
            day += 31;
        }
        if(a > 11) {
            day += 30;
        }
        
        day += b;
        
        if(day%7 == 0) {
            return "THU";
        } else if(day%7 == 1) {
            return "FRI";
        } else if(day%7 == 2) {
            return "SAT";
        } else if(day%7 == 3) {
            return "SUN";
        } else if(day%7 == 4) {
            return "MON";
        } else if(day%7 == 5) {
            return "TUE";
        } else {
            return "WED";
        }
    }
}
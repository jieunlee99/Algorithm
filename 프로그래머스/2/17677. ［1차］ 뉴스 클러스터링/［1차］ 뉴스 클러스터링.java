import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);
            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                list1.add("" + c1 + c2);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);
            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                list2.add("" + c1 + c2);
            }
        }

        if (list1.isEmpty() && list2.isEmpty()) return 65536;

        List<String> intersection = new ArrayList<>();
        List<String> union = new ArrayList<>();

        for (String s : list1) {
            if (list2.remove(s)) { 
                intersection.add(s);
            }
            union.add(s);
        }
        
        for (String s : list2) {
            union.add(s);
        }

        double jacard = (double) intersection.size() / union.size();
        return (int) (jacard * 65536);
    }
}
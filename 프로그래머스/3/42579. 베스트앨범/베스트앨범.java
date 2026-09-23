import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        HashMap<String, Integer> num = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();
        
        for(int i = 0; i < plays.length; i++) {
            if(!num.containsKey(genres[i])) {
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);
                music.put(genres[i], map);
                num.put(genres[i], plays[i]);
            } else {
                music.get(genres[i]).put(i, plays[i]);
                num.put(genres[i], num.get(genres[i]) + plays[i]);
            }
        }
        
        List<String> keySet = new ArrayList(num.keySet());
        
        // 장르 재생 수로 내림차순 정렬
        Collections.sort(keySet, (k1, k2) -> num.get(k2)-num.get(k1));
        
        for(String key : keySet) {
            HashMap<Integer, Integer> map = music.get(key);
            List<Integer> genre_key = new ArrayList(map.keySet());
 
            // 장르 내에서 곡 당 재생 수로 내림차순 정렬
            Collections.sort(genre_key, (s1, s2) -> map.get(s2) - (map.get(s1)));
 
            // answer에 0번, 1번을 추가
            answer.add(genre_key.get(0));
            if(genre_key.size() > 1) {
                answer.add(genre_key.get(1));
            }
        }
        
        return answer.stream().mapToInt(i->i).toArray();
    }
}
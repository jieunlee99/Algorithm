import java.util.*;
import java.util.stream.Collectors;

class Solution {
    // 노래 정보를 담는 클래스 생성
    class Music {
        int id; // 인덱스
        int play; // 재생수
        
        Music(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> genrePlaySum = new HashMap<>();
        HashMap<String, List<Music>> genreMap= new HashMap<>();
        
        for(int i=0; i<genres.length; i++) {
            genrePlaySum.put(genres[i], genrePlaySum.getOrDefault(genres[i], 0)+plays[i]);
            
            genreMap.putIfAbsent(genres[i], new ArrayList<>());
            genreMap.get(genres[i]).add(new Music(i, plays[i]));
        }
        
        // 1. 장르별 총 재생 횟수 기준 내림차순 정렬
        List<String> sortedGenres = genrePlaySum.keySet().stream()
            .sorted((o1, o2)->genrePlaySum.get(o2)-genrePlaySum.get(o1))
            .collect(Collectors.toList());
       
        List<Integer> answer = new ArrayList<>();
        
        for(String genre:sortedGenres) {
            List<Music> musics = genreMap.get(genre);
        
            // 2. 장르 내 노래 정렬: 재생 횟수 내림차순 -> 고유 번호 오름차순
            musics.sort((m1, m2)-> {
                if(m1.play == m2.play) {
                    return m1.id - m2.id;
                }
                return m2.play-m1.play;
            });
            
            // 3. 최대 2곡 추출
            answer.add(musics.get(0).id);
            if(musics.size() > 1) {
                answer.add(musics.get(1).id);
            }
        }
        
        return answer.stream().mapToInt(i->i).toArray();
    }
}
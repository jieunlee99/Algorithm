import java.util.*;

class Solution {
    class FileInfo {
        String full;
        String head;
        int number;
        
        public FileInfo(String full, String head, int number) {
            this.full = full;
            this.head = head;
            this.number = number;
        }
    }
    
    public FileInfo parsing(String file) {
        
        StringBuilder head = new StringBuilder();
        StringBuilder number = new StringBuilder();
        
        int idx = 0;
        int n = file.length();
        
        while (idx < n && !Character.isDigit(file.charAt(idx))) {
            head.append(file.charAt(idx++));
        }
        
        while (idx < n && Character.isDigit(file.charAt(idx)) && number.length() < 5) {
            number.append(file.charAt(idx++));
        }
        
        return new FileInfo(file, head.toString(), Integer.parseInt(number.toString()));
    }
    
    public String[] solution(String[] files) {
        List<FileInfo> list = new ArrayList<>();
        
        for(String file:files) {
            list.add(parsing(file));
        }
        
        Collections.sort(list, (a, b)-> {
            if(!a.head.toLowerCase().equals(b.head.toLowerCase())) {
                return a.head.toLowerCase().compareTo(b.head.toLowerCase());
            }
            
            return a.number-b.number;
        });
        
        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i).full;
        }
        return answer;
    }
}
import java.util.*;

class FileInfo implements Comparable<FileInfo> {
    int idx;
    int number;

    String name;
    String head;
    String tail;

    @Override
    public int compareTo(FileInfo other) {

        // 1. HEAD를 대소문자 구분 없이 비교
        int headCompare = this.head.compareToIgnoreCase(other.head);

        if (headCompare != 0) {
            return headCompare;
        }

        // 2. HEAD가 같으면 NUMBER 비교
        int numberCompare = Integer.compare(this.number, other.number);

        if (numberCompare != 0) {
            return numberCompare;
        }

        // 3. HEAD와 NUMBER가 같으면 입력 순서 유지
        return Integer.compare(this.idx, other.idx);
    }
}

class Solution {

    public String[] solution(String[] files) {
        List<FileInfo> list = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            list.add(parseFile(files[i], i));
        }

        Collections.sort(list);

        String[] answer = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            answer[i] = list.get(i).name;
        }

        return answer;
    }

    private FileInfo parseFile(String name, int idx) {
        int length = name.length();

        // 첫 번째 숫자가 나오는 위치
        int numberStart = 0;

        while (numberStart < length
                && !isDigit(name.charAt(numberStart))) {
            numberStart++;
        }

        // NUMBER는 최대 5자리
        int numberEnd = numberStart;

        while (numberEnd < length
                && numberEnd - numberStart < 5
                && isDigit(name.charAt(numberEnd))) {
            numberEnd++;
        }

        FileInfo file = new FileInfo();

        file.idx = idx;
        file.name = name;
        file.head = name.substring(0, numberStart);
        file.number = Integer.parseInt(
                name.substring(numberStart, numberEnd)
        );
        file.tail = name.substring(numberEnd);

        return file;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
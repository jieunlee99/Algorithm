import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> idMap = new HashMap<>(); // uid, nickname
        List<String[]> history = new ArrayList<>(); // uid, Enter/Leave

        for (String r : record) {
            StringTokenizer st = new StringTokenizer(r, " ");

            String cmd = st.nextToken();
            String uid = st.nextToken();

            if (cmd.equals("Enter")) {
                String nickname = st.nextToken();

                idMap.put(uid, nickname);
                history.add(new String[]{uid, cmd});
            }

            else if (cmd.equals("Leave")) {
                history.add(new String[]{uid, cmd});
            }

            else if (cmd.equals("Change")) {
                String nickname = st.nextToken();

                idMap.put(uid, nickname);
            }
        }

        String[] result = new String[history.size()];

        for (int i = 0; i < history.size(); i++) {
            String[] log = history.get(i);

            String uid = log[0];
            String cmd = log[1];
            String nickname = idMap.get(uid);

            if (cmd.equals("Enter")) {
                result[i] = nickname + "님이 들어왔습니다.";
            } else {
                result[i] = nickname + "님이 나갔습니다.";
            }
        }

        return result;
    }
}
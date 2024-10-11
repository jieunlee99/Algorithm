import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P1991/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        Node head = new Node('A', null, null);

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            char value = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            insert(head, value, left, right);
        }

        preOrder(head);
        sb.append("\n");
        inOrder(head);
        sb.append("\n");
        postOrder(head);
        System.out.println(sb);
    }

    static void insert(Node node, char value, char left, char right) {
        if(node.value == value) {
            node.left = (left == '.' ? null : new Node(left, null, null));
            node.right = (right == '.' ? null : new Node(right, null, null));
        } else {
            if(node.left != null) {
                insert(node.left, value, left, right);
            }
            if(node.right != null) {
                insert(node.right, value, left, right);
            }
        }
    }

    static void preOrder(Node node) {
        if(node == null) {
            return;
        }
        sb.append(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    static void inOrder(Node node) {
        if(node == null) {
            return;
        }
        inOrder(node.left);
        sb.append(node.value);
        inOrder(node.right);
    }

    static void postOrder(Node node) {
        if(node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value);
    }
}

class Node {
    char value;
    Node left;
    Node right;

    public Node(char value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
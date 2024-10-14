
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P5639/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));

        String input;
        while(true) {
            input = br.readLine();
            if(input == null || input.isEmpty()) {
                break;
            }
            root.insert(Integer.parseInt(input));
        }

        post(root);

        System.out.print(sb);
    }
    static void post(Node node) {
        if(node == null) {
            return;
        }
        post(node.left);
        post(node.right);
        sb.append(node.value).append("\n");
    }
}

class Node {
    int value;
    Node left, right;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    void insert(int n) {
        if(n < this.value) {
            if(this.left == null) {
                this.left = new Node(n);
            } else {
                this.left.insert(n);
            }
        } else {
            if(this.right == null) {
                this.right = new Node(n);
            } else {
                this.right.insert(n);
            }
        }
    }
}
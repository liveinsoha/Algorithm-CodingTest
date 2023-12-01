package page408트리순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1991_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static Map<Character, Node> nodes = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            nodes.put((char) ('A' + i), new Node((char) ('A' + i)));
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char key = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            Node parent = nodes.get(key);
            if (left != '.') {
                parent.setLeftChild(nodes.get(left));
            }
            if (right != '.') {
                parent.setRightChild(nodes.get(right));
            }
        }
    }

    static void solve() {
        preOrder(nodes.get('A'));
        sb.append("\n");
        inOrder(nodes.get('A'));
        sb.append("\n");
        postOrder(nodes.get('A'));
    }

    static void preOrder(Node node) {
        if (node == null) {
            return;
        }
        sb.append(node.name);
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }
    static void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.leftChild);
        sb.append(node.name);
        inOrder(node.rightChild);
    }
    static void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        sb.append(node.name);
    }

    static class Node {
        char name;
        Node leftChild;
        Node rightChild;

        Node(char name) {
            this.name = name;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        @Override
        public String toString() {
            return String.format("%s : %s : %s", name,
                    leftChild != null ? leftChild.name : "null",
                    rightChild != null ? rightChild.name : "null");
        }
    }
}

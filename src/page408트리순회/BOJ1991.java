package page408트리순회;

import java.util.*;
import java.io.*;

public class BOJ1991 {
    static StringBuilder sb;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static Node[] Nodes;
    static int n;

    public static void main(String[] args) throws Exception {
        solution();
    }

    public static void solution() throws Exception {
        n = Integer.parseInt(br.readLine());
        Nodes = new Node[n + 1];
        saveData();

        sb = new StringBuilder();
        preOrderT(1);
        System.out.println(sb);

        sb = new StringBuilder();
        inOrderT(1);
        System.out.println(sb);

        sb = new StringBuilder();
        postOrderT(1);
        System.out.println(sb);
    }

    public static void saveData() throws Exception {
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = getIdx(st.nextToken().charAt(0));
            Nodes[idx] = new Node(st.nextToken().charAt(0), st.nextToken().charAt(0));
        }
    }

    static void preOrderT(int i) {
        sb.append(getData(i));
        if (Nodes[i].left != '.')
            preOrderT(getIdx(Nodes[i].left));
        if (Nodes[i].right != '.')
            preOrderT(getIdx(Nodes[i].right));
    }

    static void inOrderT(int i) {
        if (Nodes[i].left != '.')
            inOrderT(getIdx(Nodes[i].left));

        sb.append(getData(i));

        if (Nodes[i].right != '.')
            inOrderT(getIdx(Nodes[i].right));
    }

    static void postOrderT(int i) {
        if (Nodes[i].left != '.')
            postOrderT(getIdx(Nodes[i].left));
        if (Nodes[i].right != '.')
            postOrderT(getIdx(Nodes[i].right));
        sb.append(getData(i));
    }

    static char getData(int i) {
        return (char) (i + 'A' - 1);
    }

    public static int getIdx(char c) {
        return c - 'A' + 1;
    }

    static class Node {
        char left;
        char right;

        Node(char left, char right) {
            this.left = left;
            this.right = right;
        }
    }
}

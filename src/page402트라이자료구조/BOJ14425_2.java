package page402트라이자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14425_2 {
    static Node node = new Node();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int count;

    public static void main(String[] args) throws IOException {

        solution();
        System.out.println(count);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            node.save(br.readLine(), 0);
        }
        count = 0;
    }

    static void solve() throws IOException {
        for (int i = 0; i < m; i++) {
            if (node.contain(br.readLine(), 0)) {
                count++;
            }
        }
    }

    static class Node {
        Node[] nodes = new Node[26];
        boolean isEnd;

        void save(String str, int idx) {
            int i = str.charAt(idx) - 'a';
            if (nodes[i] == null) {
                nodes[i] = new Node();
            }
            if (idx == str.length() - 1) {
                isEnd = true;
                return;
            }
            nodes[i].save(str, idx + 1);

        }

        boolean contain(String str, int idx) {
            int i = str.charAt(idx) - 'a';
            if (nodes[i] != null) {
                if (idx == str.length() - 1) {
                    return isEnd;
                }
                return nodes[i].contain(str, idx + 1);
            }
            return false;
        }
    }
}

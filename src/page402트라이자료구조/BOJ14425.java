package page402트라이자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14425 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static Root root = new Root();
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
            root.save(br.readLine());
        }

        count = 0;
    }

    static void solve() throws IOException {
        for (int i = 0; i < m; i++) {
            if (root.contain(br.readLine())) {
                count++;
            }
        }
    }

    static class Root {
        Map<Character, Node> nodes = new HashMap<>();

        void save(String str) {
            if (nodes.containsKey(str.charAt(0))) {
                nodes.get(str.charAt(0)).save(str);
            } else {
                Node newNode = new Node();
                newNode.save(str);
                nodes.put(str.charAt(0), newNode);
            }
        }

        boolean contain(String str) {
            if (nodes.containsKey(str.charAt(0))) {
                return nodes.get(str.charAt(0)).contain(String.copyValueOf(str.toCharArray(), 1, str.length() - 1));
            }
            return false;
        }
    }

    static class Node {
        Character c;
        Map<Character, Node> nodes = new HashMap<>();
        boolean isEnd = false;

        boolean contain(String str) {
            if (nodes.containsKey(str.charAt(0))) {
                if (str.length() == 1) {
                    return nodes.get(str.charAt(0)).isEnd;
                }
                return nodes.get(str.charAt(0)).contain(String.copyValueOf(str.toCharArray(), 1, str.length() - 1));
            }
            return false;
        }

        void save(String str) {
            if (this.c == null) {
                this.c = str.charAt(0);
            }
            if (str.length() == 1) {
                isEnd = true;
                return;
            }
            String str1 = String.copyValueOf(str.toCharArray(), 1, str.length() - 1);

            if (nodes.containsKey(str1.charAt(0))) {
                nodes.get(str1.charAt(0)).save(str1);
            } else {
                Node newNode = new Node();
                newNode.save(str1);
                nodes.put(str1.charAt(0), newNode);
            }
        }
    }
}

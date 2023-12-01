package page385;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ1414_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Edge> edges = new PriorityQueue<>();
    static int[] unf;
    static int count;
    static int needLen;
    static int totalLen;
    static int n;

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
        totalLen = 0;
        needLen = 0;
        count = 0;
        for (int i = 1; i <= n; i++) {
            char[] charArr = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (charArr[j] >= 'a' && charArr[j] <= 'z') {
                    edges.add(new Edge(i, j + 1, charArr[j] - 'a' + 1));
                    totalLen += charArr[j] - 'a' + 1;
                } else if (charArr[j] >= 'A' && charArr[j] <= 'Z') {
                    edges.add(new Edge(i, j + 1, charArr[j] - 'A' + 27));
                    totalLen += charArr[j] - 'A' + 27;
                }
            }
        }

        unf = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            unf[i] = i;
        }
    }

    static void solve() {
        if (spanningTree()) {
            sb.append(totalLen - needLen);
        } else {
            sb.append(-1);
        }

    }

    static boolean spanningTree() {
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (find(edge.start) == find(edge.end)) {
                continue;
            }
            union(edge.start, edge.end);
            count++;
            needLen += edge.len;
            if (count == n - 1) {
                break;
            }
        }
        if (count == n - 1) {
            return true;
        }
        return false;
    }

    static int find(int x) {
        if (unf[x] == x) {
            return unf[x];
        }
        return unf[x] = find(unf[x]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int len;

        Edge(int start, int end, int len) {
            this.start = start;
            this.end = end;
            this.len = len;
        }

        @Override
        public int compareTo(Edge that) {
            return this.len - that.len;
        }
    }
}

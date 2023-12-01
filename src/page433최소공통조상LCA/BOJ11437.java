package page433최소공통조상LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11437 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] parents;
    static int[] depths;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;

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

        visited = new boolean[n + 1];
        parents = new int[n + 1];
        depths = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        dfsToFindParentAndDepth(1, 0);
    }

    static void solve() throws IOException {
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(getLCA(a, b)).append("\n");
        }
    }

    static int getLCA(int a, int b) {
        if (depths[a] > depths[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (depths[a] != depths[b]) {
            b = parents[b];
        }
        while (a != b) {
            a = parents[a];
            b = parents[b];
        }
        return a;
    }

    static void dfsToFindParentAndDepth(int me, int depth) {
        visited[me] = true;
        depths[me] = depth;
        for (int i : graph[me]) {
            if (!visited[i]) {
                parents[i] = me;
                dfsToFindParentAndDepth(i, depth + 1);
            }
        }
    }
}

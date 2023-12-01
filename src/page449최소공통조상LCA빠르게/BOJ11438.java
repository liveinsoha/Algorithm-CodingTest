package page449최소공통조상LCA빠르게;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11438 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;

    static boolean[] visited;
    static int[] depths;
    static int[][] parents;
    static int[] parent;
    static ArrayList<Integer>[] graph;
    static int maxDepth;
    static int maxK;

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
        parent = new int[n + 1];
        depths = new int[n + 1];
        visited = new boolean[n + 1];
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
        maxDepth = 0;
        dfsToFindParentAndDepth(1, 0);

        maxK = getK(maxDepth);
        parents = new int[maxK + 1][n + 1];
        parents[0] = parent;
        initParentArr();
    }

    static void initParentArr() {
        for (int i = 1; i <= maxK; i++) {
            for (int j = 1; j <= n; j++) {
                parents[i][j] = parents[i - 1][parents[i - 1][j]];
            }
        }
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
        int diff;
        while ((diff = depths[b] - depths[a]) > 0) {
            b = parents[getK(diff)][b];
        }
        int k = getK(depths[a]);
        for (int i = k; i >= 0; i--) {
            if (parents[i][a] != parents[i][b]) {
                a = parents[i][a];
                b = parents[i][b];
            }
        }
        if (a == b) {
            return a;
        }
        return parents[0][a];
    }

    static int getK(int depth) {
        return getExponentOf2(depth);
    }


    static int getExponentOf2(int n) {
        int exponent = 0;
        while (n >= 2) {
            n = n / 2;
            exponent++;
        }
        return exponent;
    }


    static void dfsToFindParentAndDepth(int me, int depth) {
        visited[me] = true;
        depths[me] = depth;
        maxDepth = Math.max(maxDepth, depth);
        for (int i : graph[me]) {
            if (!visited[i]) {
                parent[i] = me;
                dfsToFindParentAndDepth(i, depth + 1);
            }
        }
    }
}

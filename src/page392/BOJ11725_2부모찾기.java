package page392;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11725_2부모찾기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] parents;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

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
        graph = new ArrayList[n + 1];
        parents = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
    }

    static void solve() {
        dfs(1, 0);
        for (int i = 2; i <= n; i++) {
            sb.append(parents[i]).append("\n");
        }
    }

    static void dfs(int me, int parent) {
        visited[me] = true;
        parents[me] = parent;
        for (int i : graph[me]) {
            if (!visited[i])
                dfs(i, me);
        }
    }
}

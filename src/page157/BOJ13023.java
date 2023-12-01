package page157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13023 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int m;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static boolean yes;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        visited = new boolean[n];
        yes = false;
    }

    static void solve() {
        for (int i = 0; i < n; i++) {
            if (!yes) {
                dfs(i, 0);
            }
        }
        if (yes) {
            sb.append(1);
            return;
        }
        sb.append(0);
    }

    static void dfs(int s, int depth) {
        visited[s] = true;
        if (depth == 4) {
            yes = true;
            visited[s] = false;
            return;
        }
        for (int i : graph[s]) {
            if (!visited[i] && !yes) {
                dfs(i, depth + 1);
            }
        }
        visited[s] = false;
    }


}

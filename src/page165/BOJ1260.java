package page165;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Queue<Integer> q = new LinkedList<>();
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int m;
    static int s;

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
        s = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i]);
        }

    }

    static void solve() {
        dfs(s);
        sb.append("\n");
        visited = new boolean[n + 1];
        bfs(s);
        sb.append("\n");
    }

    static void bfs(int s) {
        q.add(s);
        while (!q.isEmpty()) {
            int d = q.poll();
            if (visited[d]) {
                continue;
            }
            visited[d] = true;
            sb.append(d).append(" ");

            for (int i : graph[d]) {
                if (!visited[i]) {
                    q.add(i);
                }
            }
        }


    }

    static void dfs(int s) {
        visited[s] = true;
        sb.append(s).append(" ");
        for (int i : graph[s]) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

}

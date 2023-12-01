package page175;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1167_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static boolean[] visited;
    static ArrayList<int[]>[] graph;
    static int maxLen;
    static int endOfV;
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
        visited = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            while (true) {
                int e = Integer.parseInt(st.nextToken());
                if (e == -1) {
                    break;
                }
                int w = Integer.parseInt(st.nextToken());
                graph[s].add(new int[]{e, w});
            }
        }
        maxLen = 0;
    }

    static void solve() {
        dfs(1, 0);
        maxLen = 0;
        visited = new boolean[n + 1];
        dfs(endOfV, 0);

        sb.append(maxLen);
    }

    static void dfs(int s, int len) {
        if (visited[s])
            return;
        visited[s] = true;
        int child = 0;
        for (int[] next : graph[s]) {
            if (!visited[next[0]]) {
                child++;
                dfs(next[0], len + next[1]);
            }
        }
        if (child == 0 && len > maxLen) {
            maxLen = len;
            endOfV = s;
        }
        visited[s] = false;
    }
}
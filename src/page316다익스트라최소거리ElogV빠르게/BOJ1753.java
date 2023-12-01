package page316다익스트라최소거리ElogV빠르게;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1753 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int v;
    static int e;

    static Queue<int[]> q = new LinkedList<>();
    static boolean[] visited;
    static int startV;
    static ArrayList<int[]>[] graph;
    static int[] minWeight;

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
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        startV = Integer.parseInt(br.readLine());

        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[v + 1];
        minWeight = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            minWeight[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, w});
        }
    }

    static void solve() {
        //dfs(startV, 0);
        bfs();
        for (int i = 1; i <= v; i++) {
            if (i == startV) {
                sb.append(0).append("\n");
            } else if (minWeight[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
            } else {
                sb.append(minWeight[i]).append("\n");
            }
        }

    }



    static void dfs(int s, int w) {
        if (w >= minWeight[s]) {
            visited[s] = false;
            return;
        }
        visited[s] = true;
        minWeight[s] = w;
        for (int[] next : graph[s]) {
            if (!visited[next[0]]) {
                dfs(next[0], w + next[1]);
            }
        }
        visited[s] = false;
    }

    static void bfs() {

        q.add(new int[]{startV, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (now[1] >= minWeight[now[0]]) {
                continue;
            }
            minWeight[now[0]] = now[1];
            for (int[] next : graph[now[0]]) {
                if (now[1] + next[1] < minWeight[next[0]]) {
                    q.add(new int[]{next[0], now[1] + next[1]});
                }
            }
        }
    }
}



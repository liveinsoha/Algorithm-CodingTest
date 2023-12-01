package page319;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon1753_2 { //PQ 사용하지 않으면 오래걸림
    static boolean[] visited;
    static int[] minDistance;
    static ArrayList<Edge>[] graph;
    static int V;

    public static void main(String[] args) throws Exception {
        solution();
    }

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        visited = new boolean[V + 1];
        minDistance = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            minDistance[i] = Integer.MAX_VALUE;
        }

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, w));
        }
        minDistance[k] = 0;
        dijkstra(k, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (minDistance[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
            } else {
                sb.append(minDistance[i]).append("\n");
            }
        }

        System.out.println(sb);


    }

    public static void dijkstra(int k, int w) {
        visited[k] = true;

        for (Edge next : graph[k]) {
            if (next.w + w < minDistance[next.v]) {
                minDistance[next.v] = next.w + w;
            }
        }

        int min = Integer.MAX_VALUE;
        int idx = 0;
        for (int i = 1; i <= V; i++) {
            if (minDistance[i] < min && !visited[i]) {
                min = minDistance[i];
                idx = i;
            }
        }
        if (idx == 0)
            return;
        dijkstra(idx, min);

    }

    public static class Edge {
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}

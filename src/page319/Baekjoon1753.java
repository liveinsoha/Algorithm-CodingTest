package page319;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1753 {

    static boolean[] visited;
    static int[] minDistance;
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

    public static void main(String[] args) throws Exception {
        solution();
    }

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
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

        pq.add(new Edge(k, 0));
        minDistance[k] = 0;
        dijkstra();

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

    public static void dijkstra() {

        while (!pq.isEmpty()) {

            Edge now = pq.poll();
            int nowV = now.v;
            int nowW = now.w;

            for (Edge next : graph[nowV]) {
                if (nowW + next.w < minDistance[next.v]) {
                    minDistance[next.v] = nowW + next.w;
                    pq.add(new Edge(next.v, nowW + next.w));
                }
            }
        }


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

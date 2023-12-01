package page324다익스트라한정점최소거리양수만;

import java.io.*;
import java.util.*;

public class Baekjoon1916 {

    static boolean[] visited;
    static int[] minD;
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
    static int end;

    public static void main(String[] args) throws Exception {
        solution();
    }

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        minD = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            minD[i] = Integer.MAX_VALUE;
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
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        pq.add(new Edge(start, 0));
        System.out.println(dijkstra(start));
    }

    public static int dijkstra(int s) {
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (now.v == end)
                return now.w;
            int v = now.v;
            int w = now.w;

            for (Edge next : graph[v]) {
                if (w + next.w < minD[next.v]) {
                    minD[next.v] = w + next.w;
                    pq.add(new Edge(next.v, w + next.w));
                }
            }
        }

        throw new IllegalStateException();
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

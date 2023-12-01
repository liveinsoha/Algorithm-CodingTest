package page339음수가중치있는경우벨만포드사이클확인;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11657_2 { //음의 가중치가 있을 경우 최단거리를 구할 수 있는 벨만 포드 알고리즘
    //음수사이클이 있는 경우 언더플로우 발생. 최대 크기 500인 간선에 대해 한 사이클 돌면 500*10000만큼 작아진다.
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static List<Edge> edges = new ArrayList<>();
    static int v;
    static int e;
    static long[] distance;

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

        distance = new long[v + 1];
        for (int i = 2; i <= v; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(s, e, w));
        }
    }

    static void solve() {
        if (bellmanFord()) {
            getDistances();
        } else {
            sb.append(-1);
        }
    }

    static void getDistances() {
        for (int i = 2; i <= v; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(distance[i]).append("\n");
            }
        }
    }

    static boolean bellmanFord() {
        for (int i = 1; i <= v - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.start] != Integer.MAX_VALUE && distance[edge.start] + edge.weight < distance[edge.end]) {
                    distance[edge.end] = distance[edge.start] + edge.weight;
                }
            }
        }
        for (Edge edge : edges) {
            if (distance[edge.start] != Integer.MAX_VALUE && distance[edge.start] + edge.weight < distance[edge.end]) {
                return false;
            }
        }
        return true;
    }

    static class Edge {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "start : " + start + " end : " + end + " weight : " + weight;
        }
    }
}

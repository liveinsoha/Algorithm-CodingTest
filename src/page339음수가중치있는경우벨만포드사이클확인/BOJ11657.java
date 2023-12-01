package page339음수가중치있는경우벨만포드사이클확인;

import java.io.*;
import java.util.*;

public class BOJ11657 { //한 싸이클에 모든 간선을 다 돈다. 연결 점이 1,2에 거리가 10000인 것 6000개면 한 싸이클에 6*10^7증가한다.
    static List<Edge> edgeList = new ArrayList<>();
    static int V;
    static long[] minD;

    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        solution();
    }

    public static void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        minD = new long[V + 1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(s, e, w));
        }

        for (int i = 1; i <= V; i++) {
            minD[i] = INF;
        }

        StringBuilder sb = new StringBuilder();

        minD[1] = 0;

        if (bellmanford()) {
            for (int i = 2; i <= V; i++) {
                if (minD[i] == INF)
                    sb.append(-1).append("\n");
                else
                    sb.append(minD[i]).append("\n");
            }
            System.out.println(sb);
        } else
            System.out.println(-1);

    }

    private static boolean bellmanford() {

        for (int i = 1; i <= V; i++) {

            for (Edge edge : edgeList) {

                if (minD[edge.s] == INF)
                    continue;

                if (minD[edge.s] + edge.w < minD[edge.e]) {
                    minD[edge.e] = minD[edge.s] + edge.w;

                    if (i == V) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static class Edge {
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}

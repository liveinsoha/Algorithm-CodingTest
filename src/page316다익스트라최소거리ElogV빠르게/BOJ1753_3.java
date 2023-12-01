package page316다익스트라최소거리ElogV빠르게;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1753_3 { //한 점으로부터 최단 경로를 찾는 알고리즘 : 다익스트라
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int v;
    static int e;

    static PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> {
        if (arr1[1] == arr2[1]) {
            return arr1[0] - arr2[0];
        }
        return arr1[1] - arr2[1];
    });

    static int[] inDegree;
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
        inDegree = new int[v + 1];

        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }
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
            inDegree[b]++;
        }
    }

    static void solve() {
        dijkstra();
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

    static void dijkstra() {
        pq.add(new int[]{startV, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            if (now[1] > minWeight[now[0]]) {
                continue;
            }
            for (int[] next : graph[now[0]]) {
                if (now[1] + next[1] < minWeight[next[0]]) {
                    minWeight[next[0]] = now[1] + next[1];
                    pq.add(new int[]{next[0], now[1] + next[1]});
                }
            }
        }

    }
}



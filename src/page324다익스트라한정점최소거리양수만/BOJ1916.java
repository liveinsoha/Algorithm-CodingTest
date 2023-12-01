package page324다익스트라한정점최소거리양수만;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int v;
    static int e;

    static int startV;
    static int endV;
    static int[] minWeight;
    static ArrayList<int[]>[] graph;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((edge1, edge2) -> {
        if (edge1[1] == edge2[1]) {
            return edge1[0] - edge2[0];
        }
        return edge1[1] - edge2[1];
    });

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        v = Integer.parseInt(br.readLine());
        e = Integer.parseInt(br.readLine());

        minWeight = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            minWeight[i] = Integer.MAX_VALUE;
        }
        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, w});
        }

        st = new StringTokenizer(br.readLine());
        startV = Integer.parseInt(st.nextToken());
        endV = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        dijkstra();
    }

    static void dijkstra() {
        pq.add(new int[]{startV, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            if (now[1] > minWeight[now[0]]) {
                continue;
            }
            if (now[0] == endV) {
                sb.append(now[1]);
                return;
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


package page370최소신장트리엣지리스트작은거부터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1197_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static PriorityQueue<Edge> edges = new PriorityQueue<>();
    static int count;
    static long weightSum;
    static int v;
    static int e;
    static int[] unf;

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

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, weight));
        }

        unf = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            unf[i] = i;
        }
        count = 0;
        weightSum = 0;
    }

    static void solve() {
        spanningTree();
        sb.append(weightSum);
    }

    static void spanningTree() {
        while (!edges.isEmpty()) {

            Edge edge = edges.poll();
            if (find(edge.start) == find(edge.end)) {
                continue;
            }
            union(edge.start, edge.end);
            weightSum += edge.weight;
            count++;

            if (count == v - 1) {
                break;
            }
        }
    }

    static int find(int x) {
        if (x == unf[x]) {
            return x;
        }
        return unf[x] = find(unf[x]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            unf[fb] = fa;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }
}

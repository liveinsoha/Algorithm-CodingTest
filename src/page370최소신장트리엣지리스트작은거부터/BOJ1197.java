package page370최소신장트리엣지리스트작은거부터;

import java.io.*;
import java.util.*;

public class BOJ1197 {
    static int V;
    static List<Edge> edgeList = new ArrayList<>();
    static int[] unf;

    public static void main(String[] args) throws Exception {
        solution();
    }

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        unf = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            unf[i] = i;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(s, e, w));
        }

        Collections.sort(edgeList); //w기준 오름차순 정렬
        System.out.println(mst());

    }

    static void printUNF() {
        System.out.println(Arrays.toString(unf));
    }

    private static void printAll() {
        StringBuilder sb = new StringBuilder();
        for (Edge e : edgeList) {
            sb.append(e.w).append(" ");
        }
        System.out.println(sb);
    }

    static long mst() {
        int count = 0;
        long sum = 0;

        for (Edge e : edgeList) {
            if (find(e.s) == find(e.e))
                continue;

            union(e.s, e.e);
            count++;
            sum += e.w;

            if (count == V - 1)
                break;
        }
        return sum;
    }

    static int find(int x) {
        if (unf[x] == x)
            return x;
        return unf[x] = find(unf[x]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    static class Edge implements Comparable {
        int s;
        int e;
        int w;

        Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        public int compareTo(Object o) {
            Edge edge = (Edge) o;
            return this.w - edge.w;
        }

    }
}

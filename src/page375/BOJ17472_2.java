package page375;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17472_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static val[][] unf;
    static int[][] map;
    static PriorityQueue<Edge> edges = new PriorityQueue<>();
    static Set<val> vals = new HashSet<>();
    static int numOfIsland;
    static int lenSum;

    public static void main(String[] args) throws IOException {
    /*    init();


        for (Edge edge : edges) {
            System.out.println(edge);
        }
        System.out.println(numOfIsland);
        for (val v : vals) {
            System.out.println(v);
        }*/
        solution();
        //printUnf();
        System.out.println(sb);
    }

    static void printUnf() {
        sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sb.append(unf[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        if (solve()) {
            sb.append(lenSum);
        } else {
            sb.append(-1);
        }
    }

    static boolean solve() {
        int count = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (!find(edge.start[0], edge.start[1]).equals(find(edge.end[0], edge.end[1]))) {
                union(edge.start[0], edge.start[1], edge.end[0], edge.end[1]);
                lenSum += edge.len;
                count++;
            }
            if (count == numOfIsland - 1) {
                break;
            }
        }
        if (count == numOfIsland - 1) {
            return true;
        }
        return false;
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        unf = new val[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 1)
                    unf[i][j] = new val(i, j);
            }
        }
        initUnion();
        numOfIsland = 0;
        countIsland();
        lenSum = 0;

        for (int j = 1; j <= m; j++) {
            getVerticalEdges(j);
        }
        for (int i = 1; i <= n; i++) {
            getHorizontalEdges(i);
        }

    }

    static void initUnion() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                union(i, j, i, j + 1);
                union(i, j, i + 1, j);
            }
        }
    }

    static void countIsland() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (find(i, j) != null) {
                    vals.add(find(i, j));
                }
            }
        }
        numOfIsland = vals.size();
    }

    static void getVerticalEdges(int j) {
        for (int i = 1; i <= n - 3; i++) {
            if (map[i][j] == 1 && map[i + 1][j] == 0) {
                int k = i;
                int len = 0;
                while (map[1 + k][j] == 0 && 1 + k < n) {
                    k++;
                    len++;
                }
                if (map[1 + k][j] == 1 && len >= 2 && !find(i, j).equals(find(k + 1, j))) {
                    edges.add(new Edge(new int[]{i, j}, new int[]{k + 1, j}, len));
                }
            }
        }
    }

    static void getHorizontalEdges(int i) {
        for (int j = 1; j <= m - 3; j++) {
            if (map[i][j] == 1 && map[i][j + 1] == 0) {
                int k = j;
                int len = 0;
                while (map[i][1 + k] == 0 && 1 + k < m) {
                    k++;
                    len++;
                }
                if (map[i][1 + k] == 1 && len >= 2 && !find(i, j).equals(find(i, 1 + k))) {
                    edges.add(new Edge(new int[]{i, j}, new int[]{i, 1 + k}, len));
                }
            }
        }
    }


    static val find(int i, int j) {
        if (map[i][j] == 0) {
            return null;
        }
        if (unf[i][j].i == i && unf[i][j].j == j) {
            return unf[i][j];
        }
        return unf[i][j] = find(unf[i][j].i, unf[i][j].j);
    }

    static void union(int i, int j, int p, int q) {
        if (i < 1 || i > n || j < 1 || j > m || p < 1 || p > n || q < 1 || q > m) {
            return;
        }
        val valA = find(i, j);
        val valB = find(p, q);

        if (valA == null || valB == null) {
            return;
        }
        if (!valA.equals(valB)) {
            unf[valA.i][valA.j] = valB;
        }
    }

    static class Edge implements Comparable<Edge> {
        int[] start;
        int[] end;
        int len;

        Edge(int[] start, int[] end, int len) {
            this.start = start;
            this.end = end;
            this.len = len;
        }

        @Override
        public String toString() {
            return Arrays.toString(start) + Arrays.toString(end) + " " + len;
        }

        @Override
        public int compareTo(Edge that) {
            return this.len - that.len;
        }
    }

    static class val {
        int i;
        int j;

        val(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (o == null || o.getClass() != getClass()) {
                return false;
            }
            val that = (val) o;
            return this.i == that.i && this.j == that.j;
        }

        @Override
        public String toString() {
            return "[" + i + ", " + j + "]";
        }
    }
}

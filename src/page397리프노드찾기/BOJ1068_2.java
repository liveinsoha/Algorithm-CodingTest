package page397리프노드찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1068_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] parents;
    static int leafCount;
    static int rootNode;
    static int removeNode;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        parents = new int[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int me = 0; me < n; me++) {
            parents[me] = Integer.parseInt(st.nextToken());
            if (parents[me] == -1) {
                rootNode = me;
                continue;
            }
            graph[parents[me]].add(me);
        }
        removeNode = Integer.parseInt(br.readLine());
    }

    static void solve() {
        dfsForVisit(removeNode);
        if (removeNode == rootNode) {
            sb.append(0);
            return;
        }
        dfsForCountLeaf(rootNode);
        sb.append(leafCount);
    }

    static void dfsForVisit(int s) {
        visited[s] = true;
        for (int i : graph[s]) {
            dfsForVisit(i);
        }
    }

    static void dfsForCountLeaf(int s) {
        boolean leaf = true;
        visited[s] = true;

        for (int i : graph[s]) {
            if (!visited[i]) {
                leaf = false;
                dfsForCountLeaf(i);
            }
        }
        if (leaf) {
            leafCount++;
        }
    }
}

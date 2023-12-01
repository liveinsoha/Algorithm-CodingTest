package page269;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1707 {
    //리셋해주는 걸 주의하자.
    //섬을 찾아도 되지만, 방문하지 않은 정점에 대해서 다시 bipartite를 수행해도 된다.
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static boolean[] visited;
    static boolean[] color;
    static Queue<Integer> landQ = new LinkedList<>();
    static Queue<Node> q = new LinkedList<>();
    static ArrayList<Integer>[] graph;
    static List<Integer> island = new ArrayList<>();
    static int T;
    static int v;
    static int e;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            solution();
        }
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        island.clear();
        landQ.clear();
        q.clear();
        graph = new ArrayList[v + 1];
        visited = new boolean[v + 1];
        color = new boolean[v + 1];

        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
    }

    static void solve() {
        getIsland();
        visited = new boolean[v + 1];
        if (island.stream().allMatch(i -> bfsBipartite(i))) {
            sb.append("YES").append("\n");
        } else {
            sb.append("NO").append("\n");
        }
    }

    static boolean bfsBipartite(int s) {
        q.add(new Node(s, true));
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (visited[now.v]) {
                if (color[now.v] != now.color) {
                    return false;
                }
                continue;
            } else {
                visited[now.v] = true;
                color[now.v] = now.color;
            }
            for (int i : graph[now.v]) {
                if (visited[i]) {
                    if (color[i] == now.color) {
                        return false;
                    }
                    continue;
                } else {
                    q.add(new Node(i, !now.color));
                }
            }
        }
        return true;
    }

    static void getIsland() {
        for (int i = 1; i <= v; i++) {
            if (!visited[i]) {
                bfsForIsland(i);
                island.add(i);
            }
        }
    }

    static void bfsForIsland(int s) {
        landQ.add(s);
        while (!landQ.isEmpty()) {
            int v = landQ.poll();
            if (visited[v]) {
                continue;
            }
            visited[v] = true;
            for (int i : graph[v]) {
                if (!visited[i]) {
                    landQ.add(i);
                }
            }
        }
    }

    static class Node {
        int v;
        boolean color;

        Node(int v, boolean color) {
            this.v = v;
            this.color = color;
        }
    }
}

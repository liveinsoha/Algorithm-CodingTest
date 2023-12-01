package page392;

import java.io.*;
import java.util.*;

public class BOJ11725 {

    static ArrayList<Integer>[] graph;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[] parent;
    static Queue<Integer> queue = new LinkedList<>();
    static boolean[] visited;
    static int count = 0;

    public static void main(String[] args) throws Exception {
        solution();
    }

    static void solution() throws Exception {

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        parent = new int[n + 1];
        initGraph();
        BSF(1);

        for (int i = 2; i <= n; i++) {
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void initGraph() throws Exception {
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
            count++;
        }
    }

    static void BSF(int start) {
        queue.add(start);
        while (!queue.isEmpty()) {
            int s = queue.poll();
            visited[s] = true;
            for (int i : graph[s]) {
                if(visited[i])
                    continue;
                queue.add(i);
                parent[i] = s;
            }
        }
    }
}

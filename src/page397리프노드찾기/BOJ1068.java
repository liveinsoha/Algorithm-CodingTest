package page397리프노드찾기;

import java.io.*;
import java.util.*;

public class BOJ1068 {
    /*말단노드를 뗴어 냈을 때 새롭게 말단노드가 될 수 있는 경우를 생각해야 한다.*/
    static ArrayList<Integer>[] graph;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static Queue<Integer> queue = new LinkedList<>();
    static int root;
    static boolean[] visited;
    static int deleted;
    static int leaf = 0;

    public static void main(String[] args) throws Exception {
        solution();
    }

    static void solution() throws Exception {
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];

        initGraph();
        deleted = Integer.parseInt(br.readLine());
        if(deleted == root)
            System.out.println(0);
        else {
            BFS(root);
            System.out.println(leaf);
        }
    }

    static void initGraph() throws Exception {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(st.nextToken());
            if (k != -1) {
                graph[k].add(i);
            } else {
                root = i;
            }
        }
    }

    static void BFS(int start) {
        queue.add(start);

        while (!queue.isEmpty()) {
            int s = queue.poll();
            int child = 0;

            for (int i : graph[s]) {
                if (i == deleted)
                    continue;
                child++;
                queue.add(i);
            }

            if (child == 0)
                leaf++;
        }

    }
}

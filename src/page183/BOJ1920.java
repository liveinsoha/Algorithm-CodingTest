package page183;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int n;
    static int m;

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
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        m = Integer.parseInt(br.readLine());
    }

    static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int s = Integer.parseInt(st.nextToken());
            sb.append(BinarySearch(0, arr.length - 1, s)).append("\n");
        }
    }

    public static int BinarySearch(int lo, int hi, int key) {
        while (lo <= hi) {

            int mid = (lo + hi) / 2;
            if (arr[mid] == key) {
                return 1;
            } else if (arr[mid] < key)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return 0;
    }
}

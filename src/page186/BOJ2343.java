package page186;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ2343 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[] arr;
    static int n;
    static int key;
    static int maxLen;
    static int sum;

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
        n = Integer.parseInt(st.nextToken());
        key = Integer.parseInt(st.nextToken());
        arr = new int[n];
        maxLen = 0;
        sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxLen = Math.max(maxLen, arr[i]);
            sum += arr[i];
        }
    }

    static void solve() {
        sb.append(binarySearch(maxLen, sum, key));

    }

    static int save(int len) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + arr[i] <= len) {
                sum += arr[i];
            } else {
                count++;
                sum = arr[i];
            }
        }
        count++;
        return count;
    }

    static int binarySearch(int lo, int hi, int key) {
        if (lo == hi) {
            return lo;
        }
        int mid = (lo + hi) / 2;
        if (save(mid) == key) {
            return binarySearch(lo, mid, key);
        } else if (save(mid) > key) {
            return binarySearch(mid + 1, hi, key);
        } else {
            return binarySearch(lo, mid - 1, key);
        }
    }
}

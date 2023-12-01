package page193;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1300 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static long n;
    static long key;

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
        key = Integer.parseInt(br.readLine());
    }

    static void solve() {
        sb.append(binarySearch(1, Math.min((int) Math.pow(10, 9), n * n), key));
    }

    static long countSmallerThanMe(long target) {
        long count = 0;
        for (int i = 1; i <= n; i++) {
            count += Math.min(n, target / i);
        }
        return count;
    }

    static long binarySearch(long lo, long hi, long key) {
        if (lo == hi) {
            return lo;
        }
        long mid = (lo + hi) / 2;
        long count = countSmallerThanMe(mid);

        if (count > key) {
            return binarySearch(lo, mid , key); // 카운팅이 클 때, 중복된 숫자가 존재하므로 경계를 함부로 탐색 범위에서 제외시키면 안된다.
        } else if (count < key) {
            return binarySearch(mid + 1, hi, key);
        } else {
            return binarySearch(lo, mid, key);
        }
    }
}

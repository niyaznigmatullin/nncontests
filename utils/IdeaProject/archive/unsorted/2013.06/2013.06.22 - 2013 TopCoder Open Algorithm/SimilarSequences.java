package coding;

import java.util.Arrays;
import java.util.HashSet;

public class SimilarSequences {

    final int MOD = 1000000009;

    public int count(int[] seq, int bound) {
        int n = seq.length;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            set.add(seq[i]);
        }
        if (set.size() != bound) {
            set.add(0);
        }
        HashSet<Element> el = new HashSet<Element>();
        for (int i = 0; i < n; i++) {
            int[] a = new int[n - 1];
            int cur = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                a[cur++] = seq[j];
            }
            for (int j = 0; j < n; j++) {
                for (int z : set) {
                    int[] b = new int[n];
                    cur = 0;
                    for (int k = 0; k < j; k++) {
                        b[cur++] = a[k];
                    }
                    b[cur++] = z;
                    for (int k = j; k < n - 1; k++) {
                        b[cur++] = a[k];
                    }
                    el.add(new Element(b));
                }
            }
        }
        int ans = 0;
        for (Element e : el) {
            int[] a = e.a;
            boolean found = false;
            for (int i : a) {
                if (i == 0) {
                    found = true;
                    break;
                }
            }
            if (found) {
                ans += bound - set.size() + 1;
            } else {
                ++ans;
            }
            ans %= MOD;
        }
        return ans;
    }

    static class Element {
        int[] a;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Element element = (Element) o;

            if (!Arrays.equals(a, element.a)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return a != null ? Arrays.hashCode(a) : 0;
        }

        Element(int[] a) {

            this.a = a;
        }
    }
}

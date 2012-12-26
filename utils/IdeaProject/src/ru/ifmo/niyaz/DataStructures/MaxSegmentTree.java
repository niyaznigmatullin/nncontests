package DataStructures;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 02.10.12
 * Time: 17:01
 * To change this template use File | Settings | File Templates.
 */
public class MaxSegmentTree {
    int[] max;
    int[] maxId;
    int n;

    public MaxSegmentTree(int n) {
        this.n = Integer.highestOneBit(n) << 1;
        max = new int[this.n * 2];
        maxId = new int[this.n * 2];
        Arrays.fill(max, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            maxId[i + n] = i;
        }
        for (int i = 0; i < n; i++) {
            set(i, Integer.MIN_VALUE);
        }
    }

    public void set(int x, int y) {
        x += n;
        max[x] = y;
        while (x > 1) {
            x >>= 1;
            int left = max[x << 1];
            int right = max[(x << 1) | 1];
            if (left >= right) {
                max[x] = left;
                maxId[x] = maxId[x << 1];
            } else {
                max[x] = right;
                maxId[x] = maxId[(x << 1) | 1];
            }
        }
    }

    public void add(int x, int y) {
        x += n;
        max[x] += y;
        while (x > 1) {
            x >>= 1;
            int left = max[x << 1];
            int right = max[(x << 1) | 1];
            if (left >= right) {
                max[x] = left;
                maxId[x] = maxId[x << 1];
            } else {
                max[x] = right;
                maxId[x] = maxId[(x << 1) | 1];
            }
        }
    }

    public int getMax(int left, int right) {
        --right;
        left += n;
        right += n;
        int ret = Integer.MIN_VALUE;
        while (left <= right) {
            if ((left & 1) == 1) {
                ret = Math.max(ret, max[left]);
                left++;
            }
            if ((right & 1) == 0) {
                ret = Math.max(ret, max[right]);
                right--;
            }
            left >>= 1;
            right >>= 1;
        }
        return ret;
    }

}

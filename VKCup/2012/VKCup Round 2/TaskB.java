package mypackage;

import arrayutils.ArrayUtils;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskB {

    static class Creature implements Comparable<Creature> {
        int mass;
        int velocity;
        int id;

        Creature(int mass, int velocity, int id) {
            this.mass = mass;
            this.velocity = velocity;
            this.id = id;
        }

        public int compareTo(Creature o) {
            if (mass != o.mass) {
                return o.mass - mass;
            }
            return o.velocity - velocity;
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int h = in.nextInt();
        Creature[] c = new Creature[n];
        int[] mass = new int[n];
        int[] velocity = new int[n];
        for (int i = 0; i < n; i++) {
            mass[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            velocity[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            c[i] = new Creature(mass[i], velocity[i], i);
        }
        Arrays.sort(c);
        double left = 0;
        double right = 2e9;
        for (int it = 0; it < 100; it++) {
            double mid = (left + right) * .5;
            int current = k;
            for (int i = 0; i < n && current > 0; i++) {
                if (current * h < mid * c[i].velocity) {
                    --current;
                }
            }
            if (current == 0) {
                right = mid;
            } else {
                left = mid;
            }
        }
        List<Integer> answer = new ArrayList<Integer>();
        int current = k;
        for (int i = 0; i < n && current > 0; i++) {
            if (current * h < right * c[i].velocity) {
                --current;
                answer.add(c[i].id + 1);
            }
        }
        Collections.reverse(answer);
        out.printArray(ArrayUtils.toPrimitiveArray(answer));
	}
}

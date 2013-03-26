import java.io.*;
import java.util.*;

public class Rear {
    public static void main(String[] args) throws Throwable {
        Scanner sc = new Scanner(System.in);
        List<Integer> answer = new ArrayList<>();
        while (sc.hasNextInt()) {
            int[] a = new int[10];
            int[] b = new int[10];
            for (int i = 0; i < 10; i++) {
                a[i] = sc.nextInt();
            }
            for (int i = 0; i < 10; i++) {
                b[i] = sc.nextInt();
            }
            Queue<State> q = new ArrayDeque<>(10000000);
            State start = new State(a);
            q.add(start);
            Map<State, Integer> d = new HashMap<>(10000000);
            d.put(start, 0);
            State finish = new State(b);
            State temp = new State(new int[10]);
            int[] z = temp.a;
            while (!d.containsKey(finish)) {
                State v = q.poll();
                for (int i = 0; i < 10; i++) {
                    for (int j = i + 2; j <= 10; j++) {
                        System.arraycopy(v.a, 0, z, 0, 10);
                        for (int x = i, y = j - 1; x < y; x++, y--) {
                            int t = z[x];
                            z[x] = z[y];
                            z[y] = t;
                        }
                        if (!d.containsKey(temp)) {
                            State newState = new State(z.clone());
                            d.put(newState, d.get(v) + 1);
                            q.add(newState);
                            if (0 == (d.size() & 262143)) System.err.println(d.size());
                        }
                    }   
                }
            }
            answer.add(d.get(finish));
        }
        for (int i = 0; i < answer.size(); i++) {
            if (i > 0) System.out.print(' ');
            System.out.print(answer.get(i));
        }
        System.out.println();
    }
    
    static class State {
        int[] a;
        
        State(int[] a) {
            this.a = a;
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(a);   
        }
        
        @Override
        public boolean equals(Object o) {
            State b = (State) o;
            return Arrays.equals(a, b.a);
        }
    }
}
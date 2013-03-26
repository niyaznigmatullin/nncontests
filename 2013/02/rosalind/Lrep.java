import java.io.*;
import java.util.*;

public class Lrep {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int k = in.nextInt();
        Map<String, Vertex> label = new HashMap<>();
        Vertex root = null;
        List<Vertex> all = new ArrayList<>();
        while (in.hasNext()) {
            String vs = in.next();
            String us = in.next();
            Vertex v = ml(label, all, vs);
            Vertex u = ml(label, all, us);
            if (root == null) root = v;
            v.edges.add(new Edge(v, u, in.nextInt() - 1, in.nextInt()));
        }
        root.dfs(0);
        Vertex answer = null;
        for (Vertex e : all) {
            if (e.count < k) continue;
            if (answer == null || answer.depth < e.depth) answer = e;
        }
        if (answer == null) throw new AssertionError();
        StringBuilder ans = new StringBuilder();
        for (Vertex v = answer; v != root; v = v.parent.from) {
            Edge last = v.parent;
            for (int i = last.start + last.len - 1; i >= last.start; i--) {
                ans.append(s.charAt(i));
            }
        }
        System.out.println(ans.reverse());
    }
    
    static Vertex ml(Map<String, Vertex> label, List<Vertex> all, String vs) {
        Vertex d = label.get(vs);
        if (d != null) return d;
        d = new Vertex();
        all.add(d);
        label.put(vs, d);
        return d;
    }
}

class Vertex {
    Edge parent;
    List<Edge> edges;
    int depth;
    int count;
    
    Vertex() {
        edges = new ArrayList<>();
    }
    
    int dfs(int depth) {
        this.depth = depth;
        count = 0;
        for (Edge e : edges) {
            e.to.parent = e;
            count += e.to.dfs(depth + e.len);
        }
        if (count == 0) count = 1;
        return count;
    }
}

class Edge {
    Vertex from;
    Vertex to;
    int start;
    int len;
    
    Edge(Vertex from, Vertex to, int start, int len) {
        this.from = from;
        this.to = to;
        this.start = start;
        this.len = len;
    }
}
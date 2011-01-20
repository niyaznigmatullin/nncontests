import java.io.*;
import java.util.*;

public class Tree implements Runnable {

  public static void main(String[] args) {
    new Thread(new Tree()).start();
  }

  BufferedReader br;
  StringTokenizer st;
  PrintWriter out;
  boolean eof = false;

  public void run() {
    Locale.setDefault(Locale.US);
    try {
      br = new BufferedReader(new FileReader("tree.in"));
      out = new PrintWriter("tree.out");
      solve();
      out.close();
    } catch (Throwable e) {
      e.printStackTrace();
      System.exit(239);
    }
  }

  String nextToken() {
    while (st == null || !st.hasMoreTokens()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (Exception e) {
        eof = true;
        return "0";
      }
    }
    return st.nextToken();
  }

  static class Edge {
    int start, len;
    Node from, to;

    public Edge(int start, int len, Node from, Node to) {
      this.start = start;
      this.len = len;
      this.from = from;
      this.to = to;
    }

    // @Override
    // public String toString() {
    // return "Edge " + s.substring(start, start + len) + " to "
    // + to.number;
    // }
  }

  static class Node {
    Node p, link;
    int number;
    int depth;
    Edge[] e;

    Node(int number) {
      this.number = number;
      e = new Edge[ALPHABET];
    }

    Node(int number, int depth, Node p) {
      this.number = number;
      this.depth = depth;
      this.p = p;
      e = new Edge[ALPHABET];
    }

    public String toString() {
      String s = "#" + number + " edges: ";
      for (int i = 0; i < e.length; i++) {
        if (e[i] != null)
          s += (char) ('a' + i - 1);
      }
      s += ", depth: " + depth;
      return s;
    }
  }

  void addEdgeToLeaf(Node from, int start) {
    Node leaf = new Node(nodes.size(), from.depth + c.length - start, from);
    nodes.add(leaf);
    Edge edge = new Edge(start, c.length - start, from, leaf);
    leaf.p = from;
    from.e[toInt(c[start])] = edge;
  }

  Node splitEdge(Edge edge, int k) {
    Node n1 = new Node(nodes.size(), edge.from.depth + k, edge.from);
    nodes.add(n1);
    Edge e1 = new Edge(edge.start + k, edge.len - k, n1, edge.to);
    n1.e[toInt(c[e1.start])] = e1;
    edge.to.p = n1;
    edge.to = n1;
    edge.len = k;
    return n1;
  }

  void giveLink(Node to) {
    if (needsLink) {
      lastnode.link = to;
      needsLink = false;
    }
  }

  boolean insert(Node n, int j, int i) { // insert after n
    if (j > i)
      return false;
    Edge edge = n.e[toInt(c[j + n.depth])];
    while (edge != null && n.depth + edge.len <= i - j) {
      if (edge == null)
        break;
      n = edge.to;
      edge = n.e[toInt(c[j + n.depth])];
    }
    if (edge == null) {
      addEdgeToLeaf(n, n.depth + j);
      giveLink(n);
      lastnode = n;
      return true;
    }
    if (c[edge.start + i - j - n.depth] == c[i]) {
      giveLink(n);
      lastnode = n;
      return false; // OK, do nothing
    }
    int k = i - n.depth - j;
    Node n1 = splitEdge(edge, k);
    addEdgeToLeaf(n1, i);

    giveLink(n1);
    lastnode = n1;
    needsLink = true;
    return true;
  }

  boolean needsLink;
  Node lastnode;
  Node start;

  boolean insert(int j, int i, boolean wehavejustincreasedi) {
    if (wehavejustincreasedi) {
      return insert(lastnode, j, i);
    } else {
      if (lastnode == null || lastnode == start)
        return insert(start, j, i);
      else {
        if (needsLink)
          return insert(lastnode.p.link, j, i);
        else
          return insert(lastnode.link, j, i);
      }
    }
  }

  int toInt(char c) {
    return c - 'a' + 1;
  }

  final static int ALPHABET = 27;
  int maxN;
  ArrayList<Node> nodes;
  char[] c;
  String s;

  void work(String s) {
    c = s.toCharArray();
    // System.err.println(s);
    maxN = s.length() * 2 + 10;
    nodes = new ArrayList<Node>();
    start = new Node(0);
    start.link = start;
    lastnode = start;
    nodes.add(start);
    int n = c.length;
    int j = 0, i = 0;
    boolean flag = false;
    while (j < n && i < n) {
      if (insert(j, i, flag)) {
        // System.err.println(j + " " + i + " - added a new node");
        j++;
        flag = false;
      } else {
        // System.err.println(j + " " + i + " - did nothing");
        i++;
        flag = true;
      }
    }
  }

  private void solve() throws IOException {
    s = nextToken();
    work(s);
    out.println(nodes.size() + " " + (nodes.size() - 1));
    for (Node n : nodes) {
      for (Edge t : n.e) {
        if (t != null) {
          out.println((n.number + 1) + " " + (t.to.number + 1) + " "
              + (t.start + 1) + " " + (t.start + t.len));
        }
      }
    }
  }
}
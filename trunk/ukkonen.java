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

  class Edge {
    int start, len;
    Node to;

    public Edge(int start, int len, Node to) {
      this.start = start;
      this.len = len;
      this.to = to;
    }

    @Override
    public String toString() {
      return "Edge " + s.substring(start, start + len) + " to "
          + to.number;
    }
  }

  class Node {
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

  boolean insert(Node n, int j, int i) { // insert after n
    if (j > i)
      return false;
    Edge e0 = n.e[toInt(c[j + n.depth])];
    while (e0 != null && n.depth + e0.len <= i - j) {
      if (e0 == null)
        break;
      n = e0.to;
      e0 = n.e[toInt(c[j + n.depth])];
    }
    if (e0 == null) {
      Node leaf = new Node(nodes.size(), c.length - j, n);
      nodes.add(leaf);
      e0 = new Edge(n.depth + j, c.length - n.depth - j, leaf);
      n.e[toInt(c[j + n.depth])] = e0;
      if (needsLink) {
        lastnode.link = n;
        needsLink = false;
      }
      lastnode = n;
      return true;
    }
    if (c[e0.start + i - j - n.depth] == c[i]) {
      if (needsLink) {
        lastnode.link = n;
        needsLink = false;
      }
      lastnode = n;
      return false; // OK, do nothing
    }
    int k = i - n.depth - j;
    if (k == 0) {
      throw new AssertionError();
    }
    Node n1 = new Node(nodes.size(), n.depth + k, n);
    nodes.add(n1);

    Edge e1 = new Edge(e0.start + k, e0.len - k, e0.to);
    n1.e[toInt(c[e1.start])] = e1;
    e0.to.p = n1;

    e0.to = n1;
    e0.len = k;

    Node leaf = new Node(nodes.size(), c.length - j, n1);
    nodes.add(leaf);

    Edge e2 = new Edge(i, c.length - i, leaf);
    n1.e[toInt(c[e2.start])] = e2;

    if (needsLink) {
      lastnode.link = n1;
    }
    lastnode = n1;
    needsLink = true;
    return true;
  }

  boolean needsLink;
  Node lastnode;
  Node start;

  boolean insert(int j, int i, boolean wehavejustincreasedi) {
    if (wehavejustincreasedi) {
      if (needsLink)
        throw new AssertionError();
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
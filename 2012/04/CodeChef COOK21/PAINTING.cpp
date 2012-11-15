#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Reader {
    char * buffer;
    static const size_t DEFAULT_BUF_SIZE = 1 << 13;
    istream & is;
    size_t curPos;
    size_t curSize;
    size_t bufSize;
    bool eof;
public:
    Reader(istream & is, size_t bufSize) : is(is), buffer(new char[bufSize]), bufSize(bufSize), curSize(0), curPos(0)
    {
        
    }
    
    int read()
    {
        if (eof)
        {
            return -1;
        }
        if (curPos >= curSize)
        {
            curPos = 0;
            is.read(buffer, bufSize);
            curSize = is.gcount();
            if (curSize == 0)
            {
                eof = true;
                return -1;
            }
        }
        return buffer[curPos++];
    }
    
    int nextInt()
    {
        int c = read();
        while (isWhiteSpace(c))
        {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int ret = 0;
        while (!isWhiteSpace(c)) {
            if (c < '0' || c > '9') {
                //                cerr << "digit expected " + ((char) c) + " found";
                cerr << "bad input";
                throw 1;
            }
            ret = ret * 10 + c - '0';
            c = read();
        }
        return ret * sgn;
    }
    
    string next()
    {
        int c = read();
        while (isWhiteSpace(c))
        {
            c = read();
        }
        string ret = "";
        while (!isWhiteSpace(c))
        {
            ret += (char) c;
            c = read();
        }
        return ret;
    }
    
    long long nextLong()
    {
        int c = read();
        while (isWhiteSpace(c))
        {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long long ret = 0;
        while (!isWhiteSpace(c)) {
            if (c < '0' || c > '9') {
                //                cerr << "digit expected " + ((char) c) + " found";
                cerr << "bad input";
                throw 1;
            }
            ret = ret * 10 + c - '0';
            c = read();
        }
        if (sgn < 0)
        {
            ret = -ret;
        }
        return ret;
    }
    
    static bool isWhiteSpace(int c) {
        return c >= -1 && c <= 32;
    }
    
};

Reader in(cin, 1 << 15);
typedef long long ll;
const int INF = ~(1 << 31);

void merge(int a, int b, int c, int d, int & e, int & f) {
    int a1 = max(a, c);
    int a2 = a == a1 ? max(b, c) : max(a, d);
    e = a1;
    f = a2;
}

struct Node;
int getMax1(Node * v);

int getMax2(Node * v);


struct Node {
    Node * left;
    Node * right;
    int max1;
    int max2;
    
    Node(Node * left, Node * right, int max1, int max2) : left(left), right(right), max1(max1), max2(max2) {
        
    }
    
    Node (Node * left, Node * right) : left(left), right(right) {
        merge(getMax1(left), getMax2(left), getMax1(right), getMax2(right), max1, max2);
    }
};

int getMax1(Node * v) {
    return v == 0 ? -INF : v->max1;
}

int getMax2(Node * v) {
    return v == 0 ? -INF : v->max2;
}

Node * build(int left, int right, vector<int> const & r) {
    if (left >= right) {
        return 0;
    }
    if (left == right - 1) {
        return new Node(0, 0, r[left], -INF);
    }
    int mid = (left + right) / 2;
    return new Node(build(left, mid, r), build(mid, right, r));
}

Node * set(Node * v, int left, int right, int x, int y) {
    if (left == right - 1) {
        return new Node(0, 0, y, -INF);
    }
    int mid = (left + right) / 2;
    if (x < mid) {
        return new Node(set(v->left, left, mid, x, y), v->right);
    } else {
        return new Node(v->left, set(v->right, mid, right, x, y));
    }
}

void getMin(Node * v, int left, int right, int l, int r, int & ans1, int & ans2) {
    if (l <= left && right <= r) {
        ans1 = getMax1(v);
        ans2 = getMax2(v);
        return;
    }
    if (right <= l || r <= left) {
        ans1 = -INF;
        ans2 = -INF;
        return;
    }
    int mid = (left + right) / 2;
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;
    getMin(v->left, left, mid, l, r, a, b);
    getMin(v->right, mid, right, l, r, c, d);
    merge(a, b, c, d, ans1, ans2);
}

void solve() {
    int n = in.nextInt();
    int m = in.nextInt();
    int a = in.nextInt();
    int b = in.nextInt();
    int c = in.nextInt();
    int d = in.nextInt();
    vector<int> ratings(n);
    for (int i = 0; i < n; i++) {
        ratings[i] = in.nextInt();
    }
    int q = in.nextInt();
    Node * root = build(0, n, ratings);
    int r1 = 0;
    int r2 = 0;
    vector<Node*> ver(q + 1);
    ver[0] = root;
    for (int i = 1; i <= q; i++) {
        int t = (int) (((ll) a * r1 + d) % i);
        int qi = in.nextInt();
        int qj = in.nextInt();
        getMin(ver[t], 0, n, qi, qj + 1, r1, r2);
        cout << r1 << " " << r2 << "\n";
        ver[i] = set(ver[i - 1], 0, n, (int) (( (ll) b * r1 + d) % n), (int) (( (ll) c * r2 + d) % m));
    }
}

int main()
{
    int t = 1;
    for (int i = 0; i < t; i++)
    {
        solve();
    }
}

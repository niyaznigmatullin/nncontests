
#include <iostream>

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
typedef long long int64;

int64 gcd(int64 a, int64 b)
{
    return b == 0 ? a : gcd(b, a % b);
}

int64 lcm(int64 a, int64 b)
{
    a /= gcd(a, b);
    if ((~(1LL << 63)) / a <= b)
    {
        return -1;
    }
    return a * b;
}

void solve()
{
    int64 a = in.nextLong();
    int64 b = in.nextLong();
    int64 c = in.nextLong();
    int64 d = in.nextLong();
    int64 k = in.nextLong();
    int64 v = lcm(gcd(a, b), gcd(c, d));
    if (v < 0)
    {
        cout << 1 << "\n";
    }
    else
    {
        cout << 2 * (k / v) + 1 << "\n";
    }
}

int main()
{
    int t = in.nextInt();
    for (int i = 0; i < t; i++)
    {
        solve();
    }
}
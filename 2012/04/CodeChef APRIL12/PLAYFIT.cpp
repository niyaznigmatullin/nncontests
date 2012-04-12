//
//  PLAYFIT.cpp
//  CodeChef
//
//  Created by Нияз Нигматуллин on 02.04.12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

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
    
    
    static bool isWhiteSpace(int c) {
        return c >= -1 && c <= 32;
    }
    
};

Reader inputReader(cin, 1 << 15);


void solve()
{
    int n = inputReader.nextInt();
    int minimal = ~(1 << 31);
    int ans = 0;
    for (int i = 0; i < n; i++)
    {
        int x = inputReader.nextInt();
        if (minimal < x)
        {
            ans = max(ans, x - minimal);
        }
        minimal = min(minimal, x);
    }
    if (0 == ans)
    {
        cout << "UNFIT\n";
    }
    else
    {
        cout << ans << "\n";
    }
}

int main()
{
    int t = inputReader.nextInt();
    for (int i = 0; i < t; i++)
    {
        solve();
    }
}
#include <cstdio>
#include <string>


using std::string;

string tree, letters;

int freq[777], left[777], right[777], nodes[777], let[777];
double prob[777];
char s[222222];
string repr[777];

char tohex(int x) {
//    int ret = x < 10 ? (x + '0') : x < 36 ? (x - 10 + 'a') : x < 62 ? (x - 36 + 'A') : x == 62 ? '+' : x == 63 ? '-' : x - 64 + 128;
    int ret = x + 100;
    return ret;
}

void dfs(int v, string const & s) {
    if (let[v] >= 0) {
        tree += "0";
        repr[let[v]] = s;
        letters += tohex(let[v] / 16);
        letters += tohex(let[v] % 16);
    } else {
        tree += "1";
        dfs(left[v], s + "0");
        dfs(right[v], s + "1");
    }
}

string encode(string const & all, int all_len) {
    string code = "";
    for (int i = 0; i < all_len; i += 7) {
        int v = 0;
        for (int j = 0; j < 7; j++) {
            v = v * 2 + all[i + j] - '0';
        }
        code += tohex(v);
    }
    return code;
}

int main() {
    int c = getchar();
    int n = 0;
    while (c >= 0) {
//        if (c != 13)
            s[n++] = c;
        c = getchar();
    }
    int fr = 0;
    int cn = 0;
    for (int i = 0; i < 256; i++) freq[i] = 0;
    for (int i = 0; i < n; i++) freq[(int) s[i]]++;
    ++freq[0];
    for (int i = 0; i < 256; i++) {
        if (freq[i] > 0) {
            let[fr] = i;
            prob[fr] = 1. * freq[i] / n;
            left[fr] = right[fr] = -1;
            nodes[cn++] = fr++;
        }
    }
    while (cn > 1) {
        for (int i = 0; i < cn; i++) {
            for (int j = i + 1; j < cn; j++) {
                if (prob[nodes[i]] < prob[nodes[j]]) {
                    int t = nodes[i];
                    nodes[i] = nodes[j];
                    nodes[j] = t;   
                }
            }
        }
        left[fr] = nodes[cn - 2];
        right[fr] = nodes[cn - 1];
        prob[fr] = prob[left[fr]] + prob[right[fr]];
        let[fr] = -1;
        --cn;
        nodes[cn - 1] = fr;
        ++fr;
    }
    tree = "";
    dfs(nodes[0], "");
    string all = "";
    for (int i = 0; i < n; i++) {
        all += repr[(int) s[i]];
    }
    puts("//CPP");
    puts("#include <cstdio>");
    puts("#include <string>");
    puts("#include <map>");
    puts("using std::string;");
    puts("using std::map;");
    printf("const int N = %d;\n", (int) all.length());
    int all_len = all.length();
    for (int i = 0; i < 10; i++) all += "0";
    int tree_len = tree.length();
    for (int i = 0; i < 10; i++) tree += "0";
    string treecode = encode(tree, tree_len);
    string code = encode(all, all_len);
    printf("const string tc = \"%s\";\n", treecode.c_str());
    printf("const int tl = %d;\n", tree_len);
    printf("const string le = \"%s\";\n", letters.c_str());
    puts("const string co =");
    const int PART = 100;
    for (int i = 0; i < (int) code.length(); i += PART) {
        int len = i + PART;
        if (len > (int) code.length()) len = code.length();
        len -= i;
        printf("\"%s\"\n", code.substr(i, len).c_str());
    }
    puts(";");
//    printf("const string co = \"%s\";\n", code.c_str());
    puts("string tree, s;");
    puts("map<string, int> r;");
    puts("int cur, cl;");
//    puts("int dd(char c) { return c >= '0' && c <= '9' ? c - '0' : c >= 'a' && c <= 'z' ? c - 'a' + 10 : c >= 'A' && c <= 'Z' ? c - 'A' + 36 : c == '+' ? 62 : c == '-' ? 63 : c - 128 + 64; }");
    puts("int dd(char c) { return c - 100; }");
    puts("string d(string const & s, int len) {");
    puts("string ret = \"\";");
    puts("for (int i = 0; i < (int) s.length(); i++) {");
    puts("int c = dd(s[i]);");
    puts("for (int j = 0; j < 7; j++) {");
    puts("int bit = 1 & (c >> (6 - j));");
    puts("if ((int) ret.length() < len) ret += (char) (bit + '0');");
    puts("}");
    puts("}");
    puts("return ret;");
    puts("}");
    puts("void gr(string const & s) {");
    puts("int c = tree[cur++] - '0';");
    puts("if (c == 0) {");
    puts("int let = dd(le[cl++]);");
    puts("let = let * 16 + dd(le[cl++]);");
    puts("r[s] = let;");
    puts("} else {");
    puts("gr(s + \"0\");");
    puts("gr(s + \"1\");");
    puts("}");
    puts("}");
    puts("int main() {");
    puts("cur = cl = 0;");
    puts("tree = d(tc, tl);");
    puts("gr(\"\");");
    puts("s = d(co, N);");
    puts("string c = \"\";");
    puts("for (int i = 0; i < N; i++) {");
    puts("c += s[i];");
    puts("if (r.count(c)) { putchar(r[c]); c = \"\"; }");
    puts("}");
    puts("}");
}

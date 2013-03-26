#include <cstdio>

char s[4444];
int has[30];

int main() {
    gets(s);
    int t = 0;
    for (int i = 0; s[i]; i++) {
        if (s[i] >= '0' && s[i] <= '9') t = t * 10 + s[i] - '0';
    }
    for (int i = 0; i <t ;i++) {
        for (int j = 0; j < 26; j++) {
            has[j] = 0;
        }
        gets(s);
        for (int j = 0; s[j]; j++) {
            int c = s[j];
            if (c >= 'A' && c <= 'Z') c = c - 'A' + 'a';
            if (c >= 'a' && c <= 'z') {
                has[c - 'a'] = 1;
            }
        }
        int f = 0;
        for (int j = 0; j < 26; j++) {
            if (!has[j]) {
                f = 1;
                putchar(j + 'a');
                puts("");
                break;
            }
        }
        if (!f) puts("~");
    }    
}
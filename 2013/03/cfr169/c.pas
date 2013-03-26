
const MAXN = 555555;
var l, r, n, q, i : longint;
    a, b, c, d, e, f : array[0 .. 555555] of longint;
    ans : int64;
    
begin
    read(n, q);
    for i := 1 to n do begin
        read(b[i]);
    end;
    fillchar(c, sizeof(c), 0);
    fillchar(a, sizeof(a), 0);
    fillchar(d, sizeof(d), 0);
    for i := 1 to q do begin
        read(l, r);
        inc(a[l]);
        dec(a[r + 1]);
    end;
    for i := 1 to n do begin
        a[i] := a[i] + a[i - 1];
    end;
    for i := 1 to n do begin
        inc(c[a[i]]);
    end;
    for i := 1 to n do begin
        inc(d[b[i]]);
    end;
    for i := 1 to MAXN do begin
        c[i] := c[i] + c[i - 1];
    end;
    for i := 1 to MAXN do begin
        d[i] := d[i] + d[i - 1];
    end;
    for i := 1 to n do begin
        e[c[a[i]]] := a[i];
        dec(c[a[i]]);
    end;
    for i := 1 to n do begin
        f[d[b[i]]] := b[i];
        dec(d[b[i]]);
    end;
    ans := 0;
    for i := 1 to n do begin
        ans := ans + int64(e[i]) * f[i];
    end;
    writeln(ans);
end.
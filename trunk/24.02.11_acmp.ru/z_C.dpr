
var
    n, m: integer;
    a: array[0 .. 100, 0 .. 100] of integer;
    i, j: integer;
    ans: extended;
    count: integer;

begin
    reset(input, 'input.txt');
    rewrite(output, 'output.txt');
    read(n, m);
    for i := 1 to n do begin
        for j := 1 to m do begin
            read(a[i][j]);
        end;
    end;
    ans := 0;
    for i := 1 to n do begin
        for j := 1 to m do begin
                if (i < n) and (a[i][j] <> a[i + 1][j]) then begin
                    ans := ans + 0.8 * 0.2;
                end;
                if (j < m) and (a[i][j] <> a[i][j + 1]) then
                    ans := ans + 0.8 * 0.2;
        end;
    end;
    for i := 1 to n - 1 do
        for j := 1 to m - 1 do begin
            count := 0;
            if (a[i][j] <> a[i + 1][j]) then
                inc(count);
            if (a[i + 1][j] <> a[i + 1][j + 1]) then
                inc(count);
            if (a[i + 1][j + 1] <> a[i][j + 1]) then
                inc(count);
            if (a[i][j] <> a[i][j + 1]) then
                inc(count);
            if (count > 0) then
                ans := ans + 0.04;
        end;
(*    for i := 1 to n - 1 do
        if (a[i][1] <> a[i + 1][1]) then
            ans := ans - 0.04;
    for i := 1 to n - 1 do
        if (a[i][m] <> a[i + 1][m]) then
            ans := ans - 0.04;
    for i := 1 to m - 1 do
        if (a[1][i] <> a[1][i + 1]) then
            ans := ans - 0.04;
    for i := 1 to m - 1 do
        if (a[n][i] <> a[n][i + 1]) then
            ans := ans - 0.04; *)
    writeln(3 * ans:0:10);
end.
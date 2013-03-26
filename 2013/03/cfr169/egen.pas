var j, t, v, n, q, i : longint;
    a : array[0 .. 123123] of longint;
    p : array[0 .. 123123] of longint;

begin
    n := random(1000) + 1;
    q := random(10010) + 1;
    writeln(n, ' ', q);
    fillchar(a, sizeof(a), 0);
    for i := 1 to n do p[i] := i;
    for i := 3 to n do begin
        j := random(i - 2) + 2;
        t := p[i];
        p[i] := p[j];
        p[j] := t;
    end;
    for i := 2 to n do begin
        v := random(i - 1) + 1;
        if (v = 1)  then
            writeln(p[i], ' ', p[v])
        else if a[v] = 0 then begin
            writeln(p[i], ' ', p[v]);
            inc(a[v]);
        end else begin
            writeln(p[i], ' ', 1);
        end;
    end;
    for i := 1 to q do begin
        if (random(2) = 0) then begin
            writeln('0 ', random(n) + 1, ' ', random(10), ' ', random(n div 2));
        end else begin
            writeln('1 ', random(n) + 1);
        end;
    end;
end.
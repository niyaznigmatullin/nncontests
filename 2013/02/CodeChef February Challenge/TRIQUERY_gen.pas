var i, n, q, mx : longint;

begin
    n := 5;
    q := 40;
    writeln(n, ' ', q);
    mx := 20;
    for i := 1 to n do begin
        writeln(random(mx) + 1, ' ', random(mx) + 1);
    end;
    for i := 1 to q do begin
        writeln(random(mx) + 1, ' ', random(mx) + 1, ' ', random(mx) + 1);
    end;
end.
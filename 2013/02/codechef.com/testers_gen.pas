var t, j, n, i : longint;
    p : array[0 .. 1234567] of longint;
    
begin
    randomize;
    n := 300000;
    writeln(n);
    for i := 1 to n do begin
        p[i] := i;
        j := random(i) + 1;
        t := p[j];
        p[j] := p[i];
        p[i] := t;
    end;
    for i := 2 to n do
//        writeln(random(i - 1) + 1, ' ', i);
        writeln(p[random(i - 1) + 1], ' ', p[i]);
//        writeln(i div 2, ' ', i);
//        if i mod 2 = 1 then writeln(i - 2,' ', i) else writeln(i - 1, ' ', i);
//        writeln(p[i - 1], ' ', p[i]);
    for i := 1 to n do begin
        write(1 + random(7), ' ');
    end;
    
end.
var n, m, i, j : longint;
begin
    randomize;
    n := 100000;
    writeln(n);
    for i := 2 to n do begin
        writeln(i div 2 - 1, ' ', i - 1);
//        writeln(i - 1, ' ', i - 2);
//        writeln(i - 1, ' ', random(i - 1));
    end;
    m := 25000;
    writeln(m);
    for i := 1 to m do begin
        if (random(2) = 0) then begin
            write('U ', random(n), ' ', random(n), ' ');
            for j := 1 to 15 do begin
                write(chr(ord('a') + random(3)));
            end;
            writeln;
        end else begin
            writeln('Q ', random(n), ' ', random(n));
        end;
    end;
end.
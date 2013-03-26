
var n, m, i, t : longint;

begin
    randomize;
    n := 100000;
    m := 100000;
    writeln(n, ' ', m);
    for i := 2 to n do begin
//        writeln(i div 2, ' ', i);
        writeln(i - 1, ' ', i);
//        writeln(i, ' ', random(i - 1) + 1);
    end;
    for i := 1 to m do begin
        t := random(5);
        if (t = 0) then begin
            writeln('c ', random(n), ' ', random(n), ' ', 1000, ' ', 1000);
        end else if (t <> 0) then begin
            writeln('q ', random(n), ' ', random(n));
        end else begin
            writeln('l ', random(123));
        end;
    end;
end.
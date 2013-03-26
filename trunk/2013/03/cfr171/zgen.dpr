
var i, x, y, t : longint;

begin
    writeln(100000, ' ', 100000);
    for i := 1 to 100000 do begin
        y := i mod 2000;
        if y > 1000 then y := 2000 - y;
        write(1, ' ');
    end;
    writeln;
    for i := 1 to 100000 do begin
        x := random(100000) + 1;
        y := random(100000) + 1;
        if x > y then begin
            t := x;
            x := y;
            y := t;
        end;
        writeln(x, ' ', y);
    end;
end.
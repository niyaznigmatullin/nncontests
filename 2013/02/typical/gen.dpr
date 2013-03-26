
var i, t : longint;
begin
    t := 1;
    for i := 1 to 10000 do begin
        writeln(i, ' ', t);
        t := t + i mod 100;
    end;
end.
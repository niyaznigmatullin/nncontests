
var i : longint;

begin
    writeln(1000);
    for i := 1 to 1000 do begin
        writeln(-500000 + i * 1000000 div 1000);
    end;
end.

var i : longint;
begin
randomize;
    for i := 2 to 36 do begin
        write(i div 2, ',');
    end;
    writeln;
    for i := 2 to 36 do begin
        write(i, ',');
    end;
end.
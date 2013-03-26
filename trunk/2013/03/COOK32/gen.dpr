
procedure f;
    var i : longint;
    begin
        for i := 1 to 100000 do write(chr(random(1) + ord('a')));
        writeln;
    end;
    
begin
    randomize;
    f;
    f;
end.
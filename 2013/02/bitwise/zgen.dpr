var
i, t : longint;
begin
    t := 100;
    writeln(t);
    for i := 1 to t do begin
        writeln(random(300) + 1, ' ', random(300) + 1);
    end;
end.
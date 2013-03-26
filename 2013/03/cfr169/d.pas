
var a, b, c : int64;
    i : longint;
    
begin
    read(a, b);
    c := a xor b;
    for i := 1 to 100 do c := c or (c shr 1);
    writeln(c);
end.
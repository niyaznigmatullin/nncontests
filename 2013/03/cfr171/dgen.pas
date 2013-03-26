
var n, i : longint;
    f : array[0 .. 1232] of longint;
    
begin
    n := 23;
    f[1] := 1;
    f[2] := 2;
    for i := 3 to n do f[i] := f[i - 1] + f[i - 2];
    writeln(n);
    for i := 1 to n do write(f[i], ' ');
    writeln;
end.
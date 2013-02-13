
var n, i : longint;

begin
    randomize;
    n := 30000000;
    writeln(n);
    for i := 1 to n do writeln(random(1 shl 30));
end.
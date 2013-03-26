
var n, i : longint;

begin
    randomize;
    n := random(40) + 1;
    writeln(n);
    for i := 1 to 2 * n - 1 do write('QC'[random(2) + 1]);
    writeln;
end.
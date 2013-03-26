
var i : longint;

begin
    randomize;
    writeln(300000);
    for i := 1 to 300000 do writeln(random(1 shl 30) - 1 shl 29);
end.
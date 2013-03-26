
var j, n, i : longint;

begin
    randomize;
    n := 200000;//random(200000 - 20000) + 20000;
    for i := 1 to 16 do for j := 1 to 1 shl i do write(chr(ord('a') + i));
end.
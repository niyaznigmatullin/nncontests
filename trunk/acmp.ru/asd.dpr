var i:integer;
begin
rewrite(output,'input.txt');
write(random(9)+1);
for i:=3 to 2500 do write(random(10));
writeln;
for i:=3 to 2500 do write(random(10));
end.
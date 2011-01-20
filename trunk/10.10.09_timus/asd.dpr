
var s:String;
	i, j: integer;
begin
rewrite(output,'asd');
writeln(1000);
for i := 1 to 1000 do
	begin
		s := '';
		for j := 1 to 100 do s := s + chr(random(26) + ord('a'));
		writeln(s);
	end;
end.
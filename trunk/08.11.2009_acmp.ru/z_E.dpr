program Project2;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var q,n,i :integer;
    m:extended;
    s,s1:String;
begin
reset(input,'input.txt');
rewrite(output,'output.txt');
  read(n);
  s := inttoStr(n);
  close(input);
  for i := 0 to 30 do
    begin
      s1 := inttostr(int64(1) shl i);
      if (pos(s, s1) = 1) then
        begin
          writeln(i);
          close(output);
          halt(0);
        end;
    end;
  m := 1;
  q := 0;
  while (true) do
    begin
      while (m - 1 > n) do m := m / 10;
      if (m - n < 1) and (m - n > 0) then
        begin
          writeln(q);
          close(output);
          halt(0);
        end;
      m := m * 2;
      inc(q);
    end;
end.

program _0435;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var i,j,x,y:integer;

begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  for i:=0 to 100 do
  for j:=0 to 9 do if (i*j<=300) then
    begin
      if (not eof) then
        readln else begin write(i,' ',j);halt(0); end;
    end;
end.
 
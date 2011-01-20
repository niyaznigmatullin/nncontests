program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var c:array[-10..1000,-10..1000] of char;
    i,j,n,m,ans:integer;

begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  readln(n,m);
  for i:=1 to n do
    begin
      for j:=1 to m do
        begin
          read(c[i][j]);
        end;
      readln;
    end;
  close(input);
  ans:=0;
  for i:=1 to n do
  for j:=1 to m do if (c[i][j]<>'*') and (c[i-1][j]<>'*') and (c[i][j-1]<>'*') and (c[i+1][j]<>'*') and (c[i][j+1]<>'*') then
    inc(ans);
  write(ans);
  close(output);
  halt(0);
end.
 
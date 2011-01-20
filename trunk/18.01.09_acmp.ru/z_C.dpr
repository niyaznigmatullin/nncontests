program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:string;
    i:integer;
begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  readln(s);
  close(input);
  s:=trim(s);
  for i:=1 to length(s) div 2 do if (s[i]<>s[length(s)-i+1]) then
    begin
      write('NO');
      close(output);
      halt(0);
    end;
  write('YES');
  close(output);
  halt(0);
end.
 
program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

VAR N:INTEGER;
    q:integer;
procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    i:=1;
    q:=0;
    while i<=n do
      begin
        if n mod i=0 then
          inc(q,i);
        inc(i);
      end;
  end;

procedure out;
  begin
    write(q);
    close(output);
  end;

begin
  init;
  solve;
  out;
end. 

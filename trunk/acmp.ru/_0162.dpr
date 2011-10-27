program _0162;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,m:integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m);
    close(input);
  end;

procedure done;
  begin
    if n=1 then write(m*4) else
    if m=1 then write(n*4) else
    if (not odd(n)) or (not odd(m)) then write((n+2)*m+(m+2)*n) else
    write((n+2)*m+(m+2)*n-2);
    close(output);
    halt(0);
  end;

begin
  init;
  done;
end.
 
program z_1409;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,m:integer;

procedure init;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    read(n,m);
  end;

procedure done;
  begin
    write(m-1,' ',n-1);
  end;

begin
  init;
  done;
end.

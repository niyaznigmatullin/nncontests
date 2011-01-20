program z_F;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;
procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure out;
  begin
    write(n div 6,' ',(n div 3)*2,' ',n div 6);
    close(output);
  end;

begin
  init;
  out;
end.

program z_G;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:string;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
    close(input);
  end;

procedure solve;
  begin

  end;

procedure done;
  begin
    if (odd(ord(s[1])-ord('A')+ord(s[2])-ord('0'))) then write('BLACK') else write('WHITE');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
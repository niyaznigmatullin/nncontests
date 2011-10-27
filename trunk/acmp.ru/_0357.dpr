program _0357;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:string;
    a,b:integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    for i:=1 to length(s) do if odd(i) then inc(a,ord(s[i])-ord('0')) else inc(b,ord(s[i])-ord('0'));
  end;

procedure done;
  begin
    if abs(a-b) mod 11=0 then write('YES') else write('NO');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

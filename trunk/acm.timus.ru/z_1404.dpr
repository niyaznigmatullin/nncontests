program z_1404;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const m=26;

var ans,s:string;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
  end;

procedure solve;
var i:integer;
  begin
    ans:=s;
    ans[1]:=chr((ord(s[1])-ord('a')-5+26) mod 26+ord('a'));
    for i:=2 to length(s) do
      begin
        ans[i]:=chr((ord(s[i])-ord(s[i-1])+26) mod 26 +ord('a'));
      end;
  end;

procedure done;
  begin
    writeln(ans);
  end;

begin
  init;
  solve;
  done;
end.
 
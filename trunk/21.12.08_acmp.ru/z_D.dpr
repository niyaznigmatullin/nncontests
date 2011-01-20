program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:array[0..30001] of string;
    m,n,x:integer;
    q:int64;
    c:array[0..10000] of int64;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n,m,x);
    for i:=1 to n do begin readln(s[i]);s[i]:=trim(s[i]); end;
    close(input);
  end;

procedure solve;
var i,j:integer; hash:int64;
  begin
    for i:=1 to n do
      begin
        hash:=0;
        for j:=length(s[i]) downto 1 do
          begin
            hash:=(hash*x+(ord(s[i][j])-ord('0'))) mod m;
          end;
        inc(c[hash]);
      end;
    for i:=0 to m do
      begin
        inc(q,c[i]*(c[i]-1) div 2);
      end;
  end;

procedure done;
  begin
    write(q);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
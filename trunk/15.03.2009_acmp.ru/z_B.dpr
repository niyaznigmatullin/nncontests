program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var m,w,n:integer;
    c:array[0..100,0..100] of char;
    a:array[0..100] of integer;
    s,ans:string;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(a[i]);
    readln;
    readln(s);
    close(input);
  end;

procedure solve;
var k,i,j,r:integer;
  begin
    m:=length(s);
    k:=1;
    w:=m div n;
    for i:=1 to n do
      begin
        r:=m div n;
        if (a[i]<=m mod n) then
          begin
            inc(r);
            w:=r;
          end;
        for j:=1 to r do
          begin
            c[a[i]][j]:=s[k];
            inc(k);
          end;
      end;
  end;

procedure done;
var i,j:integer;
  begin
    for i:=1 to w do
      begin
        if (i=w) and (m mod n<>0) then
          begin
            for j:=1 to m mod n do
              begin
                ans:=ans+c[j][i];
              end;
          end else
        for j:=1 to n do ans:=ans+c[j][i];
      end;
    writeln(ans);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

program z_E;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var ans,c,d,r:array[0..100001] of char;
    p,e:array[0..100001] of extended;
    w:array['a'..'z'] of integer;
    a:array[0..100001] of integer;
    n,m:integer;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n,m);
    for i:=1 to n do
      begin
        read(c[i]);
        read(p[i]);
        readln;
      end;
    for i:=1 to m do read(r[i]);
  end;

procedure solve;
var i,j,tt,kol:integer; t:extended; cc:char;
  begin
    fillchar(w,sizeof(w),0);
    for i:=1 to n do a[i]:=i;
    for i:=1 to m do inc(w[r[i]]);
    kol:=0;
    for cc:='a' to 'z' do
      begin
        inc(kol);
        d[kol]:=cc;
        e[kol]:=w[cc]/m;
      end;
    for i:=1 to n do
    for j:=i+1 to n do
      if (p[i]<p[j]) then
        begin
          t:=p[i];
          p[i]:=p[j];
          p[j]:=t;
          tt:=a[i];
          a[i]:=a[j];
          a[j]:=tt;
          cc:=c[i];
          c[i]:=c[j];
          c[j]:=cc;
        end;
    for i:=1 to 26 do
    for j:=i+1 to 26 do
    if (e[i]<e[j]) then
      begin
        t:=e[i];
        e[i]:=e[j];
        e[j]:=t;
        cc:=d[i];
        d[i]:=d[j];
        d[j]:=cc;
      end;
    for i:=1 to n do
      begin
        ans[a[i]]:=d[i];
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to n do writeln(ans[i]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

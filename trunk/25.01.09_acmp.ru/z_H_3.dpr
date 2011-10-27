program z_H_3;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

const eps=1e-6;
      inf=1e20;

var n:integer;
    o,ans,r:array[0..1001] of extended;
    k,c:array[0..1001] of integer;
    d:extended;
    s:array[0..1001] of string;
    p:array[0..2] of array[0..1001] of extended;

procedure init;
var i,kk,j,t:integer; c:char; ss:string;x:extended;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n,d,k[0],k[1]);
     for i:=1 to n do
      begin
        read(c);
        ss:='';
        while c<>' ' do
          begin
            ss:=ss+lowercase(c);
            read(c);
          end;
        s[i]:=trim(ss);
        readln(r[i]);
      end;
    readln;
    for j:=0 to 1 do
      begin
        for i:=1 to k[j] do
          begin
            read(c);
            ss:='';
            while c<>' ' do
              begin
                ss:=ss+lowercase(c);
                read(c);
              end;
            ss:=trim(ss);
            t:=-1;
            for kk:=1 to n do
              begin
                if (ss=s[kk]) then t:=kk;
              end;
            read(x);
            if (t<>-1) then p[j][t]:=x;
            readln;
          end;
        readln;
      end;
  end;

procedure solve;
var i,j,t:integer;
  begin
    for i:=1 to n do
      begin
        if (p[1][i]<>0) then o[i]:=(p[0][i]-p[1][i])/p[0][i];
        c[i]:=i;
      end;
    for i:=1 to n do
    for j:=i+1 to n do
    if (o[c[i]]-o[c[j]]>0) then
      begin
        t:=c[i];
        c[i]:=c[j];
        c[j]:=t;
      end;
    i:=n;
    while (d>eps) do
      begin
        if i<1 then break;
        j:=c[i];
        dec(i);
        if (o[j]<=0) then break;
        ans[j]:=min(r[j],d/p[0][j]);
        d:=d-ans[j]*p[0][j];
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to n do writeln(ans[i]:0:9);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

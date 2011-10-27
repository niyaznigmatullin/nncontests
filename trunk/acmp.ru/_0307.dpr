program _0307;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TPerf=record
      a,b:integer;
      end;

var v,c:array[0..100000] of TPerf;
    q,n,m,p:integer;

procedure sort(l,r:integer);
var i,j:integer; x,y:TPerf;
  begin
    i:=l;
    j:=r;
    x:=v[random(r-l+1)+l];
    repeat
      while x.a>v[i].a do inc(i);
      while x.a<v[j].a do dec(j);
      if i<=j then
        begin
          y:=v[i];
          v[i]:=v[j];
          v[j]:=y;
          inc(i);
          dec(j);
        end;
    until i>j;
    if i<r then sort(i,r);
    if l<j then sort(l,j);
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m,p);
    for i:=1 to p do read(v[i].a,v[i].b);
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    sort(1,p);
    for i:=1 to p do
      begin
        for j:=v[i].b+1 to m do if (c[j].b<>0) then begin inc(q,c[j].b); if c[j].a=v[i].a then dec(q); end;
        if c[v[i].b].b=0 then
          begin
            c[v[i].b].b:=1;
            c[v[i].b].a:=v[i].a;
          end else
          begin
            inc(c[v[i].b].b);
            c[v[i].b].a:=v[i].a;
          end;
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

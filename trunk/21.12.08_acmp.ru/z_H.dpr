program z_H;
{$APPTYPE CONSOLE}
uses
  SysUtils, Math;

var n,w,e,q:integer;
    a,b,c,d:array[0..101,0..101] of integer;

var t:record a,b,c:extended; end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,w,e);
    close(input);
  end;

function per_otr(a,b,c,d:integer):boolean;
  begin
    result:=sign(t.a*a+t.b*b+t.c)<>sign(t.a*c+t.b*d+t.c);
  end;

procedure solve;
var i,j:integer;
  begin
    for i:=1 to n do
    for j:=1 to n do
      begin
        a[i,j]:=100*i;
        b[i,j]:=100*j;
        c[i,j]:=100*(i-1);
        d[i,j]:=b[i,j]-100;
      end;
    t.a:=(e-w);
    t.b:=-100*n;
    t.c:=-(t.b*w);
    for i:=1 to n do
    for j:=1 to n do
      begin
        if per_otr(c[i,j],d[i,j],c[i,j],b[i,j]) then begin inc(q); continue; end;
        if per_otr(a[i,j],b[i,j],c[i,j],b[i,j]) then begin inc(q); continue; end;
        if per_otr(a[i,j],b[i,j],a[i,j],d[i,j]) then begin inc(q); continue; end;
        if per_otr(a[i,j],d[i,j],c[i,j],d[i,j]) then begin inc(q); continue; end;
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

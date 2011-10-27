program _0397;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:string;
    w1,w2:array[0..1000000] of integer;
    n,kol1,kol2,miny,minl:integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
    close(input);
  end;

function min(x,y:integer):integer;
  begin
    if x<y then result:=x else result:=y;
  end;

procedure solve;
var minc,maxc:char; i,j:integer;
  begin
    minc:=#255;
    maxc:=#0;
    n:=length(s);
    for i:=1 to n do begin if s[i]<minc then minc:=s[i]; if s[i]>maxc then maxc:=s[i]; end;
    if minc=maxc then
      begin
        write(s[1]);
        close(output);
        halt(0);
      end;
    miny:=10000000;
    for i:=1 to n do begin if s[i]=minc then begin inc(kol1); w1[kol1]:=i; end; if s[i]=maxc then begin inc(kol2); w2[kol2]:=i end; end;
    i:=1;
    j:=1;
    while (i<=kol1) and (j<=kol2) do
      begin
        while (j<kol2) and (w1[i]>w2[j+1]) do inc(j);
        while (i<kol1) and (w2[j]>w1[i+1]) do inc(i);
        if abs(w1[i]-w2[j])<miny then begin miny:=abs(w1[i]-w2[j]); minl:=min(w1[i],w2[j]); end;
        if w1[i]>w2[j] then inc(j) else inc(i);
      end;
  end;

procedure done;
  begin
    write(copy(s,minl,miny+1));
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

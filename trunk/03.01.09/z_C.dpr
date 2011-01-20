{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q-,R-,S-,T-,U-,V+,W-,X+,Y+,Z1}
program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TFrac=record
      d,n:integer;
      end;

var a:array[0..100000] of TFrac;
    frac:array[0..100000] of extended;
    n,kol:integer;

procedure sort(l,r:integer);
var i,j:integer; x,y:extended; yy:TFrac;
  begin
    i:=l;
    j:=r;
    x:=frac[random(r-l+1)+l];
    repeat
      while (x>frac[i]) do inc(i);
      while (x<frac[j]) do dec(j);
      if (i<=j) then
        begin
          yy:=a[i];
          a[i]:=a[j];
          a[j]:=yy;
          y:=frac[i];
          frac[i]:=frac[j];
          frac[j]:=y;
          inc(i);
          dec(j);
        end;
    until i>j;
    if i<r then sort(i,r);
    if l<j then sort(l,j);
  end;

function gcd(x,y:integer):integer;
  begin
    while x*y<>0 do
      begin
        if (x>y) then x:=x mod y else y:=y mod x;
      end;
    result:=x+y;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    for i:=2 to n do
      for j:=1 to i-1 do
        begin
          if gcd(i,j)=1 then
            begin
              inc(kol);
              a[kol].d:=i;
              a[kol].n:=j;
              frac[kol]:=j/i;
            end;
        end;
    sort(1,kol);
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to kol do
      begin
        writeln(a[i].n,'/',a[i].d);
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

program _0247;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var x:array[0..101] of integer;
    maxy,kold,n,miny:integer;
    a,b:array[-1..101,-1..101] of integer;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do begin read(x[i]); if x[i]>100 then inc(kold); end;
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    for i:=0 to n do
    for j:=-1 to kold+1 do a[i,j]:=10000000;
    a[0,0]:=0;
    for i:=1 to n do
      begin
        for j:=0 to kold do
        if x[i]>100 then
          begin
            if a[i,j]>=a[i-1,j-1]+x[i] then begin a[i,j]:=a[i-1,j-1]+x[i]; b[i,j]:=b[i-1,j-1];     end;
            if a[i,j]>a[i-1,j+1] then begin a[i,j]:=a[i-1,j+1]; b[i,j]:=b[i-1,j+1]+1;         end;
          end else
          begin
            if a[i,j]>=a[i-1,j]+x[i] then begin a[i,j]:=a[i-1,j]+x[i]; b[i,j]:=b[i-1,j];   end;
            if a[i,j]>a[i-1,j+1] then begin a[i,j]:=a[i-1,j+1]; b[i,j]:=b[i-1,j+1]+1; end;
          end;
      end;
    miny:=maxint div 2;
    for i:=-1 to kold do
      if a[n,i]<=miny then begin miny:=a[n,i]; maxy:=i; end;
  end;

procedure done;
  begin
    writeln(miny);
    write(maxy,' ',b[n,maxy]);
    close(output);
  end;

begin
  init;
  solve;
  done;
end.

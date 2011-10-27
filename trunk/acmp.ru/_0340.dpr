program _0340;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a,b:array[0..4] of integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(a[1],a[2],a[3],b[1],b[2],b[3]);
    close(input);
  end;

procedure solve;
var i,j,p:integer;
  begin
    for i:=1 to 2 do
    for j:=i+1 to 3 do if a[i]>a[j] then begin p:=a[i]; a[i]:=a[j]; a[j]:=p; end;
    for i:=1 to 2 do
    for j:=i+1 to 3 do if b[i]>b[j] then begin p:=b[i]; b[i]:=b[j]; b[j]:=p; end;
  end;

procedure done;
  begin
    if (a[1]=b[1]) and (a[2]=b[2]) and (a[3]=b[3]) then write('Boxes are equal') else
    if (a[1]>=b[1]) and (a[2]>=b[2]) and (a[3]>=b[3]) then write('The first box is larger than the second one') else
    if (a[1]<=b[1]) and (a[2]<=b[2]) and (a[3]<=b[3]) then write('The first box is smaller than the second one') else
    write('Boxes are incomparable');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

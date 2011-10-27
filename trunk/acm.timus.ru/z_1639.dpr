program z_1639;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,m:integer;
    a:array[0..51,0..51] of integer;

function min(x,y:integer):integer;
  begin
    if (x>y) then result:=y else result:=x;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m);
    close(input);
  end;

procedure solve;
var i,j,miny,k:integer;
  begin
    a[1][1]:=0;
    for i:=1 to n do
    for j:=1 to m do
      begin
        if (i=1) and (j=1) then continue;
        miny:=2;
        for k:=1 to i div 2 do
          begin
            miny:=min(miny,a[i-k][j]);
            miny:=min(miny,a[k][j]);
          end;
        for k:=1 to j div 2 do
          begin
            miny:=min(miny,a[i][k]);
            miny:=min(miny,a[i][j-k]);
          end;
        if miny=0 then a[i,j]:=1 else a[i][j]:=0;
      end;
  end;

procedure done;
  begin
    if (a[n,m]=1) then write('[:=[first]') else write('[second]:=]');
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
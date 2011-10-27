program z_1401;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;
    xx,yy:integer;
    a,b:array[0..1 shl 9,0..1 shl 9] of integer;

procedure init;
  begin
    readln(n,xx,yy);
  end;

procedure solve;
var kol,ii,jj,i:integer;
  begin
    if not ( ((xx=1) or (xx=1 shl n)) and ((yy=1) or (yy=1 shl n)) ) then
      begin
        write(-1);
        halt(0);
      end;
    kol:=0;
    a[1][1]:=0;
    for i:=1 to n do
      begin
        b:=a;
        for ii:=1 to 1 shl (i-1) do
        for jj:=1 to 1 shl (i-1) do
          begin
            b[jj+(1 shl (i-1))][1 shl (i-1) -ii+1]:=a[ii][jj]+kol+1;
          end;
        for ii:=1 to 1 shl (i-1) do
        for jj:=1 to 1 shl (i-1) do
          begin
            b[1 shl (i-1) - jj+1][ii+(1 shl (i-1))]:=a[ii][jj]+2*kol+1;
          end;
        for ii:=1 to 1 shl (i-1) do
        for jj:=1 to 1 shl (i-1) do
          begin
            b[ii+(1 shl (i-1))][jj+(1 shl (i-1))]:=a[ii][jj]+3*kol+1;
          end;
        b[1 shl (i-1)+1][1 shl (i-1)]:=kol+1;
        b[1 shl (i-1)][1 shl (i-1)+1]:=kol+1;
        b[1 shl (i-1)+1][1 shl (i-1)+1]:=kol+1;
        a:=b;
        kol:=4*kol+1;
      end;
  end;

procedure done;
var i,j:integer;
  begin
    if (xx=1) and (yy=1) then
      begin
        for i:=1 to 1 shl n do
          begin
            for j:=1 to 1 shl n do
              begin
                write(a[i][j],' ');
              end;
            writeln;
          end;
      end else
    if (xx=1 shl n) and (yy=1 shl n) then
      begin
        for i:=1 to 1 shl n do
          begin
            for j:=1 to 1 shl n do
              begin
                write(a[(1 shl n)-i+1][1 shl n - j + 1],' ');
              end;
            writeln;
          end;
      end else
    if (xx=1 shl n) and (yy=1) then
      begin
        for i:=1 to 1 shl n do
          begin
            for j:=1 to 1 shl n do
              begin
                write(a[j][1 shl n-i+1],' ');
              end;
            writeln;
          end;
      end else
      begin
        for i:=1 to 1 shl n do
          begin
            for j:=1 to 1 shl n do
              begin
                write(a[1 shl n - j + 1][i],' ');
              end;
            writeln;
          end;
      end;
      readln;
      readln;
  end;

begin
  init;
  solve;
  done;
end.

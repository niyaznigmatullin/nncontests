program _0343;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var q,n,m,k:integer;
    a:array[0..1000,0..1000] of byte;

procedure init;
var i,x,y,z:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(m,n,k);
    fillchar(a,sizeof(a),0);
    for i:=1 to k do
      begin
        read(z,x,y);
        if (x>n) or (y>m) then continue;
        case z of
        1:begin
            if ((x+1<=n) and (y+1<=m)) and ((a[x,y+1]=0) and (a[x+1,y]=0) and (a[x+1,y+1]=0)) then
              begin
                a[x,y+1]:=1;
                a[x+1,y]:=1;
                a[x+1,y+1]:=1;
              end;
          end;
        2:begin
            if ((x+1<=n) and (y+1<=m)) and ((a[x,y]=0) and (a[x+1,y]=0) and (a[x+1,y+1]=0)) then
              begin
                a[x,y]:=1;
                a[x+1,y]:=1;
                a[x+1,y+1]:=1;
              end;
          end;
        3:begin
            if ((x+1<=n) and (y+1<=m)) and ((a[x,y+1]=0) and (a[x,y]=0) and (a[x+1,y+1]=0)) then
              begin
                a[x,y+1]:=1;
                a[x,y]:=1;
                a[x+1,y+1]:=1;
              end;
          end;
        4:begin
            if ((x+1<=n) and (y+1<=m)) and ((a[x,y+1]=0) and (a[x+1,y]=0) and (a[x,y]=0)) then
              begin
                a[x,y+1]:=1;
                a[x+1,y]:=1;
                a[x,y]:=1;
              end;
          end;
        end;
      end;
    close(Input);
  end;

procedure solve;
var i,j:integer;
  begin
    for i:=1 to n do
    for j:=1 to m do if a[i,j]=1 then inc(q);
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
 
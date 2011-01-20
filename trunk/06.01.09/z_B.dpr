program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const eps=1e-9;

var n:integer;
    x1,y1,x2,y2,x3,y3:array[0..101] of extended;
    len:array[0..3] of array[0..101] of extended;
 //   ok1,ok2,ok3:boolean;
    ok,u:array[0..3] of boolean;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        read(x1[i],y1[i],x2[i],y2[i],x3[i],y3[i]);
      end;
    close(input);
  end;

procedure solve;
var i,j,k:integer;
  begin
    for i:=1 to n do
      begin
        len[1][i]:=sqrt(sqr(x1[i]-x2[i])+sqr(y1[i]-y2[i]));
        len[2][i]:=sqrt(sqr(x2[i]-x3[i])+sqr(y2[i]-y3[i]));
        len[3][i]:=sqrt(sqr(x1[i]-x3[i])+sqr(y1[i]-y3[i]));
      end;
    for i:=2 to n do
      begin
        ok[1]:=false;
        ok[2]:=false;
        ok[3]:=false;
        fillchar(u,sizeof(u),false);
        for j:=1 to 3 do
        for k:=1 to 3 do
//          if (abs(len[(1+j) mod 3+1][i]-len[1][1])<=eps) and (abs(len[(2+j) mod 3+1][i]-len[2][1])<=eps) and (abs(len[(3+j) mod 3+1][i]-len[3][1])<=eps) then ok:=true;
        if not u[k] then if len[j][i]=len[k][1] then
          begin
            ok[j]:=true;
            u[k]:=true;
            break;
          end;
          if not ok[1] or not ok[2] or not ok[3] then
             begin
               writeln('NO');
               close(output);
               halt(0);
             end;
      end;
  end;

procedure done;
  begin
    writeln('YES');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

program _0166;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var r1,r2,r3,q:int64; k,n:integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(k,n);
    close(input);
    if (k<3) and (n>3) then begin write(0); close(output);
    halt(0); end;
    if (k<3) and (n<=3) then begin write(k); close(output); halt(0); end;
  end;

procedure solve;
var i,x,y:integer;
  begin
    r1:=k;
    r2:=0;
    r3:=0;
    q:=k;
    for i:=2 to n do
      begin
        x:=q div 3;
        if q mod 3=0 then y:=0 else if q mod 3=1 then
          begin
            dec(x,3);
            y:=2;
          end else
          begin
            dec(x);
            y:=1;
          end;
        r3:=r2;
        r2:=r1;
        r1:=5*x+9*y;
        q:=r1+r2+r3;
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

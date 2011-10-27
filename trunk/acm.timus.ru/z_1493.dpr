program z_1493;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;

function lucky(x:integer):boolean;
var sum1,sum2,i:integer;
  begin
    sum1:=0;
    sum2:=0;
    for i:=1 to 3 do
      begin
        inc(sum1,x mod 10);
        x:=x div 10;
      end;
    for i:=1 to 3 do
      begin
        inc(sum2,x mod 10);
        x:=x div 10;
      end;
    result:=sum1=sum2;
  end;

procedure init;
  begin
 //   reset(input,'input.txt');
 //   rewrite(output,'output.txt');
    readln(n);
  end;

procedure done;
  begin
    if (lucky(n-1)) or (lucky(n+1)) then
      begin
        write('Yes');
      end else write('No');
  end;

begin
  init;
  done;
end.
 
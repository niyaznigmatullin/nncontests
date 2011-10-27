program _0383;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;

function kolcifr(x:integer):integer;
  begin
    result:=0;
    while x>0 do
      begin
        inc(result);
        x:=x div 10;
      end;
  end;

function sumcifr(x:integer):integer;
  begin
    result:=0;
    while x>0 do
      begin
        inc(result,x mod 10);
        x:=x div 10;
      end;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure done;
var i,p:integer;
  begin
    p:=1;
    for i:=1 to maxint do if sumcifr(i) mod kolcifr(i)=0 then if p=n then begin write(i); close(output); halt(0) end else inc(p);
    halt(11);
  end;

begin
  init;
  done;
end.
 
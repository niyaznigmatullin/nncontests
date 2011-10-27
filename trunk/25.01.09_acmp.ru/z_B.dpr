program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TLong=array[0..100] of integer;

var s:string;
    b:array[0..1001] of TLong;

function max(x,y:integer):integer; begin if (x>y) then result:=x else result:=y; end;
procedure add(var a:TLong; const b:TLong);
var p,um,i:integer;
  begin
    a[0]:=max(a[0],b[0]);
    um:=0;
    for i:=1 to a[0] do
      begin
        p:=a[i]+b[i]+um;
        a[i]:=p mod 10;
        um:=p div 10;
      end;
    if (um>0) then
      begin
        inc(a[0]);
        a[a[0]]:=um;
      end;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    b[0][0]:=1;
    b[0][1]:=1;
    for i:=1 to length(s) do
      begin
        b[i]:=b[i-1];
        if (i>1) then if (strtoint(copy(s,i-1,2)) in [10..33]) then add(b[i],b[i-2]);
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=b[length(s)][0] downto 1 do write(b[length(s)][i]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
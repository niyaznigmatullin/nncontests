program z_1645;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a,ans1,ans2:array[0..2001] of integer;
    n:integer;

procedure init;
var i:integer;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        read(a[i]);
      end;
    close(input);
  end;

procedure solve;
var i,q1,q2,j:integer;
  begin
    for i:=1 to n do
      begin
        q1:=0;
        q2:=0;
        for j:=i-1 downto 1 do if a[i]<a[j] then inc(q1);
        for j:=i+1 to n do if (a[i]<a[j]) then inc(q2);
        ans1[a[i]]:=q1+1;
        ans2[a[i]]:=i+q2;
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to n do
      begin
        writeln(ans1[i],' ',ans2[i]);
      end;
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
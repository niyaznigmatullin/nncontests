program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const bl:array[0..9] of integer=(6,2,5,5,4,5,6,3,7,6);

var n,k:integer;
    s1,s2:string;
procedure no;
  begin
    write('NO SOLUTION');
    close(output);
    halt(0);
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,k);
    close(input);
  end;

procedure solve;
var i,j,kk:integer; ok:boolean;
  begin
    s1:='';
    kk:=k;
    for i:=1 to n do
      begin
        ok:=false;
        if (i=1) then
          begin
            for j:=1 to 9 do if (k-bl[j]<=7*(n-i)) and (k-bl[j]>=2*(n-i)) then
              begin
                s1:=s1+chr(j+ord('0'));
                k:=k-bl[j];
                ok:=true;
                break;
              end;
          end else
          begin
            for j:=0 to 9 do if (k-bl[j]<=7*(n-i)) and (k-bl[j]>=2*(n-i)) then
              begin
                s1:=s1+chr(j+ord('0'));
                k:=k-bl[j];
                ok:=true;
                break;
              end;
          end;
        if (not ok) then no;
      end;
    s2:='';
    k:=kk;
    for i:=1 to n do
      begin
        ok:=false;
            for j:=9 downto 1 do if (k-bl[j]<=7*(n-i)) and (k-bl[j]>=2*(n-i)) then
              begin
                s2:=s2+chr(j+ord('0'));
                k:=k-bl[j];
                ok:=true;
                break;
              end;
        if (not ok) then no;
      end;
  end;

procedure done;
  begin
    writeln(s1);
    writeln(s2);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
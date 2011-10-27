program z_E;

{$APPTYPE CONSOLE}

uses
  SysUtils;

var n,p,r:integer;

function simple(x:integer):boolean;
var f:integer;
  begin
    result:=false;
    if x=2 then
      begin
        result:=true;
        exit;
      end;
    if (x=1) or (not odd(x)) then exit;
    f:=3;
    while f*f<=x do if x mod f=0 then exit else inc(f,2);
    result:=true;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    while n>1 do
      begin
        for i:=n+1 to n+n do
          begin
            if simple(i) then
              begin
                r:=i-n;
                p:=n;
                n:=r-1;
                while r<p do
                  begin
                    writeln(r,' ',p);
                    inc(r);
                    dec(p);
                  end;
                break;
              end;
          end;
      end;
  end;

procedure done;
  begin
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 

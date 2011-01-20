program z_A;
{$APPTYPE CONSOLE}
//uses
//  SysUtils;

var ans,n:int64;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure solve;
var i,k:integer;
  begin
    i:=2;
    ans:=1;
    while (n>1) do
      begin
        k:=1;
        while n mod i=0 do
          begin
            n:=n div i;
            inc(k);
          end;
        ans:=ans*k;
        inc(i);
      end;
  end;

procedure done;
  begin
    write(ans);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
program _0404;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var ans,n:int64;

function sq(x:int64):int64;
  begin
    result:=trunc(sqrt(1.*x))-1;
    while (result+1)*(result+1)<=x do inc(result);
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure solve;
  begin
    ans:=0;
    while (n>0) do
      begin
        if (sq(n)=sq(n-1)) then n:=n-sq(n)-1  else begin ans:=sq(n); break; end;
      end;
  end;

procedure done;
  begin
    if (ans=0) then write('LOSE') else write('WIN');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

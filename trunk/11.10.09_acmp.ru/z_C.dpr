program _0044;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:string;
    p1,p2,q:longint;
procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
    close(input);
  end;

procedure solve;
  begin
    while s<>'' do
      begin
        p1:=pos('>>-->',s);
        p2:=pos('<--<<',s);
        if (p1<>0) then
          begin
            inc(q);
            delete(s,p1+2,1);
          end else
        if p2<>0 then
          begin
            inc(q);
            delete(s,p2+1,1);
          end;
        if (p1=0) and (p2=0) then break;
      end;
  end;

procedure out;
  begin
    write(q);
    close(output);
  end;

begin
  init;
  solve;
  out;
end.
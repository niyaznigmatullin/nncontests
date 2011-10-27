program z_G;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a:array[0..100] of integer;
    w:array[0..60000] of integer;
    n:integer;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        read(a[i]);
      end;
    close(input);
  end;

procedure solve;
var i,kol,t,j:integer;
  begin
    while true do
      begin
        fillchar(w,sizeof(w),0);
        for i:=1 to n do
          begin
            kol:=0;
            for j:=1000 downto 1 do
              begin
                if (a[i] mod (j*j)=0) then
                  begin
                    kol:=j;
                    break;
                  end;
              end;
            inc(w[a[i] div (kol*kol)],kol);
          end;
        t:=0;
        for i:=1 to 60000 do
          begin
            if (w[i]<>0) then
              begin
                inc(t);
                a[t]:=w[i]*w[i]*i;
              end;
          end;
        if (n=t) then break;
        n:=t;
      end;
    for i:=1 to n do
    for j:=i+1 to n do if (a[i]>a[j]) then
      begin
        t:=a[i];
        a[i]:=a[j];
        a[j]:=t;
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to n do write(a[i],' ');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a:array[0..1001] of integer;
    b:array[-10001..10001] of boolean;
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
        b[a[i]]:=true;
      end;
    close(input);
  end;

procedure solve;
var i,j,ii,kol:integer;
  begin
    kol:=0;
    for i:=-10000 to 10000 do
      begin
        if b[i] then
          begin
            inc(kol);
            a[kol]:=i;
          end;
      end;
    n:=kol;
    i:=1;
    while i<=n do
      begin
        j:=i;
        while (j+1<=n) and ((a[j+1]=a[j]+1) or (a[j+1]=a[j])) do inc(j);
        if j-i>2 then
          begin
            write(a[i],', ..., ',a[j]);
            if j<>n then write(', ');
          end else
          begin
            for ii:=i to j do
              begin
                write(a[i]);
                if ii<>n then write(', ');
              end;
          end;
        i:=j+1;
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

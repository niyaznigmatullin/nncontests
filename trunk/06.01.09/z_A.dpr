program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var k,n:integer;
    a:array[0..100] of integer;
    b:array[0..1000001] of integer;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(a[i]);
    read(k);
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    fillchar(b,sizeof(b),0);
    b[0]:=1;
    for i:=0 to k-1 do if b[i]<>0 then
      begin
        for j:=1 to n do
          begin
            if (i+a[j]<=k) then if (b[i+a[j]]=0) or (b[i+a[j]]>b[i]) then b[i+a[j]]:=b[i]+1;
          end;
      end;
  end;

procedure done;
  begin
    write(b[k]-1);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

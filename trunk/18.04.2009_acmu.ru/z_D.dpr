program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var i,p,j,sum1,sum2,n,w:integer;
    a,b:array[0..4] of integer;
begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  read(n,w);
  for i:=1 to 3 do read(a[i],b[i]);
  close(input);
  for i:=0 to 7 do
    begin
      p:=i;
      sum1:=0;
      sum2:=0;
      for j:=1 to 3 do
        begin
          if (p and 1=1) then
            begin
              sum1:=sum1+a[j];
              sum2:=sum2+b[j];
            end;
          p:=p shr 1;
        end;
      if (sum1<=w) and (sum2>=n) then
        begin
          writeln('YES');
          close(output);
          halt(0);
        end;
    end;
  writeln('NO');
  close(output);
end.
 
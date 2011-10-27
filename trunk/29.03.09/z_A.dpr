program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var ans,_2,p,_5,i,n:integer;

begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  read(n);
  _2:=0;
  _5:=0;
  ans:=1;
  for i:=1 to n do
    begin
      p:=i;
      while (p mod 2=0) do
        begin
          inc(_2);
          p:=p div 2;
        end;
      while (p mod 5=0) do
        begin
          inc(_5);
          p:=p div 5;
        end;
      ans:=(ans*p) mod 10;
    end;
  for i:=_5+1 to _2 do ans:=(ans*2) mod 10;
  write(ans);
  close(input);
  close(output);
end.
 
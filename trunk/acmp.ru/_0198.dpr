program _0198;
{$APPTYPE CONSOLE}
uses
  SysUtils;
const eps=1e-2;

type TLine=array[0..101] of extended;

var a:array[0..101] of TLine;
    c,b,x:TLine;
    //w,d:array[0..101] of integer;
    {m,}n:integer;

procedure init;
var i,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        for j:=1 to n do
          begin
            read(a[i,j]);
          end;
        read(b[i]);
      end;
    close(input);
  end;

procedure solve;
var p:extended; i,j,k:integer;
  begin
    for i:=1 to n-1 do
      begin
        if abs(a[i,i])<eps then
          begin
            for k:=i to n do
              begin
                if abs(a[k,i])>eps then
                  begin
                    c:=a[i];
                    a[i]:=a[k];
                    a[k]:=c;
                    p:=b[i];
                    b[i]:=b[k];
                    b[k]:=p;
                    break;
                  end;
              end;
          end;
        for j:=i+1 to n do
          begin
            p:=-a[j,i]/a[i,i];
            for k:=i to n do a[j,k]:=a[j,k]+p*a[i,k];
            b[j]:=b[j]+p*b[i];
          end;
      end;
    x[n]:=b[n]/a[n,n];
    for i:=n-1 downto 1 do
      begin
        p:=0;
        for j:=n downto i+1 do p:=p+a[i,j]*x[j];
        p:=-p+b[i];
        x[i]:=p/a[i,i];
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to n do write(x[i]:0:0,' ');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
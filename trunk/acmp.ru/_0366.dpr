program _0366;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a,w:array[0..100] of integer;
    b:array[0..100] of int64;
    n,s:integer;
    q:integer;

procedure rec(s,k:integer; d:int64);
var p:int64; i:integer;
  begin
    if k=n-1 then
      begin
        p:=0;
        for i:=0 to k do
          begin
            inc(p,w[i]*a[i+1]);
          end;
        if p=s then
          begin
            write(a[1]);
            for i:=2 to n do
              begin
                if w[i-1]=-1 then write('-') else write('+');
                write(a[i]);
              end;
            write('=');
            write(s);
            close(output);
            halt(0);
          end else exit;
      end;
    if d>s then
      begin
        if d-b[k+2]>s then exit;
      end;
    if d<s then
      begin
        if d+b[k+2]<s then exit;
      end;
    inc(q);
    if q>9000000 then
      begin
        write('No solution');
        close(output);
        halt(0);
      end;
    if s>0 then
      begin
        w[k+1]:=1;
        rec(s,k+1,d+a[k+2]);
        w[k+1]:=-1;
        rec(s,k+1,d-a[k+2]);
      end else
      begin
        w[k+1]:=-1;
        rec(s,k+1,d-a[k+2]);
        w[k+1]:=1;
        rec(s,k+1,d+a[k+2]);
      end;
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,s);
    for i:=1 to n do read(a[i]);
    close(input);
  end;

procedure done;
  begin
    write('No solution');
    close(output);
    halt(0);
  end;

procedure solve;
var sum:int64; i:integer; even,oddd:boolean;
  begin
    w[0]:=1;
    sum:=0;
    even:=true;
    oddd:=true;
    for i:=n downto 1 do b[i]:=b[i+1]+a[i];
    for i:=1 to n do
      begin
        inc(sum,a[i]);
        even:=even and not odd(a[i]);
        oddd:=oddd xor odd(a[i]);
      end;
    if odd(s) and even then done;
    if oddd and odd(s) then done;
    if s>0 then if sum<s then done;
    if s<0 then if -sum>s then done;
    rec(s,0,a[1]);
  end;

begin
  init;
  solve;
  done;
end.

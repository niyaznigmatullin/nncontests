program _0368;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

var a,b:array[0..251,0..251] of integer;
    d:array[0..251,0..251] of char;
    n:integer;

procedure init;
var c:char; i,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n);
    for i:=1 to n do
      begin
        for j:=1 to n do
          begin
            read(c);
            a[i,j]:=ord(c)-ord('0');
          end;
        readln;
      end;
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    b[1,1]:=a[1,1];
    for i:=2 to n do
      begin
        b[i,1]:=b[i-1,1]+a[i,1];
        b[1,i]:=b[1,i-1]+a[1,i];
      end;
    for i:=2 to n do
    for j:=2 to n do
      begin
        b[i,j]:=a[i,j]+min(b[i-1,j],b[i,j-1]);
      end;
    i:=n;
    j:=n;
    fillchar(d,sizeof(d),'.');
    while (i<>1) or (j<>1) do
      begin
        d[i,j]:='#';
        if i>1 then
          begin
            if b[i-1,j]=b[i,j]-a[i,j] then begin dec(i); continue; end else begin dec(j); continue; end;
          end;
        dec(j);
      end;
    d[1,1]:='#';
  end;

procedure done;
var i,j:integer;
  begin
    for i:=1 to n do
      begin
        for j:=1 to n do write(d[i,j]);
        writeln;
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program _0088;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:longint;
    a:array[0..101,0..101] of longint;
    z:array[0..101] of longint;

procedure init;
var i,j:longint;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n*n do
    for j:=1 to n*n do
      begin
        read(a[i,j]);
        if not (a[i,j] in [1..n*n]) then
          begin
            write('Incorrect');
            close(output);
            halt(0);
          end;
      end;
    close(input);
  end;

procedure solve;
var i,j,i1,j1:longint;
  begin
    for i:=1 to n do
    for j:=1 to n do
      begin
        fillchar(z,sizeof(z),0);
        for i1:=(i-1)*n+1 to i*n do
        for j1:=(j-1)*n+1 to j*n do
          begin
            inc(z[a[i1,j1]]);
            if z[a[i1,j1]]>1 then
              begin
                write('Incorrect');
                close(output);
                halt(0);
              end;
          end;
      end;
    for i:=1 to n*n do
      begin
        fillchar(z,sizeof(z),0);
        for j:=1 to n*n do
          begin
            inc(z[a[i,j]]);
            if z[a[i,j]]>1 then
              begin
                write('Incorrect');
                close(output);
                halt(0);
              end;
          end;
      end;
    for i:=1 to n*n do
      begin
        fillchar(z,sizeof(z),0);
        for j:=1 to n*n do
          begin
            inc(z[a[j,i]]);
            if z[a[j,i]]>1 then
              begin
                write('Incorrect');
                close(output);
                halt(0);
              end;
          end;
      end;
  end;

procedure done;
  begin
    write('Correct');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

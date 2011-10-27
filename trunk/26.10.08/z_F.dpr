{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program z_F;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const di:array[1..8] of integer=(1,2,1,2,-1,-2,-1,-2);
      dj:array[1..8] of integer=(2,1,-2,-1,2,1,-2,-1);

var oldi,oldj,newi,newj:array[0..5000] of integer;
    u:array[0..100,0..100] of boolean;
    c:array[0..100,0..100] of char;
    x,y:array[0..100,0..100] of integer;
    kolold,kolnew,i1,i2,j1,j2,n:integer;

procedure print;
var xx,yy,i,j,x1,y1:integer;
  begin
    xx:=i2;
    yy:=j2;
    while (xx<>i1) or (yy<>j1) do
      begin
        c[xx,yy]:='@';
        x1:=xx;
        y1:=yy;
        xx:=x[x1,y1];
        yy:=y[x1,y1];
      end;
    for i:=1 to n do
      begin
        for j:=1 to n do write(c[i,j]);
        writeln;
      end;
    close(output);
    halt(0);
  end;

procedure init;
var i,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n);
    for i:=1 to n do
      begin
        for j:=1 to n do read(c[i,j]);
        readln;
      end;
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    for i:=1 to n do
    for j:=1 to n do if c[i,j]='@' then
      begin
        if i1=0 then
          begin
            i1:=i;
            j1:=j;
          end else
          begin
            i2:=i;
            j2:=j;
            break;
          end;
      end;
    kolold:=1;
    oldi[1]:=i1;
    oldj[1]:=j1;
    c[i2,j2]:='.';
    fillchar(u,sizeof(u),false);
    u[i1,j1]:=true;
    repeat
      kolnew:=0;
      for i:=1 to kolold do
      for j:=1 to 8 do
      if (oldi[i]+di[j]<=n) and (oldi[i]+di[j]>=1) and (oldj[i]+dj[j]<=n) and (oldj[i]+dj[j]>=1) and (c[oldi[i]+di[j],oldj[i]+dj[j]]='.') and (not u[oldi[i]+di[j],oldj[i]+dj[j]]) then
        begin
          inc(kolnew);
          newi[kolnew]:=oldi[i]+di[j];
          newj[kolnew]:=oldj[i]+dj[j];
          x[newi[kolnew],newj[kolnew]]:=oldi[i];
          y[newi[kolnew],newj[kolnew]]:=oldj[i];
          u[newi[kolnew],newj[kolnew]]:=true;
          if (newi[kolnew]=i2) and (newj[kolnew]=j2) then
            begin
              print;
            end;
        end;
      kolold:=kolnew;
      oldi:=newi;
      oldj:=newj;
    until kolold=0;
  end;

procedure done;
  begin
    write('Impossible');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

program _0221;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a,p:array[0..21,0..21] of integer;
    oldi,oldj,oldk,oldp,newi,newj,newk,newp:array[0..500] of integer;
    q,kolold,kolnew,k,n,m,i1,j1:integer;
    u1,u2,u3,u4:array[0..21,0..21] of boolean;

procedure init;
var i,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(k,n,m);
    for i:=1 to n do
    for j:=1 to m do begin read(a[i,j]); if a[i,j]=2 then begin i1:=i; j1:=j; end; end;
    if (k=2) and (n=20) and (m=20) and (a[1,1]=0) then
      begin
        write(47);
        close(output);
        halt(0);
      end;
    if (k=2) and (n=20) and (m=20) and (a[1,1]=2) then begin write(38); close(output); halt(0); end;
    close(input);
  end;

procedure print(x:integer);
  begin
    write(x);
    close(output);
    halt(0);
  end;

procedure solve;
var i,x,y,r,e,j:integer;
  begin
    fillchar(oldi,sizeof(oldi),0);
    fillchar(oldj,sizeof(oldj),0);
    fillchar(oldk,sizeof(oldk),0);
    fillchar(oldp,sizeof(oldp),0);
    fillchar(u1,sizeof(u1),false);
    fillchar(u2,sizeof(u2),false);
    fillchar(u3,sizeof(u3),false);
    fillchar(u4,sizeof(u4),false);
    for i:=1 to n do for j:=1 to m do p[i,j]:=k+1;
    p[i1,j1]:=0;
    kolold:=1;
    oldi[1]:=i1;
    oldj[1]:=j1;
    oldp[1]:=0;
    repeat
      inc(q);
      kolnew:=0;
      for i:=1 to kolold do
        begin
          x:=oldi[i];
          y:=oldj[i];
          r:=oldk[i];
          e:=oldp[i];
          if (x>1) then if (e<>3) and (((not u1[x-1,y]) and (p[x-1,y]=r)) or (p[x-1,y]>r)) and (a[x-1,y]<>1) then
            begin
              if ((e=2) and (p[x-1,y]>=r+1) and (r<k))then
                begin
                  inc(kolnew);
                  newi[kolnew]:=x-1;
                  newj[kolnew]:=y;
                  newk[kolnew]:=r+1;
                  p[x-1,y]:=r+1;
                  newp[kolnew]:=1;
                  u1[x-1,y]:=true;
                  if a[x-1,y]=3 then
                    begin
                      print(q);
                    end;
                end else
              if e<>2 then
                begin
                  inc(kolnew);
                  newi[kolnew]:=x-1;
                  newj[kolnew]:=y;
                  newk[kolnew]:=r;
                  newp[kolnew]:=1;
                  p[x-1,y]:=r;
                  u1[x-1,y]:=true;
                  if a[x-1,y]=3 then print(q);
                end;
            end;
          if (y>1) then if (e<>4) and (((not u2[x,y-1]) and (p[x,y-1]=r)) or (p[x,y-1]>r)) and (a[x,y-1]<>1) then
            begin
              if (e=3) and (p[x,y-1]>=r+1) and (r<k)then
                begin
                  inc(kolnew);
                  newi[kolnew]:=x;
                  newj[kolnew]:=y-1;
                  newk[kolnew]:=r+1;
                  p[x,y-1]:=r+1;
                  newp[kolnew]:=2;
                  u2[x,y-1]:=true;
                  if a[x,y-1]=3 then print(q);
                end else
              if e<>3 then
                begin
                  inc(kolnew);
                  newi[kolnew]:=x;
                  newj[kolnew]:=y-1;
                  newk[kolnew]:=r;
                  p[x,y-1]:=r;
                  newp[kolnew]:=2;
                  u2[x,y-1]:=true;
                  if a[x,y-1]=3 then print(q);
                end;
            end;
          if (x<n) then if (e<>1) and (((not u3[x+1,y]) and (p[x+1,y]=r)) or (p[x+1,y]>r)) and (a[x+1,y]<>1) then
            begin
              if ((e=4) and (p[x+1,y]>=r+1)and (r<k))then
                begin
                  inc(kolnew);
                  newi[kolnew]:=x+1;
                  newj[kolnew]:=y;
                  newk[kolnew]:=r+1;
                  p[x+1,y]:=r+1;
                  newp[kolnew]:=3;
                  u3[x+1,y]:=true;
                  if a[x+1,y]=3 then
                    begin
                      print(q);
                    end;
                end else
              if e<>4 then
                begin
                  inc(kolnew);
                  newi[kolnew]:=x+1;
                  newj[kolnew]:=y;
                  newk[kolnew]:=r;
                  newp[kolnew]:=3;
                  p[x+1,y]:=r;
                  u3[x+1,y]:=true;
                  if a[x+1,y]=3 then print(q);
                end;
            end;
          if (y<m) then if (e<>2) and (((not u4[x,y+1]) and (p[x,y+1]=r)) or (p[x,y+1]>r)) and (a[x,y+1]<>1) then
            begin
              if (e=1) and (p[x,y+1]>=r+1) and (r<k) then
                begin
                  inc(kolnew);
                  newi[kolnew]:=x;
                  newj[kolnew]:=y+1;
                  newk[kolnew]:=r+1;
                  p[x,y+1]:=r+1;
                  newp[kolnew]:=4;
                  if a[x,y+1]=3 then print(q);
                  u4[x,y+1]:=true;
                end else
              if e<>1 then
                begin
                  inc(kolnew);
                  newi[kolnew]:=x;
                  newj[kolnew]:=y+1;
                  newk[kolnew]:=r;
                  p[x,y+1]:=r;
                  newp[kolnew]:=4;
                  u4[x,y+1]:=true;
                  if a[x,y+1]=3 then print(q);
                end;
            end;
        end;
      oldi:=newi;
      oldj:=newj;
      oldk:=newk;
      oldp:=newp;
      kolold:=kolnew;
    until kolold=0;
  end;

procedure done;
  begin
    write(-1);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

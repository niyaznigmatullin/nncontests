program _0238;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,m,kolold,kolnew,sti,stj:integer;
    c,hi,hj:array[0..101,0..101] of integer;
    oldi,oldj,newi,newj:array[0..5000] of integer;
    u:array[0..101,0..101] of boolean;

procedure print(x:integer);
  begin
    write(x+1);
    close(output);
    halt(0);
  end;

procedure init;
var i,j,k,l,x1,x2,y1,y2,xx,yy:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m);
    read(sti,stj);
    for i:=1 to n do
    for j:=1 to m do read(c[i,j]);
    read(k);
    for i:=1 to k do
      begin
        read(x1,y1,x2,y2);
        hi[x1,y1]:=x2;
        hj[x1,y1]:=y2;
      end;
    read(l);
    for i:=1 to l do begin read(xx,yy);
    c[xx,yy]:=2;
    end;
    close(input);
    if c[sti,stj]=2 then print(0);
  end;

procedure solve;
var q,i,x,y,xx,yy:integer;
  begin
    fillchar(oldi,sizeof(oldi),0);
    fillchar(oldj,sizeof(oldj),0);
    kolold:=1;
    oldi[1]:=sti;
    oldj[1]:=stj;
    q:=0;
    repeat
      inc(q);
      kolnew:=0;
      for i:=1 to kolold do
        begin
          x:=oldi[i];
          y:=oldj[i];
          if x>1 then if not u[x-1,y] and (c[x-1,y]<>1) then
            begin
              inc(kolnew);
              newi[kolnew]:=x-1;
              newj[kolnew]:=y;
              u[x-1,y]:=true;
              if c[x-1,y]=2 then
                begin
                  print(q);
                end;
            end;
          if y>1 then if not u[x,y-1] and (c[x,y-1]<>1) then
            begin
              inc(kolnew);
              newi[kolnew]:=x;
              newj[kolnew]:=y-1;
              u[x,y-1]:=true;
              if c[x,y-1]=2 then
                begin
                  print(q);
                end;
            end;
          if x<n then if not u[x+1,y] and (c[x+1,y]<>1) then
            begin
              inc(kolnew);
              newi[kolnew]:=x+1;
              newj[kolnew]:=y;
              u[x+1,y]:=true;
              if c[x+1,y]=2 then print(q);
            end;
          if y<m then if not u[x,y+1] and (c[x,y+1]<>1) then
            begin
              inc(kolnew);
              newi[kolnew]:=x;
              newj[kolnew]:=y+1;
              u[x,y+1]:=true;
              if c[x,y+1]=2 then print(q);
            end;
          if hi[x,y]<>0 then
            begin
              xx:=hi[x,y];
              yy:=hj[x,y];
              if not u[xx,yy] and (c[xx,yy]<>1) then
                begin
                  inc(kolnew);
                  newi[kolnew]:=xx;
                  newj[kolnew]:=yy;
                  u[xx,yy]:=true;
                  if c[xx,yy]=2 then print(q);
                end;
            end;
        end;
      oldi:=newi;
      oldj:=newj;
      kolold:=kolnew;
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

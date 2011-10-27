program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type node=record
      x,y,p,k,d:integer;
      end;

var w:array[0..100000] of node;
    n,m:integer;
    a:array[0..302,0..302] of integer;
    ansx,ansy:array[0..100000] of integer;
    x1,x2,y1,y2:integer;
    u:array[0..302,0..302] of boolean;

procedure init;
var i,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m);
    for i:=1 to n do
    for j:=1 to m do read(a[i][j]);
    read(x1,y1,x2,y2);
    close(input);
  end;

procedure print(x:integer);
var i,j:integer;
  begin
    writeln(w[x].d,' ',w[x].k);
    i:=0;
    while (x<>0) do
      begin
        inc(i);
        ansx[i]:=w[x].x;
        ansy[i]:=w[x].y;
        x:=w[x].p;
      end;
    for j:=i downto 1 do
      begin
        writeln(ansx[j],' ',ansy[j]);
      end;
    close(output);
    halt(0);
  end;

procedure solve;
var uk1,i,uk2,d,k,xx,yy:integer;
  begin
    uk1:=1;
    uk2:=2;
    fillchar(u,sizeof(u),false);
    w[1].x:=x1;
    w[1].y:=y1;
    w[1].d:=0;
    w[1].k:=0;
    w[uk1].p:=0;
    while (uk2>uk1) do
      begin
        xx:=w[uk1].x;
        yy:=w[uk1].y;
        d:=w[uk1].d;
        k:=w[uk1].k;
        i:=xx;
        while (i<n) and (a[i+1][yy]=0) do inc(i);
        if (not u[i][yy]) then
          begin
            w[uk2].p:=uk1;
            w[uk2].x:=i;
            w[uk2].y:=yy;
            w[uk2].d:=d+i-xx;
            w[uk2].k:=k+1;
            u[i][yy]:=true;
            if (w[uk2].x=x2) and (w[uk2].y=y2) then
              begin
                print(uk2);
              end;
            inc(uk2);
          end;
        i:=xx;
        while (i>1) and (a[i-1][yy]=0) do dec(i);
        if (not u[i][yy]) then
          begin
            w[uk2].p:=uk1;
            w[uk2].x:=i;
            w[uk2].y:=yy;
            w[uk2].d:=d+xx-i;
            w[uk2].k:=k+1;
            u[i][yy]:=true;
            if (w[uk2].x=x2) and (w[uk2].y=y2) then
              begin
                print(uk2);
              end;
            inc(uk2);
          end;
        i:=yy;
        while (i<m) and (a[xx][i+1]=0) do inc(i);
        if (not u[xx][i]) then
          begin
            w[uk2].p:=uk1;
            w[uk2].x:=xx;
            w[uk2].y:=i;
            w[uk2].d:=d+i-yy;
            w[uk2].k:=k+1;
            u[xx][i]:=true;
            if (w[uk2].x=x2) and (w[uk2].y=y2) then
              begin
                print(uk2);
              end;
            inc(uk2);
          end;
        i:=yy;
        while (i>1) and (a[xx][i-1]=0) do dec(i);
        if (not u[xx][i]) then
          begin
            w[uk2].p:=uk1;
            w[uk2].x:=xx;
            w[uk2].y:=i;
            w[uk2].d:=d+yy-i;
            w[uk2].k:=k+1;
            u[xx][i]:=true;
            if (w[uk2].x=x2) and (w[uk2].y=y2) then
              begin
                print(uk2);
              end;
            inc(uk2);
          end;
        inc(uk1);
      end;
  end;

procedure done;
  begin
    writeln('No Solution');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

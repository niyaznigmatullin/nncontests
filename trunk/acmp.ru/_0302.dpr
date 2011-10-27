{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program _0142;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TEdge=record
      v,u:smallint;
      w:single;
      end;

var e:array[0..999001] of TEdge;
    p:array[0..1001] of smallint;
    nn,kol,n,hh:integer;
    xx,yy:array[0..1001] of single;
    miny:extended;

function minh(x,y:integer):integer;
  begin
    if e[x].w>e[y].w then result:=y else result:=x;
  end;

procedure swap(x,y:longint);
var r:TEdge;
  begin
    r:=e[x];
    e[x]:=e[y];
    e[y]:=r;
  end;

function find_set(const x:integer; var k:integer):integer;
var i:integer;
  begin
    i:=x;
    k:=0;
    while i<>p[i] do
      begin
        i:=p[i];
        inc(k);
      end;
    result:=i;
  end;

procedure heapify_up(x:integer);
var i:integer;
  begin
    i:=x;
    while (i>1) and (e[i].w<e[i div 2].w) do
      begin
        swap(i,i div 2);
        i:=i div 2;
      end;
  end;

procedure heapify_down(x:integer);
var i,t:integer;
  begin
    i:=x;
    while (i+i+1<=hh) and ((e[i].w>e[i+i].w) or (e[i].w>e[i+i+1].w)) do
      begin
        t:=minh(i+i,i+i+1);
        swap(i,t);
        i:=t;
      end;
    if (i+i<=hh) and (e[i].w>e[i+i].w) then swap(i+i,i);
  end;

procedure add_heap(x:extended; s,t:integer);
  begin
    inc(hh);
    e[hh].w:=x;
    e[hh].v:=s;
    e[hh].u:=t;
    heapify_up(hh);
  end;

procedure delete_heap(x:integer);
  begin
    swap(x,hh);
    dec(hh);
    heapify_down(x);
  end;

procedure union(x,y:integer);
var h1,h2:integer;
  begin
    h1:=0;
    h2:=0;
    x:=find_set(x,h1);
    y:=find_set(y,h2);
    if h1<h2 then
      begin
        p[x]:=y;
      end else p[y]:=x;
  end;

procedure init;
var i,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        read(xx[i],yy[i]);
      end;
    hh:=0;
    for i:=1 to n do
    for j:=1 to n do if i<>j then
      begin
        add_heap(sqrt(sqr(xx[i]-xx[j])+sqr(yy[i]-yy[j])),i,j);
      end;
    nn:=hh;
    close(input);
  end;

procedure solve;
var i,k:integer;
  begin
    kol:=0;
    for i:=1 to n do p[i]:=i;
    while (kol<n-1) and (hh>0) do
      begin
        if find_set(e[1].v,k)=find_set(e[1].u,k) then
          begin
            delete_heap(1);
          end else
          begin
            inc(kol);
            //kr[kol]:=e[1];
            union(e[1].v,e[1].u);
            miny:=e[1].w;
            delete_heap(1);
          end;
      end;
  end;

procedure done;
  begin
    write(miny:0:2);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

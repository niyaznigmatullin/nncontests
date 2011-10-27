{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program _0304;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TLong=array[0..1000] of longint;

var c:char;
    qq,q:TLong;
    a,b:array[0..6] of longint;
    n:longint;

procedure mult_2(var a:TLong; const v:longint);
var i,j,um,e:longint;
  begin
    for i:=1 to v do
      begin
        um:=0;
        for j:=1 to a[0] do
          begin
            e:=a[j]+a[j]+um;
            a[j]:=e mod 10;
            um:=e div 10;
          end;
        while um>0 do
          begin
            inc(a[0]);
            a[a[0]]:=um mod 10;
            um:=um div 10;
          end;
      end;
  end;

procedure mult(var a:TLong; const b:TLong);
var c:TLong; j,i,e:longint;
  begin
    c:=a;
    fillchar(a,sizeof(a),0);
    for i:=1 to c[0] do
      begin
        for j:=1 to b[0] do
          begin
            e:=a[i+j-1]+c[i]*b[j];
            a[i+j-1]:=e mod 10;
            a[i+j]:=a[i+j]+(e div 10);
          end;
      end;
    a[0]:=b[0]+c[0];
    while a[a[0]]=0 do dec(a[0]);
    while a[a[0]]>9 do
      begin
        inc(a[0]);
        a[a[0]]:=a[a[0]-1] div 10;
        a[a[0]-1]:=a[a[0]-1] mod 10;
      end;
  end;

procedure find_comb(var a:TLong; const c,d:longint);
var i,um,j,e:longint;
  begin
    fillchar(a,sizeof(a),0);
    a[0]:=1;
    a[1]:=1;
    for i:=2 to c do
      begin
        um:=0;
        for j:=1 to a[0] do
          begin
            e:=a[j]*i+um;
            a[j]:=e mod 10;
            um:=e div 10;
          end;
        while um>0 do
          begin
            inc(a[0]);
            a[a[0]]:=um mod 10;
            um:=um div 10;
          end;
      end;
    for i:=2 to d do
      begin
        um:=0;
        for j:=a[0] downto 1 do
          begin
            e:=a[j]+um*10;
            a[j]:=e div i;
            um:=e mod i;
          end;
        while (a[a[0]]=0) and (a[0]>1) do
          begin
            dec(a[0]);
          end;
      end;
    for i:=2 to c-d do
      begin
        um:=0;
        for j:=a[0] downto 1 do
          begin
            e:=a[j]+um*10;
            a[j]:=e div i;
            um:=e mod i;
          end;
        while (a[a[0]]=0) and (a[0]>1) do
          begin
            dec(a[0]);
          end;
      end;
  end;

procedure init;
var i:longint;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        while c<>':' do
          begin
            a[i]:=a[i]*10;
            read(c);
            if c in ['0'..'9'] then inc(a[i],ord(c)-ord('0'));
          end;
        a[i]:=a[i] div 10;
        while (c<>' ') and (c in ['0'..'9',':']) do
          begin
            b[i]:=b[i]*10;
            read(c);
            if c in ['0'..'9'] then inc(b[i],ord(c)-ord('0'));
          end;
        b[i]:=b[i] div 10;
      end;
    close(input);
  end;

procedure solve;
var p,w,i:longint; h:TLong;
  begin
    {qq[0]:=2;
    qq[1]:=1;
    qq[2]:=1;
    h[0]:=2;
    h[1]:=5;
    h[2]:=5;
    mult(qq,h);}
    for i:=1 to n do
    if a[i]<b[i] then
      begin
        p:=a[i];
        a[i]:=b[i];
        b[i]:=p;
      end;
    fillchar(qq,sizeof(qq),0);
    qq[0]:=1;
    qq[1]:=1;
    for i:=1 to n do
      begin
        if i=5 then
          begin
            if a[i]=15 then
              begin
                find_comb(q,a[i]+b[i]-1,b[i]);
              end else
              begin
                find_comb(q,28,14);
                w:=(b[i]*2-28) div 2;
                mult_2(q,w);
              end;
            mult(qq,q);
          end else
          begin
            if a[i]=25 then
              begin
                find_comb(q,a[i]+b[i]-1,b[i]);
              end else
              begin
                find_comb(q,48,24);
                w:=(b[i]*2-48) div 2;
                mult_2(q,w);
              end;
            mult(qq,q);
          end;
      end;
  end;

procedure done;
var i:longint;
  begin
    for i:=qq[0] downto 1 do write(qq[i]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end. 

{Problem: casino, ROI WTC 2004
Compiler: Free Pascal 1.0.10
(C) Semyon Dyatlov, 2004}
{$R+,Q+,S+}
const
  MAXN = 50;
  MAXL = 201;
  BASE = 1000;

type
  Number = record
    length : integer;
    data : array [1..MAXL] of integer;
  end;

function max(a, b : longint) : longint;
begin
  if a>b then
    max := a
  else
    max := b;
end;

procedure ShortToNumber(var x : Number; a : integer);
begin
  if a=0 then
    x.length := 0
  else
  begin
    x.length := 1;
    x.data[1] := a;
  end;
end;

procedure IncNumber(var x : Number; a : Number);
var
  len, temp, i : longint;
begin
  len := max(x.length,a.length);
  temp := 0;
  for i:=1 to len do
  begin
    if (i<=x.length) then
      inc(temp,x.data[i]);
    if (i<=a.length) then
      inc(temp,a.data[i]);
    x.data[i] := temp mod BASE;
    temp := temp div BASE;
  end;
  x.length := len;
  while (temp>0) do
  begin
    inc(x.length);
    x.data[x.length] := temp mod BASE;
    temp := temp div BASE;
  end;
end;

procedure ShortMulNumber(var x : Number; a : Integer);
var
  i, temp : longint;
begin
  temp := 0;
  for i:=1 to x.length do
  begin
    Inc(temp,x.data[i]*a);
    x.data[i] := temp mod BASE;
    temp := temp div BASE;
  end;
  while (temp>0) do
  begin
    inc(x.length);
    x.data[x.length] := temp mod BASE;
    temp := temp div BASE;
  end;
end;

procedure ShortDivNumber(var x : Number; a : Integer);
var
  i, temp : longint;
begin
  temp := 0;
  for i:=x.length downto 1 do
  begin
    temp := temp*BASE+x.data[i];
    x.data[i] := temp div a;
    temp := temp mod a;
  end;
  while ((x.length>0) and (x.data[x.length]=0)) do
    Dec(x.length);
end;

procedure MulNumber(var x : Number; a, b : Number);
var
  temp, i, j : longint;
begin
  temp := 0;
  x.length := a.length+b.length;
  for i:=0 to x.length-1 do
  begin
    for j:=0 to i do
      if ((j<a.length) and (i-j<b.length)) then
        Inc(temp,a.data[j+1]*b.data[i-j+1]);
    x.data[i+1] := temp mod BASE;
    temp := temp div BASE;
  end;
  while ((x.length>0) and (x.data[x.length]=0)) do
    dec(x.length);
end;

procedure Mul_factorial(var x : Number; a : Integer);
var
  i : integer;
begin
  for i:=1 to a do
    ShortMulNumber(x,i);
end;

procedure Div_factorial(var x : Number; a : Integer);
var
  i : integer;
begin
  for i:=1 to a do
    ShortDivNumber(x,i);
end;

var
  n, i, j, k, len, c, p : longint;
  ar : array [1..MAXN] of integer;
  present : array [1..MAXN] of integer;
  answer, temp : Number;
  f : array [0..MAXN-1] of Number;

begin
  assign(input,'input.txt');
  reset(input);
  assign(output,'output.txt');
  rewrite(output);

  read(n);
  for i:=1 to n do
    read(present[i]);

  ShortToNumber(f[0],1);
  for i:=1 to n-1 do
  begin
    ShortToNumber(f[i],0);
    for j:=0 to i-1 do
    begin
      MulNumber(temp,f[j],f[i-j-1]);
      mul_factorial(temp,i-1);
      div_factorial(temp,j);
      div_factorial(temp,i-j-1);
      ShortMulNumber(temp,j+1);
      IncNumber(f[i],temp);
    end;
  end;

  ShortToNumber(answer,1);
  k := 0;
  for i:=1 to n do
    Inc(k,1-present[i]);
  if (k<n) then
  begin
    c := 1;
    while (present[c]=0) do
      Inc(c);
    len := 1;
    ar[1] := c-1;
    p := 0;
    for i:=c to n do
      if (present[i]=1) then
      begin
        if (p>0) then
        begin
          inc(len);
          ar[len] := p;
        end;
        p := 0;
      end
      else
        Inc(p);
    Inc(ar[1],p);
    mul_factorial(answer,k);
    for i:=1 to len do
      div_factorial(answer,ar[i]);
    for i:=1 to len do
      MulNumber(answer,answer,f[ar[i]]);
  end
  else
    for i:=1 to n do
      ShortMulNumber(answer,n);

  write(answer.data[answer.length]);
  for i:=answer.length-1 downto 1 do
    write(answer.data[i] div 100, answer.data[i] mod 100 div 10,
      answer.data[i] mod 10);
  writeln;

  close(input);
  close(output);
end.


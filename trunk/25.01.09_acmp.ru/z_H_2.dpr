program z_H_2;
{$APPTYPE CONSOLE}
{$O-,R+,S+,Q+}
uses
  SysUtils,
  Math;

const
  maxn = 1000;
  inf = 1e20;

type
  int=longint;
  real=extended;
  list=record
    n:int;
    name:array[1..maxn] of string;
    cnt:array[1..maxn] of real;
    id:array[1..maxn] of int;
  end;
  base=record
    n:int;
    name:array[1..maxn] of string;
    price:array[1..maxn] of real;
  end;

procedure parse(s : string; var name : string; var value : real);
var
  sp : int;
  i : int;
begin
  s := trim(s);
  sp := pos(' ', s);
  for i := 1 to length(s) do
  begin
    if s[i] = '.' then s[i] := ',';
  end;
  name := lowercase(copy(s, 1, sp - 1));
  value := strtofloat(copy(s, sp + 1, length(s) - sp));
end;

function getPrice(name : string; const b : base) : real;
var
  i : int;
begin
  for i := 1 to b.n do
  begin
    if b.name[i] = name then
    begin
      result := b.price[i];
      exit;
    end;
  end;
  result := inf;
end;

procedure swapi(var a, b : int);
var
  t : int;
begin
  t := a;
  a := b;
  b := t;
end;

procedure swapr(var a, b : real);
var
  t : real;
begin
  t := a;
  a := b;
  b := t;
end;

var
  n, k1, k2 : int;
  d : real;
  plan : list;
  old, newb : base;
  po, pn : real;
  ans : array[1..maxn] of real;
  w, p : array[1..maxn] of real;
  id : array[1..maxn] of int;
  cnt : int;
  i, j : int;
  str : string;

begin
  reset(input, 'input.txt');
  rewrite(output, 'output.txt');
  readln(n, d, k1, k2);
  plan.n := n;
  for i := 1 to n do
  begin
    readln(str);
    parse(str, plan.name[i], plan.cnt[i]);
  end;

  readln;

  old.n := k1;
  for i := 1 to k1 do
  begin
    readln(str);
    parse(str, old.name[i], old.price[i]);
  end;

  readln;

  newb.n := k2;
  for i := 1 to k2 do
  begin
    readln(str);
    parse(str, newb.name[i], newb.price[i]);
  end;
  close(input);
  cnt := 0;
  for i := 1 to n do
  begin
    pn := getPrice(plan.name[i], newb);
    po := getPrice(plan.name[i], old);
    if abs(pn - inf) < 1 then
    begin
      ans[i] := 0;
      continue;
    end;
    inc(cnt);
    w[cnt] := plan.cnt[i] * po;
    p[cnt] := pn;
    id[cnt] := i;
  end;

  for i := 1 to cnt do
  begin
    for j := i + 1 to cnt do
    begin
      if p[i] / w[i] < p[j] / w[j] then
      begin
        swapr(p[i], p[j]);
        swapr(w[i], w[j]);
        swapi(id[i], id[j]);
      end;
    end;
  end;

  for i := 1 to cnt do
  begin
    ans[id[i]] := min(d / getPrice(plan.name[id[i]], old), plan.cnt[id[i]]);
    d := d - min(d, plan.cnt[id[i]] * getPrice(plan.name[id[i]], old));
  end;

  for i := 1 to n do
  begin
    writeln(ans[i] : 0 : 9);
  end;
  close(output);
  halt(0);
end.

program _0205;

{$APPTYPE CONSOLE}
  {$o-}
uses
  SysUtils;
var
vs,s:string;
z,q,w,e,n,min,sec,hou,day:int64;
i:longint;
label lb1;
begin
 reset(input,'input.txt');
 rewrite(output,'output.txt');
 readln(s);
 q:=(strtoint(s[1]+s[2]));w:=(strtoint(s[4]+s[5]));e:=strtoint(s[7]+s[8]);
 readln(s);
 if s=''then goto lb1;
 for i:=1 to length(s) do
   if s[i]=':'  then inc(n);
 if n=0 then begin sec:=strtoint(s); goto lb1; end;
 if n=1 then begin
   z:=1;
   while s[z]<>':' do begin
    vs:=vs+s[z];
    inc(z);
   end;
   inc(z);
   min:=strtoint(vs);
   vs:='';
   while z<=length(s) do begin
    vs:=vs+s[z];
    inc(z);
   end;
   sec:=strtoint(vs);
   goto lb1;
 end;
 if n=2 then begin
   z:=1;
   while s[z]<>':' do begin
    vs:=vs+s[z];
    inc(z);
   end;
   inc(z);
   hou:=strtoint(vs);
   vs:='';
   while s[z]<>':' do begin
    vs:=vs+s[z];
    inc(z);
   end;
   inc(z);
   min:=strtoint(vs);
   vs:='';
   while z<=length(s) do begin
    vs:=vs+s[z];
    inc(z);
   end;
   sec:=strtoint(vs);
   goto lb1;
 end;
 lb1:
 min:=min+(e+sec) div 60;
 sec:=(e+sec) mod 60;
 hou:=hou+(w+min) div 60;
 min:=(w+min) mod 60;
 day:=(q+hou) div 24;
 hou:=(hou+q) mod 24;
 if hou<10 then write(0); write(hou); write(':');
  if min<10 then write(0); write(min); write(':');
   if sec<10 then write(0); write(sec);

   if day>0 then write('+',day,' days'); 
end.

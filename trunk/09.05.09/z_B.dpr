{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s1,s2:string;

begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  readln(s1);
  readln(s2);
  s1:=trim(s1);
  s2:=trim(s2);
//  insert('19',s1,7);
//  insert('19',s2,7);
  if (s1 = '') or (s2 = '') then halt(0);
  write((StrToDate(s2) - StrToDate(s1)) : 0 : 0);
  close(input);
  close(output);
  halt(0);
end.
 
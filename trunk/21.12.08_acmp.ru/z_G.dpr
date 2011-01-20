var x1,x2,y1,y2:extended;
begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  read(x1,y1,x2,y2);
  write(sqrt(sqr(x1-x2)+sqr(y1-y2)):0:10);
  close(input);
  close(output);
end.

type TPoint=record
			x,y:int64;
		 end;
		 TRect=record
		 	r1,r2,r3,r4:TPoint;
		 end;

var p:array[0..101] of TRect;
	
procedure init;
	begin
	  reset(input,'input.txt');
	  read(n);
	  for i:=1 to n do 
	  	begin
	  		read(x1,y1,x2,y2);
	  		if x1>x2 then begin p:=x1; x1:=x2; x2:=p; end;
	  		if y1>y2 then begin p:=y1; y1:=y2; y2:=p; end;
	  		p[i].r1.x:=x1;
	  		p[i].r1.y:=y1;
	  		p[i].r2.x:=x1;
	  		p[i].r2.y:=y2;
	  		p[i].r3.x:=x2;
	  		p[i].r3.y:=y2;
	  		p[i].r4.x:=x2;
	  		p[i].r4.y:=y1;
	  	end;
	  close(input);
	end;

procedure solve;
	begin
		for i:=1 to n-1 do
		for j:=i+1 to n do
			begin
			  t:=inter_rect(p[i],p[j]);
			  u:=0;
			  for k:=1 to n do
			  	begin
			  	  if in_rect(t,p[k]) then inc(u);
			  	end;
			  inc(q,
			end;

	end;


procedure done;
	begin
	  rewrite(output,'output.txt');
	  close(output);
	  halt(0);
	end;
		
begin

end.
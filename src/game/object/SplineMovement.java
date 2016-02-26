package game.object;

import java.util.Iterator;
import java.util.LinkedList;

import util.Time;

public class SplineMovement {
	float x1,x2,x3,x4,y1,y2,y3,y4;
	long start,end;
	GameObject go;
	
	static LinkedList<SplineMovement> movements=new LinkedList<SplineMovement>();
	
	static float mix(float x, float y, float a){
		return (1-a)*x+a*y;
	}
	
	public static void update(){
		Iterator<SplineMovement> it=movements.iterator();
		while (it.hasNext()) {
			SplineMovement s = it.next();
			if (Time.getTime()>s.end) {
				it.remove();
			} else {
				s.updateMovement();
			}
		}
	}
	
	public static void add(GameObject go, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, long duration){
		SplineMovement s=new SplineMovement();
		s.go=go;
		s.x1=x1;
		s.x2=x2;
		s.x3=x3;
		s.x4=x4;
		s.y1=y1;
		s.y2=y2;
		s.y3=y3;
		s.y4=y4;
		s.start=Time.getTime();
		s.end=s.start+duration;
		movements.add(s);
	}
	
	private void updateMovement(){
		float f=(float)(Time.getTime()-start)/(end-start);
		float x=mix(x2,x3,f);
		go.x=mix(mix(mix(x1,x2,f),x,f),mix(x,mix(x3,x4,f),f),f);
		float y=mix(y2,y3,f);
		go.y=mix(mix(mix(y1,y2,f),y,f),mix(y,mix(y3,y4,f),f),f);
	//	System.out.println("setting");
	//	go.x=go.y=0;
	}
}

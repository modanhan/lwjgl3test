package game;

public abstract class GameEntity extends GameObject {
	protected float px,py;
	protected float size=10;
	public void kill(){
		Game.remove(this);
	}
	public static boolean checkCollision(float ax,float ay,float bx,float by,float ar,float br){
		if(Math.hypot(bx-ax, by-ay)<ar+br){
			return true;
		}
		return false;
	}
	public static boolean checkCollision(GameEntity a,GameEntity b){
		if(Math.hypot(b.px-a.px, b.py-a.py)<=a.size+b.size){
			return true;
		}
		return false;
	}
}

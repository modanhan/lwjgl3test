package util;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.*;

public class Mouse{
	private static class MousePos extends GLFWCursorPosCallback{
		static double x=0,y=0;
		@Override
		public void invoke(long window, double xpos, double ypos) {
			x=xpos;
			y=ypos;
		}
	}
	private static class MouseClick extends GLFWMouseButtonCallback{
		static boolean lmbdown = false;
		static boolean rmbdown = false;
		static boolean lmbpdown = false;
		static boolean rmbpdown = false;
		@Override
		public void invoke(long window, int button, int action, int mods) {
			if(button==0){
				lmbdown = action==1;
			}else{
				rmbdown = action==1;
			}
		}
		public void update(){
			lmbpdown = lmbdown;
			rmbpdown = rmbdown;
		}
	}
	private static MouseClick mc;
	private static MousePos mp;
	public static void init(){
		mc = new MouseClick();
		mp = new MousePos();
		glfwSetMouseButtonCallback(Global.window,mc);
		glfwSetCursorPosCallback(Global.window,mp);
	}
	public static void update(){
		mc.update();
	}
	public static double getX(){
		return MousePos.x;
	}
	public static double getY(){
		return MousePos.y;
	}
	public static boolean isButtonDown(int button) {
		return button==0?MouseClick.lmbdown:MouseClick.rmbdown;
	}

	public static boolean isButtonPressed(int button) {
		return button==0?(MouseClick.lmbdown&&!MouseClick.lmbpdown):(MouseClick.rmbdown&&!MouseClick.rmbpdown);
	}

	public static boolean isButtonReleased(int button) {
		return button==0?(!MouseClick.lmbdown&&MouseClick.lmbpdown):(!MouseClick.rmbdown&&MouseClick.rmbpdown);
	}
}

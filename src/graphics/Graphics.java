package graphics;

import static org.lwjgl.opengl.GL11.*;

import util.Global;

public class Graphics {
	private static int initialframebuffer=0, initialtexture=0, initialshader=0;
	public static void clearBuffers(){
		glClearColor(0, 0, 0, 0);
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	}
	public static void blendAdditive(){
		glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
	}
	public static void blendOverlay(){
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	public static void renderPass(Shader s,FrameBuffer in, FrameBuffer out){
		FrameBuffer.bind(out);
		Graphics.clearBuffers();
		Shader.use(s);
		FrameBuffer.render(in);
	}
	public static void renderPass(Shader s,FrameBuffer in){
		FrameBuffer.bind(0);
		Shader.use(s);
		FrameBuffer.render(in);
	}
	public static void set(){
		initialframebuffer = FrameBuffer.getCurrentID();
		initialtexture = Texture.getCurrentID();
		initialshader = Shader.getCurrentID();
	}
	public static void reset(){
		FrameBuffer.bind(initialframebuffer);
		Texture.bind(initialtexture);
		Shader.use(initialshader);
	}
	public static void quad(float width, float height){
		glBegin(GL_TRIANGLE_FAN);
		glTexCoord2f(1,1); glVertex2f( - width,  - height);
		glTexCoord2f(1,0); glVertex2f( - width,  + height);
		glTexCoord2f(0,0); glVertex2f( + width,  + height);
		glTexCoord2f(0,1); glVertex2f( + width,  - height);
		glEnd();
	}
	public static void quad(float size){
		glBegin(GL_TRIANGLE_FAN);
		glTexCoord2f(1,1); glVertex2f( - size,  - size);
		glTexCoord2f(1,0); glVertex2f( - size,  + size);
		glTexCoord2f(0,0); glVertex2f( + size,  + size);
		glTexCoord2f(0,1); glVertex2f( + size,  - size);
		glEnd();
	}
	public static void screenQuad(){
		glBegin(GL_TRIANGLE_FAN);
		glTexCoord2f(1,1); glVertex2f(0, 0);
		glTexCoord2f(1,0); glVertex2f(0, Global.height);
		glTexCoord2f(0,0); glVertex2f(Global.width, Global.height);
		glTexCoord2f(0,1); glVertex2f(Global.width, 0);
		glEnd();
	}
}

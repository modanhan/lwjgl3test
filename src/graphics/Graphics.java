package graphics;

import static org.lwjgl.opengl.GL11.*;

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
}

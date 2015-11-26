package game;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import graphics.FrameBuffer;
import graphics.Graphics;
import graphics.Shader;

public class Game {
	private static Set<GameObject> s, toremove;
	static FrameBuffer hbuf, vbuf;
	static Shader hblur, vblur;
	public static void init() {
		s = new TreeSet<GameObject>();
		toremove = new TreeSet<GameObject>();
		hbuf = new FrameBuffer(GlobalVars.WIDTH, GlobalVars.HEIGHT);
		vbuf = new FrameBuffer(GlobalVars.WIDTH, GlobalVars.HEIGHT);
		hblur = new Shader(new File("shaders/mainvertex.glsl"),new File("shaders/blurhfragment.glsl"));
		int loc;
		Shader.use(hblur);
		loc = glGetUniformLocation(hblur.getID(), "texture");
		glUniform1i(loc, 0);
		vblur = new Shader(new File("shaders/mainvertex.glsl"),new File("shaders/blurvfragment.glsl"));
		Shader.use(vblur);
		loc = glGetUniformLocation(vblur.getID(), "texture");
		glUniform1i(loc, 0);
		Shader.use(0);
		glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
		Graphics.reset();
	}

	public static void update() {
		Graphics.blendOverlay();	
		Graphics.set();
		for (GameObject g : toremove) {
			s.remove(g);
		}
		toremove.clear();
		for (GameObject g : s) {
			g.update();
		}
		for (GameObject g : s) {
			g.render();
		}
		FrameBuffer.bind(hbuf);
		Graphics.clearBuffers();
		for (GameObject g : s) {
			g.render();
		}
		Graphics.blendAdditive();
		Graphics.renderPass(hblur, hbuf, vbuf);	
		Graphics.renderPass(vblur, vbuf);
		Graphics.reset();
		
	}

	public static void add(GameObject e) {
		s.add(e);
	}

	public static void remove(GameObject e) {
		toremove.add(e);
	}
}

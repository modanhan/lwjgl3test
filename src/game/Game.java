package game;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import org.lwjgl.opengl.GL13;

import graphics.FrameBuffer;
import graphics.Graphics;
import graphics.Shader;
import graphics.Texture;

public class Game {
	private static Set<GameObject> s, toremove;
	static FrameBuffer hbuf, vbuf;
	static Shader hblur, vblur;
	static Texture main;
	public static void init() {
		s = new TreeSet<GameObject>();
		toremove = new TreeSet<GameObject>();
		hbuf = new FrameBuffer(GlobalVars.WIDTH, GlobalVars.HEIGHT);
		vbuf = new FrameBuffer(GlobalVars.WIDTH, GlobalVars.HEIGHT);
		hblur = new Shader(new File("shaders/mainvertex.glsl"),new File("shaders/blurhfragment.glsl"));
		Shader.use(hblur);
		hblur.setUniformi("texture", 0);
		vblur = new Shader(new File("shaders/mainvertex.glsl"),new File("shaders/blurvfragment.glsl"));
		Shader.use(vblur);
		vblur.setUniformi("texture", 0);
		Shader.use(0);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
		if(main==null)main = new Texture(new File("res/circle.png"));
		Graphics.reset();
	}

	public static void update() {
		Graphics.blendOverlay();	
		Graphics.set();
		
		Texture.bind(main);
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
		Texture.bind(0);
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

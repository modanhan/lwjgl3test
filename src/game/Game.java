package game;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

import org.lwjgl.opengl.GL13;

import graphics.FrameBuffer;
import graphics.Graphics;
import graphics.Shader;
import graphics.Texture;

public class Game {
	private static LinkedList<GameObject> s;
	static FrameBuffer hbuf, vbuf;
	static Shader hblur, vblur;
	static Texture main;
	public static void init() {
		s=new LinkedList<GameObject>();
		if(hbuf==null)hbuf = new FrameBuffer(GlobalVars.WIDTH, GlobalVars.HEIGHT);
		if(vbuf==null)vbuf = new FrameBuffer(GlobalVars.WIDTH, GlobalVars.HEIGHT);
		if(hblur==null)hblur = new Shader(new File("shaders/mainvertex.glsl"),new File("shaders/blurhfragment.glsl"));
		Shader.use(hblur);
		hblur.setUniformi("texture", 0);
		if(vblur==null)vblur = new Shader(new File("shaders/mainvertex.glsl"),new File("shaders/blurvfragment.glsl"));
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
		
		Iterator<GameObject> it=s.iterator();
		System.out.println(s.size());
		while(it.hasNext()){
			GameObject go=it.next();
			if(go.remove){
				it.remove();
			}else{
				go.update();
			}
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
		e.remove();
	}
	public static void removeAll(Collection<? extends GameObject> e) {
		e.forEach(new Consumer<GameObject>() {
			@Override
			public void accept(GameObject t) {
				t.remove();
			}
		});
	}
}

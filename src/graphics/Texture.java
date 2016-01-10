package graphics;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL14.*;

public class Texture{
	private static final int BYTES_PER_PIXEL = 4;// 3 for RGB 4 for RGBA

	private int textureID;
	public Texture(File f){
		textureID = loadTexture(f).getID();
	}
	public Texture(String s, String font){
		textureID = loadTextTexture(s,font).getID();
	}
	Texture(int ID) {
		textureID = ID;
	}
	public int getID(){
		return textureID;
	}
	public void dispose(){
		glDeleteTextures(textureID);
	}
	public static int getCurrentID() {
		return glGetInteger(GL_TEXTURE_BINDING_2D);
	}
	public static Texture loadTexture(File f){

		BufferedImage image = null;
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Texture(generateTexture(image));

	}
	public static int generateTexture(BufferedImage image) {		
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0,
				image.getWidth());
		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth()
				* image.getHeight() * BYTES_PER_PIXEL); // 4 for RGBA, 3 for RGB
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) (pixel >> 16 & 0xFF)); // Red component
				buffer.put((byte) (pixel >> 8 & 0xFF)); // Green component
				buffer.put((byte) (pixel & 0xFF)); // Blue component
				buffer.put((byte) (pixel >> 24 & 0xFF)); // Alpha component.
				// Only for RGBA
			}
		}

		buffer.flip();

		int textureID = glGenTextures(); // Generate texture ID
		glBindTexture(GL_TEXTURE_2D, textureID); // Bind texture ID
		
		// Setup wrap mode
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T,GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,GL_LINEAR_MIPMAP_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER,GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_GENERATE_MIPMAP, GL_TRUE); 
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA,
				image.getWidth(), image.getHeight(), 0, GL_RGBA,
				GL_UNSIGNED_BYTE, buffer);
		glBindTexture(GL_TEXTURE_2D, 0);
		return textureID;
	}
	public static Texture loadTextTexture(String text, String font) {
		BufferedImage textimg = new BufferedImage(1, 1,
				BufferedImage.TYPE_4BYTE_ABGR);

		Graphics g = textimg.getGraphics();
		Font f;
		final int size = 256;
		f = new Font(font, Font.PLAIN, size);

		g.setFont(f);
		FontRenderContext frc = g.getFontMetrics().getFontRenderContext();
		Rectangle2D rect = f.getStringBounds(text, frc);
		g.dispose();
		BufferedImage img = new BufferedImage((int) Math.ceil(rect.getWidth()),
				(int) Math.ceil(rect.getHeight()),
				BufferedImage.TYPE_4BYTE_ABGR);
		g = img.getGraphics();
		((java.awt.Graphics2D) g).setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g.setColor(new Color(255, 255, 255, 0));
		g.fillRect(0, 0, (int) Math.ceil(rect.getWidth()),
				(int) Math.ceil(rect.getHeight()));
		g.setColor(new Color(255, 255, 255, 255));
		g.setFont(f);
		g.drawString(text, 0, (int) -rect.getY());
		g.dispose();
		BufferedImage image = img;
		return new Texture(generateTexture(image));
	}
	public static void bind(Texture t){
		glBindTexture(GL_TEXTURE_2D, t!=null?t.getID():0);
	}
	public static void bind(int id){
		glBindTexture(GL_TEXTURE_2D, id);
	}
}



import static org.lwjgl.opengl.GL11.GL_TEXTURE_BINDING_2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL30;

public class Texture{
	private static final int BYTES_PER_PIXEL = 4;// 3 for RGB 4 for RGBA

	private int ID;

	private Texture(int ID) {
		this.ID = ID;

	}


	public void setID(int ID) {
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public static int getBoundTextureID() {

		return GL11.glGetInteger(GL_TEXTURE_BINDING_2D);
	}

	@Override
	public Object clone() {
		return this.clone();
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
	public static int generateTexture(BufferedImage image, int wrapmode) {
		
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
		// You now have a ByteBuffer filled with the color data of each pixel.
		// Now just create a texture ID and bind it. Then you can load it using
		// whatever OpenGL method you want, for example:

		int textureID = GL11.glGenTextures(); // Generate texture ID
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID); // Bind texture ID
		// Setup wrap mode
		// GL11.glTexParameteri(GL11.GL_TEXTURE_2D,
		// GL11.GL_TEXTURE_WRAP_S,GL12.GL_CLAMP_TO_EDGE);
		// GL11.glTexParameteri(GL11.GL_TEXTURE_2D,
		// GL11.GL_TEXTURE_WRAP_T,GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER,GL11.GL_LINEAR);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA,
				image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA,
				GL11.GL_UNSIGNED_BYTE, buffer);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		return textureID;
	}

	public static int generateTexture(BufferedImage image) {
		return generateTexture(image, GL12.GL_CLAMP_TO_EDGE);
	}
}

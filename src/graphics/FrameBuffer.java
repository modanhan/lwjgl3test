package graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.*;

import static org.lwjgl.opengl.EXTFramebufferObject.*;


public class FrameBuffer {
	private Texture tex;
	private int framebufferID,colorTextureID,depthRenderBufferID;
	public FrameBuffer(int width, int height){         
         framebufferID = glGenFramebuffersEXT();                                         // create a new framebuffer
         colorTextureID = glGenTextures();                                               // and a new texture used as a color buffer
         depthRenderBufferID = glGenRenderbuffersEXT();                                  // And finally a new depthbuffer
  
         glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, framebufferID);                        // switch to the new framebuffer
  
         // initialize color texture
         glBindTexture(GL_TEXTURE_2D, colorTextureID);                                   // Bind the colorbuffer texture
         glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);               // make it linear filterd
         glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0,GL_RGBA, GL_INT, (java.nio.ByteBuffer) null);  // Create the texture data
         glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT,GL_COLOR_ATTACHMENT0_EXT,GL_TEXTURE_2D, colorTextureID, 0); // attach it to the framebuffer
  
  
         // initialize depth renderbuffer
         glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, depthRenderBufferID);                // bind the depth renderbuffer
         glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL_DEPTH_COMPONENT24, width, height); // get the data space for it
         glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT,GL_DEPTH_ATTACHMENT_EXT,GL_RENDERBUFFER_EXT, depthRenderBufferID); // bind it to the renderbuffer
  
         glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);                                    // Swithch back to normal framebuffer rendering
         tex = new Texture(colorTextureID);
	}
	public int getID(){
		return framebufferID;
	}
	public Texture getTexture(){
		return tex;
	}
	public void dispose(){
		glDeleteFramebuffersEXT(framebufferID);
	}
}

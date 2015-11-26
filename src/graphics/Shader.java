package graphics;
import java.io.File;
import java.util.Scanner;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
public class Shader {
	private int programID,vertexShaderID,fragmentShaderID;

	public Shader(){
		programID = glCreateProgram();
	}
	public int getID(){
		return programID;
	}
	public void attachVertexShader(String name)
	{
	    // Load the source
	    String vertexShaderSource = (name);

	    // Create the shader and set the source
	    vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
	    glShaderSource(vertexShaderID, vertexShaderSource);

	    // Compile the shader
	    glCompileShader(vertexShaderID);

	    // Check for errors
	    if (glGetShaderi(vertexShaderID, GL_COMPILE_STATUS) == GL_FALSE)
	        throw new RuntimeException("Error creating vertex shader\n"
	                                   + glGetShaderInfoLog(vertexShaderID, glGetShaderi(vertexShaderID, GL_INFO_LOG_LENGTH)));

	    // Attach the shader
	    glAttachShader(programID, vertexShaderID);
	}
	public void attachFragmentShader(String name)
	{
	    // Load the source
	    String fragmentShaderSource = (name);
	    // Create the shader and set the source
	    fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
	    glShaderSource(fragmentShaderID, fragmentShaderSource);

	    // Compile the shader
	    glCompileShader(fragmentShaderID);

	    // Check for errors
	    if (glGetShaderi(fragmentShaderID, GL_COMPILE_STATUS) == GL_FALSE)
	        throw new RuntimeException("Error creating vertex shader\n"
	                                   + glGetShaderInfoLog(fragmentShaderID, glGetShaderi(fragmentShaderID, GL_INFO_LOG_LENGTH)));

	    // Attach the shader
	    glAttachShader(programID, fragmentShaderID);
	}
	public void link()
	{
	    // Link this program
	    glLinkProgram(programID);

	    // Check for linking errors
	    if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE)
	        throw new RuntimeException("Unable to link shader program:");
	}
	public void dispose()
	{
		
	    // Detach the shaders
	    glDetachShader(programID, vertexShaderID);
	    glDetachShader(programID, fragmentShaderID);

	    // Delete the shaders
	    glDeleteShader(vertexShaderID);
	    glDeleteShader(fragmentShaderID);

	    // Delete the program
	    glDeleteProgram(programID);
	}
	public static int getCurrentID(){
		return glGetInteger(GL_CURRENT_PROGRAM);
	}
	public static String readFromFile(String name)
	{
	    StringBuilder source = new StringBuilder();
	    try
	    {
	        Scanner reader = new Scanner(new File(name));
	        while (reader.hasNextLine())
	        {
	            source.append(reader.nextLine()).append("\n");
	        }
	        reader.close();
	    }
	    catch (Exception e)
	    {
	        System.err.println("Error loading source code: " + name);
	        e.printStackTrace();
	    }

	    return source.toString();
	}
	public static void use(Shader s){
		glUseProgram(s!=null?s.getID():0);
	}
	public static void use(int id){
		glUseProgram(id);
	}
}

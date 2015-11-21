uniform sampler2D texture;
void main()
{
	gl_FragColor = gl_Color*texture2D(texture, gl_TexCoord[0].st)*vec4(0.5,0.5,1,1);
}
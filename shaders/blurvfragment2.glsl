uniform sampler2D texture; // this should hold the texture rendered by the horizontal blur pass
 
const float blurSize = 1.0/1080.0*2.82;
 
void main(void)
{
   vec4 sum = vec4(0.0);
 
   // blur in y (vertical)
  	sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y - 7.0*blurSize))*0.0044299121055113265;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y - 6.0*blurSize))*0.00895781211794;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y - 5.0*blurSize))*0.0215963866053;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y - 4.0*blurSize))*0.0443683338718;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y - 3.0*blurSize))*0.0776744219933;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y - 2.0*blurSize))*0.115876621105;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y - 1.0*blurSize))*0.147308056121;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y               ))*0.159576912161;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y + 1.0*blurSize))*0.147308056121;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y + 2.0*blurSize))*0.115876621105;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y + 3.0*blurSize))*0.0776744219933;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y + 4.0*blurSize))*0.0443683338718;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y + 5.0*blurSize))*0.0215963866053;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y + 6.0*blurSize))*0.00895781211794;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x, gl_TexCoord[0].y + 7.0*blurSize))*0.0044299121055113265;
   gl_FragColor = sum;
}
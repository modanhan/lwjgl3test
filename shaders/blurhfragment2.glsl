uniform sampler2D texture; // the texture with the scene you want to blur
 
const float blurSize = 1.0/1920.0*2.82; 

void main(void)
{
   vec4 sum = vec4(0.0);
 
   // blur in y (vertical)
   // take nine samples, with the distance blurSize between them
   	sum += texture2D(texture, vec2(gl_TexCoord[0].x - 7.0*blurSize,gl_TexCoord[0].y))*0.0044299121055113265;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x - 6.0*blurSize,gl_TexCoord[0].y))*0.00895781211794;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x - 5.0*blurSize,gl_TexCoord[0].y))*0.0215963866053;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x - 4.0*blurSize,gl_TexCoord[0].y))*0.0443683338718;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x - 3.0*blurSize,gl_TexCoord[0].y))*0.0776744219933;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x - 2.0*blurSize,gl_TexCoord[0].y))*0.115876621105;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x - 1.0*blurSize,gl_TexCoord[0].y))*0.147308056121;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x               ,gl_TexCoord[0].y))*0.159576912161;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x + 1.0*blurSize,gl_TexCoord[0].y))*0.147308056121;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x + 2.0*blurSize,gl_TexCoord[0].y))*0.115876621105;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x + 3.0*blurSize,gl_TexCoord[0].y))*0.0776744219933;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x + 4.0*blurSize,gl_TexCoord[0].y))*0.0443683338718;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x + 5.0*blurSize,gl_TexCoord[0].y))*0.0215963866053;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x + 6.0*blurSize,gl_TexCoord[0].y))*0.00895781211794;
    sum += texture2D(texture, vec2(gl_TexCoord[0].x + 7.0*blurSize,gl_TexCoord[0].y))*0.0044299121055113265;
 
   gl_FragColor = sum;
}
package com.example.batman;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

class Batman {
    ObjLoad objLoad = new ObjLoad("model/The DarkNight.obj");
    private FloatBuffer mVertexBuffer;
        
    private float vertices[] = new float[objLoad.size()];
                
    public Batman() 
    {
    		for(int i = 0; i < vertices.length; i++)
    		{
    			vertices[i] = objLoad.get(i);
    		}
            ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
            byteBuf.order(ByteOrder.nativeOrder());
            mVertexBuffer = byteBuf.asFloatBuffer();
            mVertexBuffer.put(vertices);
            mVertexBuffer.flip();                
    }

    public void draw(GL10 gl)
    {             
//            gl.glFrontFace(GL10.GL_CW);   
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);         
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);   
            gl.glColor4f(1f, 0f, 0f, 1f);
            gl.glDrawArrays(GL10.GL_TRIANGLES, 0,vertices.length);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}

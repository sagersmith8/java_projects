package com.example.MusicGame;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Sage on 8/5/2014.
 */
public class Tower {
    int counter = 0;
    private float vertices[]={
            -3.75f, -3.25f, 0.0f,
            -3.75f,-3.25f,0.0f,
            -2.75f,-3.25f,0.0f,
            -2.75f,-3.25f,0.0f
    };

    private short[] indices = {0,1,2,0,2,3};

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;

    public Tower(float t){
        for(int i = 0; i < vertices.length; i+=3){
            vertices[i]+=(1.25*t);
        }
    }

    public void draw(GL10 gl, float y){
        vertices[1] = y-3.25f;
        vertices[10] = y-3.25f;

        counter++;

        ByteBuffer vbb  = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);

        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}

package com.example.MusicGame;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Sage on 8/19/2014.
 */
public class Elf {
    private float vertices[]={
            -0.5f,  2.75f, 0.0f,   // top left
            -0.5f, 2.25f, 0.0f,   // bottom left
            0.0f, 2.25f, 0.0f,   // bottom right
            0.0f,  2.75f, 0.0f
    };

    private short[] indices = {0,1,2,0,2,3};

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;


    public void draw(GL10 gl, Point point){
        vertices[0]=point.x;
        vertices[3]=point.x;
        vertices[6]=point.x+.5f;
        vertices[9]=point.x+.5f;
        vertices[1]=point.y;
        vertices[4]=point.y-.5f;
        vertices[7]=point.y-.5f;
        vertices[10]=point.y;

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

package com.holu31.brainwave.render

import com.holu31.brainwave.byteBuffer
import com.holu31.brainwave.intBuffer
import org.joml.Vector2i
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL33.*
import org.lwjgl.stb.STBImage.stbi_image_free
import org.lwjgl.stb.STBImage.stbi_load_from_memory
import java.nio.ByteBuffer
import java.nio.IntBuffer

class Texture constructor(bytes: ByteArray) {

    var id: Int = glGenTextures()

    val size: Vector2i
    val channels: Int
    val data: ByteBuffer

    init {
        val wb: IntBuffer = intBuffer()
        val hb: IntBuffer = intBuffer()
        val cb: IntBuffer = intBuffer()

        val fromBuffer: ByteBuffer = byteBuffer(bytes.size)
        fromBuffer.put(bytes).flip()

        data = stbi_load_from_memory(fromBuffer, wb, hb, cb, 4)
            ?: throw RuntimeException("failed to load texture")

        size = Vector2i(wb[0], hb[0])
        channels = cb[0]
    }

    internal fun import() {
        bind()
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1)

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP)

        GL11.glTexImage2D(GL_TEXTURE_2D,
            0, GL_RGBA,
            size.x, size.y,
            0, GL_RGBA,
            GL_UNSIGNED_BYTE, data)
        unbind()

        stbi_image_free(data)
    }

    internal fun bind(): Unit = glBindTexture(GL_TEXTURE_2D, id)
    internal fun unbind(): Unit = glBindTexture(GL_TEXTURE_2D, GL_FALSE)
}
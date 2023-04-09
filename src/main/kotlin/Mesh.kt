import org.joml.*
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.opengl.GL33.*

class Mesh constructor(
	var shader: Shader,
	var positions: FloatArray,
	var indices: IntArray,
	var colors: FloatArray,
) {

	companion object {

		fun cube(shader: Shader) = Mesh(
			shader,
			floatArrayOf(
				1.0f, 1.0f, 1.0f,
				1.0f, -1.0f, 1.0f,
				-1.0f, -1.0f, 1.0f,
				-1.0f, 1.0f, 1.0f,

				1.0f, 1.0f, -1.0f,
				1.0f, -1.0f, -1.0f,
				-1.0f, -1.0f, -1.0f,
				-1.0f, 1.0f, -1.0f,

				-1.0f, 1.0f, 1.0f,
				-1.0f, -1.0f, 1.0f,
				-1.0f, -1.0f, -1.0f,
				-1.0f, 1.0f, -1.0f,

				1.0f, 1.0f, 1.0f,
				1.0f, -1.0f, 1.0f,
				1.0f, -1.0f, -1.0f,
				1.0f, 1.0f, -1.0f,

				-1.0f, -1.0f, 1.0f,
				1.0f, -1.0f, 1.0f,
				1.0f, -1.0f, -1.0f,
				-1.0f, -1.0f, -1.0f,

				-1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, -1.0f,
				-1.0f, 1.0f, -1.0f,
			),
			intArrayOf(
				0, 1, 2,
				2, 3, 0,

				4, 5, 6,
				6, 7, 4,

				8, 9, 10,
				10, 11, 8,

				12, 13, 14,
				14, 15, 12,

				16, 17, 18,
				18, 19, 16,

				20, 21, 22,
				22, 23, 20,
			),
			floatArrayOf(
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,

				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,

				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,

				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,

				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,

				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
				0.8f, 0.8f, 0.8f, 1.0f,
			),
		)

	}
	
	val vao: Int = glGenVertexArrays()
	
	val posBuffer: Int = glGenBuffers()
	val indexBuffer: Int = glGenBuffers()
	val colBuffer: Int = glGenBuffers()
	
	var pos: Vector3f = Vector3f()
	var rot: Vector3f = Vector3f()
	var scale: Vector3f = Vector3f(1.0f)
	
	val transMat: Matrix4f
		get() = Matrix4f().apply {
			identity()
			rotateXYZ(rot.toRad())
			translate(pos)
			scale(scale)
		}
	
	init {
		updateBuffers()
	}
	
	private fun setArrayBuffer(pos: Int, size: Int, id: Int, data: FloatArray) {
		glBindBuffer(GL_ARRAY_BUFFER, id)
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW)
		glEnableVertexAttribArray(pos)
		glVertexAttribPointer(pos, size, GL_FLOAT, false, 0, NULL)
		glBindBuffer(GL_ARRAY_BUFFER, GL_FALSE)
	}
	
	private fun setElementArrayBuffer(id: Int, data: IntArray) {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id)
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW)
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, GL_FALSE)
	}
	
	fun updateBuffers() {
		glBindVertexArray(vao)
		
		setArrayBuffer(0, 3, posBuffer, positions)
		setElementArrayBuffer(indexBuffer, indices)
		setArrayBuffer(1, 4, colBuffer, colors)
		
		glBindVertexArray(GL_FALSE)
	}
	
	fun draw() {
		glBindVertexArray(vao)
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer)
		shader.bind()
		
		shader.setUniform("projMat", Camera.current!!.projMat)
		shader.setUniform("viewMat", Camera.current!!.viewMat)
		shader.setUniform("transMat", transMat)
		
		glDrawElements(GL_TRIANGLES, indices.size, GL_UNSIGNED_INT, NULL)
		
		glBindVertexArray(GL_FALSE)
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, GL_FALSE)
		shader.unbind()
	}

}
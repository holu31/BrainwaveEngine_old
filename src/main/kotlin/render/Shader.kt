package render

import org.joml.Matrix4f
import org.lwjgl.opengl.GL33.*

class Shader constructor(
	val vertex: String,
	val fragment: String,
) {
	
	val program: Int = glCreateProgram()
	val vShader: Int = glCreateShader(GL_VERTEX_SHADER)
	val fShader: Int = glCreateShader(GL_FRAGMENT_SHADER)
	
	init {
		setSource(vShader, vertex)
		setSource(fShader, fragment)
	}
	
	private fun setSource(id: Int, source: String) {
		glShaderSource(id, source)
		glCompileShader(id)
		
		if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE)
			println("failed to compile shader $id, ${glGetShaderInfoLog(id)}")
		
		glAttachShader(program, id)
		glLinkProgram(program)
		
		if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE)
			println("failed to link program $program, ${glGetProgramInfoLog(program)}")
		
		glDeleteShader(id)
	}
	
	fun bind(): Unit = glUseProgram(program)
	fun unbind(): Unit = glUseProgram(GL_FALSE)
	
	fun getUniformLocation(uniform: String): Int {
		val pos: Int = glGetUniformLocation(program, uniform)
		if (pos == -1)
			println("failed to get uniform $uniform from program $program")
		
		return pos
	}

	fun getULoc(uname: String): Int = glGetUniformLocation(program, uname)

	fun setUInt(uname: String, value: Int): Unit = glUniform1i(getULoc(uname), value)
	
	// тут если что мы передаём в шейдер массив переменных из матрицы, который сохраняем в массив флоатов
	fun setUniform(uniform: String, value: Matrix4f): Unit =
		glUniformMatrix4fv(getUniformLocation(uniform), false, value.get(floatBuffer(16)))
}
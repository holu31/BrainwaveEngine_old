import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL33
import org.lwjgl.system.MemoryUtil

abstract class Engine constructor(
	width: Int,
	height: Int,
	title: String
) {
	
	val window: Long

	private var prevDeltaTime: Float = GLFW.glfwGetTime().toFloat()
	
	init {
		if (!GLFW.glfwInit())
			throw RuntimeException("failed to initialize glfw")
		
		GLFW.glfwDefaultWindowHints()
		
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3)
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3)
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE)
		
		window = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL)
		if (window == MemoryUtil.NULL)
			throw RuntimeException("failed to create window")

		val keyCallback = GLFW.glfwSetKeyCallback(window, object : GLFWKeyCallback() {
			override fun invoke(
				window: Long,
				key: Int,
				scancode: Int,
				action: Int,
				mods: Int
			) {
				if(action == GLFW.GLFW_RELEASE) input(key)
			}
		})
		
		GLFW.glfwMakeContextCurrent(window)
		GL.createCapabilities()
		
		GL33.glEnable(GL33.GL_DEPTH_TEST)
		
		GL33.glClearColor(0.4f, 0.4f, 0.4f, 1.0f)
		
		run(::start)
		while (!GLFW.glfwWindowShouldClose(window))
			logic()
		
		GLFW.glfwTerminate()
	}
	
	fun logic() {
		GLFW.glfwPollEvents()
		GL33.glClear(GL33.GL_COLOR_BUFFER_BIT or GL33.GL_DEPTH_BUFFER_BIT)

		var deltaTime = GLFW.glfwGetTime().toFloat() - prevDeltaTime
		prevDeltaTime = GLFW.glfwGetTime().toFloat()
		
		update(deltaTime)
		
		GLFW.glfwSwapBuffers(window)
		GL33.glFinish()
	}

	abstract fun start()
	abstract fun update(deltaTime: Float)
	abstract fun input(key: Int)
	
}
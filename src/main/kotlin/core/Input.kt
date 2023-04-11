package core
import doubleBuffer
import minus
import org.joml.Vector2f
import org.joml.Vector3f
import org.lwjgl.glfw.GLFW.*

class Input constructor(
    val window: Long
) {

    private var lastMousePos = Vector2f(0.0f, 0.0f)

    fun pressed(key: Int) = glfwGetKey(window, key) == GLFW_PRESS

    fun cursorLock() = glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED)
    fun cursorHide() = glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_HIDDEN)
    fun cursorShow() = glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL)

    val mousePosition
        get(): Vector2f {
            var xpos = doubleBuffer(1)
            var ypos = doubleBuffer(1)
            glfwGetCursorPos(window, xpos, ypos)
            xpos.rewind()
            ypos.rewind()

            return Vector2f(xpos[0].toFloat(), ypos[0].toFloat())
        }

    val mouseMotion
        get(): Vector2f {
            if (mousePosition == lastMousePos)
                return Vector2f(0.0f, 0.0f)

            var motion = mousePosition - lastMousePos
            lastMousePos = mousePosition

            return motion

        }

    fun getAxis(key1: Int,
                    key2: Int,
                    key3: Int,
                    key4: Int
    ): Vector3f {
        val direction = Vector3f(0.0f, 0.0f, 0.0f)

        direction.z -= if (pressed(KEY_W)) 1.0f else 0.0f
        direction.z += if (pressed(KEY_S)) 1.0f else 0.0f

        direction.x -= if (pressed(KEY_A)) 1.0f else 0.0f
        direction.x += if (pressed(KEY_D)) 1.0f else 0.0f

        return direction
    }

    val KEY_W = GLFW_KEY_W
    val KEY_A = GLFW_KEY_A
    val KEY_S = GLFW_KEY_S
    val KEY_D = GLFW_KEY_D

    val KEY_LEFT = GLFW_KEY_LEFT
    val KEY_UP = GLFW_KEY_UP
    val KEY_DOWN = GLFW_KEY_DOWN
    val KEY_RIGHT = GLFW_KEY_RIGHT

    val KEY_SPACE = GLFW_KEY_SPACE
    val KEY_LSHIFT = GLFW_KEY_LEFT_SHIFT
    val KEY_RSHIFT = GLFW_KEY_RIGHT_SHIFT

}
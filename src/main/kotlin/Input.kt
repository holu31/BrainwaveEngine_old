import org.joml.Vector2f
import org.joml.Vector3f
import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW.*
import java.nio.DoubleBuffer
import java.nio.FloatBuffer

class Input {

    companion object {

        fun pressed(window: Long, key: Int) = glfwGetKey(window, key) == GLFW_PRESS

        fun cursorDisable(window: Long) = glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED)
        fun cursorHide(window: Long) = glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_HIDDEN)
        fun cursorShow(window: Long) = glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL)

        fun mousePosition(window: Long): Vector2f {
            var xpos = BufferUtils.createDoubleBuffer(1)
            var ypos = BufferUtils.createDoubleBuffer(1)
            glfwGetCursorPos(window, xpos, ypos)
            xpos.rewind()
            ypos.rewind()

            var mousePos = Vector2f(xpos[0].toFloat(), ypos[0].toFloat())

            return mousePos
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

}
import java.nio.*
import org.joml.*
import org.lwjgl.BufferUtils

// Float

fun Float.toRad(): Float = Math.toRadians(toDouble()).toFloat()
fun Float.toDeg(): Float = Math.toDegrees(toDouble()).toFloat()

// Vector3f

fun Vector3f.toRad(): Vector3f = Vector3f(x.toRad(), y.toRad(), z.toRad())
fun Vector3f.toDeg(): Vector3f = Vector3f(x.toDeg(), y.toDeg(), z.toDeg())

operator fun Vector3f.unaryMinus(): Vector3f = Vector3f(-x, -y, -z)

// Buffers

fun floatBuffer(capacity: Int): FloatBuffer = BufferUtils.createFloatBuffer(capacity)
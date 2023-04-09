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
operator fun Vector3f.plus(o: Vector3f): Vector3f = Vector3f(x + o.x, y + o.y, z + o.z)
operator fun Vector3f.times(o: Vector3f): Vector3f = Vector3f(x * o.x, y * o.y, z * o.z)
operator fun Vector3f.times(by: Float): Vector3f = Vector3f(x * by, y * by, z * by)

// Vector2f
operator fun Vector2f.minus(o: Vector2f): Vector2f = Vector2f(x - o.x, y - o.y)
operator fun Vector2f.plus(o: Vector2f): Vector2f = Vector2f(x + o.x, y + o.y)
operator fun Vector2f.times(o: Vector2f): Vector2f = Vector2f(x * o.x, y * o.y)

// Buffers

fun floatBuffer(capacity: Int): FloatBuffer = BufferUtils.createFloatBuffer(capacity)

import org.joml.Math
import org.joml.Vector2f
import org.joml.Vector3f
import org.lwjgl.BufferUtils
import java.nio.ByteBuffer
import java.nio.DoubleBuffer
import java.nio.FloatBuffer
import java.nio.IntBuffer

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

operator fun javax.vecmath.Vector3f.plus(o: Vector3f):
        javax.vecmath.Vector3f = javax.vecmath.Vector3f(x + o.x, y + o.y, z + o.z)
operator fun javax.vecmath.Vector3f.times(o: Vector3f):
        javax.vecmath.Vector3f = javax.vecmath.Vector3f(x * o.x, y * o.y, z * o.z)
operator fun javax.vecmath.Vector3f.times(by: Float):
        javax.vecmath.Vector3f = javax.vecmath.Vector3f(x * by, y * by, z * by)

fun Vector3f.toJavaX(): javax.vecmath.Vector3f = javax.vecmath.Vector3f(x, y, z)
fun Vector3f.unique(): Vector3f = Vector3f(x, y, z)

// Vector2f
operator fun Vector2f.minus(o: Vector2f): Vector2f = Vector2f(x - o.x, y - o.y)
operator fun Vector2f.plus(o: Vector2f): Vector2f = Vector2f(x + o.x, y + o.y)
operator fun Vector2f.times(o: Vector2f): Vector2f = Vector2f(x * o.x, y * o.y)

// Buffers

fun doubleBuffer(capacity: Int = 1): DoubleBuffer = BufferUtils.createDoubleBuffer(capacity)
fun floatBuffer(capacity: Int = 1): FloatBuffer = BufferUtils.createFloatBuffer(capacity)
fun intBuffer(capacity: Int = 1): IntBuffer = BufferUtils.createIntBuffer(capacity)
fun byteBuffer(capacity: Int = 1): ByteBuffer =  BufferUtils.createByteBuffer(capacity)
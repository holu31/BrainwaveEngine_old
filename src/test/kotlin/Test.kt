
import com.bulletphysics.linearmath.Transform
import core.Config
import core.Engine
import org.joml.Math.clamp
import org.joml.Vector3f
import physics.PhysicsObject
import render.*

class Test : Engine(960, 540, "Test", config = Config(
    resizable = false
)
) {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            Test()
        }

    }

    lateinit var camera: Camera
    lateinit var skybox: Mesh

    lateinit var mesh: Mesh

    lateinit var player: PhysicsObject

    val speed: Float
        get() = 2.0f

    override fun start() {
        camera = Camera()
        camera.pos.z = 2.0f

        var shader = Shader(
            Asset("assets/mesh.vert").text,
            Asset("assets/mesh.frag").text,
        )

        skybox = Mesh.cube(shader, Texture(
            Asset("assets/cringe.jpg").bytes
        )
        )
        skybox.scale = Vector3f(60f)

        mesh = Mesh.cube(shader,
            texture = Texture(
                Asset("assets/tile_texture.jpg").bytes),
            phys = PhysicsObject.cube(
            Vector3f(10.0f, 0.1f, 10.0f).toJavaX(),
            0.0f
        ))
        mesh.scale = Vector3f(10.0f, 0.1f, 10.0f)

        player = PhysicsObject.cube(
            Vector3f(1.0f, 1.0f, 1.0f).toJavaX(),
            1.0f
        )
        player.rb.translate(Vector3f(
            0.0f, 10.0f, 0.0f).toJavaX())

        input.cursorLock()
    }

    override fun update(deltaTime: Float) {
        skybox.draw()
        mesh.draw()

        val dir2 = input.getAxis(
            input.KEY_UP,
            input.KEY_DOWN,
            input.KEY_LEFT,
            input.KEY_RIGHT
        )

        /*dir2.y += if (input.pressed(input.KEY_SPACE)) 1.0f else 0.0f
        dir2.y -= if (input.pressed(input.KEY_LSHIFT)) 1.0f else 0.0f

        camera.pos += dir2.rotateY(camera.rot.y.toRad()) * speed * deltaTime*/

        var transform = Transform()

        player.rb.getWorldTransform(transform)
        camera.pos = Vector3f(transform.origin.x, transform.origin.y, transform.origin.z)

        skybox.pos = camera.pos

        player.rb.translate(dir2.rotateY(camera.rot.y.toRad()).toJavaX() * speed * deltaTime)

        val motion = input.mouseMotion

        camera.rot.y -= motion.x * .1f
        camera.rot.x -= motion.y * .1f
        camera.rot.x = clamp(-90.0f, 90.0f, camera.rot.x)

    }

    override fun input(key: Int) {
        if (key == input.KEY_SPACE) {
            println("sus")
        }
    }

}
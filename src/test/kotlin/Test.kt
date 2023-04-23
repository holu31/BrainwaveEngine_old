
import com.bulletphysics.linearmath.Transform
import com.holu31.brainwave.core.Config
import com.holu31.brainwave.core.Engine
import com.holu31.brainwave.physics.PhysicsObject
import com.holu31.brainwave.render.*
import com.holu31.brainwave.times
import com.holu31.brainwave.toJavaX
import com.holu31.brainwave.toRad
import org.joml.Math.clamp
import org.joml.Vector3f
import kotlin.system.exitProcess

class Test : Engine(960, 540, "Test", config = Config(
    resizable = false,
    msaa = 2
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
    lateinit var mesh2: Mesh

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
            Vector3f(1f, 1.0f, 1f).toJavaX(),
            1.0f
        )
        player.rb.translate(Vector3f(
            0.0f, 10.0f, 0.0f).toJavaX())

        input.cursorLock()
    }

    override fun update(deltaTime: Float) {
        skybox.draw()
        mesh.draw()

        val dir = input.getAxis(
            input.KEY_W,
            input.KEY_S,
            input.KEY_A,
            input.KEY_D
        )

        var transform = Transform()

        player.rb.getWorldTransform(transform)
        camera.pos = Vector3f(transform.origin.x, transform.origin.y, transform.origin.z)

        skybox.pos = camera.pos

        player.rb.translate(dir.rotateY(camera.rot.y.toRad()).toJavaX() * speed * deltaTime)

        val motion = input.mouseMotion

        camera.rot.y -= motion.x * .1f
        camera.rot.x -= motion.y * .1f
        camera.rot.x = clamp(-90.0f, 90.0f, camera.rot.x)

    }

    override fun input(key: Int) {
        if (key == input.KEY_ESCAPE) {
            exitProcess(0)
        }
        if(key == input.KEY_SPACE){
            if(player.isGround())
                player.rb.applyCentralImpulse(Vector3f(0f, 5f, 0f).toJavaX())
        }
    }

}
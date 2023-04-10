import core.Config
import core.Engine
import org.joml.Math.clamp
import org.joml.Vector3f
import physics.Physics
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

        mesh = Mesh.cube(shader)

        PhysicsObject.cube(
            javax.vecmath.Vector3f(0.0f, 35.0f, 0.0f),
            0.1f
            )

        input.cursorLock()
    }

    override fun update(deltaTime: Float) {
        skybox.draw()
        mesh.draw()
        Physics.dynamicsWorld.stepSimulation(deltaTime, 7)
        Physics.dynamicsWorld.performDiscreteCollisionDetection()


        //mesh.rot.y -= 150.0f * deltaTime

        var direction = input.getAxis(
            input.KEY_W,
            input.KEY_S,
            input.KEY_A,
            input.KEY_D
        )

        camera.pos += direction.rotateY(camera.rot.y.toRad()) * speed * deltaTime
        skybox.pos += direction * speed * deltaTime

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
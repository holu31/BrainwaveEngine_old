import org.joml.Vector2f
import org.joml.Vector3f

class Test : Engine(960, 540, "Test") {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            Test()
        }

    }

    lateinit var camera: Camera
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
        mesh = Mesh.cube(shader)

        input.cursorLock()
    }

    override fun update(deltaTime: Float) {
        mesh.draw()

        //mesh.rot.y -= 150.0f * deltaTime

        var direction = input.getAxis(
            input.KEY_W,
            input.KEY_S,
            input.KEY_A,
            input.KEY_D
        )

        camera.pos += direction.rotateY(camera.rot.y.toRad()) * speed * deltaTime

        val motion = input.mouseMotion

        camera.rot.y -= motion.x * .1f
        camera.rot.x -= motion.y * .1f

    }

    override fun input(key: Int) {
        if (key == input.KEY_SPACE) {
            println("sus")
        }
    }

}
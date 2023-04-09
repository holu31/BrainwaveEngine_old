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
    var lastMousePos = Vector2f(0.0f, 0.0f)

    override fun start() {
        camera = Camera()
        camera.pos.z = 2.0f

        var shader = Shader(
            Asset("assets/mesh.vert").text,
            Asset("assets/mesh.frag").text,
        )
        mesh = Mesh.cube(shader)
        //mesh.scale = Vector3f(1f, 0.1f, 1f)
        Input.cursorHide(window)
        lastMousePos = Input.mousePosition(window)
        camera.rot.x -= 2.0f
    }

    override fun update(deltaTime: Float) {
        mesh.draw()

        //mesh.rot.y -= 150.0f * deltaTime

        camera.pos.z -= if (Input.pressed(window, Input.KEY_W)) speed * deltaTime else 0.0f
        camera.pos.z += if (Input.pressed(window, Input.KEY_S)) speed * deltaTime else 0.0f

        camera.pos.x -= if (Input.pressed(window, Input.KEY_A)) speed * deltaTime else 0.0f
        camera.pos.x += if (Input.pressed(window, Input.KEY_D)) speed * deltaTime else 0.0f

        camera.pos.y += if (Input.pressed(window, Input.KEY_SPACE)) speed * deltaTime else 0.0f
        camera.pos.y -= if (Input.pressed(window, Input.KEY_LSHIFT)) speed * deltaTime else 0.0f

        if (Input.mousePosition(window) != lastMousePos) {
            if(Input.mousePosition(window).x < lastMousePos.x) {
                camera.rot.y += Input.mousePosition(window).x / 2 * deltaTime
                //camera.rot.x += Input.mousePosition(window).y / 2 * deltaTime
            }
            else {
                camera.rot.y -= Input.mousePosition(window).x / 2 * deltaTime
                //camera.rot.x -= Input.mousePosition(window).y / 2 * deltaTime
            }
            lastMousePos = Input.mousePosition(window)
        }

    }

    override fun input(key: Int) {
        if (key == Input.KEY_SPACE) {
            println("sus")
        }
    }

}
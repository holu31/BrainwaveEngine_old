import org.joml.Math.clamp
import org.joml.Vector3f

class Test : Engine(960, 540, "Test") {

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
    lateinit var mesh3: Mesh

    lateinit var tile: Mesh
    lateinit var tile2: Mesh
    lateinit var tile3: Mesh
    lateinit var tile4: Mesh

    lateinit var wall: Mesh

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
        ))
        skybox.scale = Vector3f(60f)

        mesh = Mesh.cube(shader, Texture(
            Asset("assets/box_texture.jpg").bytes
        ))
        mesh.rot.y += 25.0f

        mesh2 = Mesh.cube(shader, Texture(
            Asset("assets/box_texture.jpg").bytes
        ))
        mesh2.pos.x -= 2.5f
        mesh2.pos.z += 0.5f
        mesh2.rot.y -= 15.0f

        mesh3 = Mesh.cube(shader, Texture(
            Asset("assets/box_texture.jpg").bytes
        ))

        mesh3.pos.x -= 1.5f
        mesh3.pos.z -= 0.2f
        mesh3.pos.y += 2.0f

        tile = Mesh.cube(shader, Texture(
            Asset("assets/tile_texture.jpg").bytes
        ))
        tile.scale = Vector3f(5.0f, 0.1f, 5.0f)
        tile.pos.y -= 1.1f

        tile2 = Mesh.cube(shader, Texture(
            Asset("assets/tile_texture.jpg").bytes
        ))
        tile2.scale = Vector3f(5.0f, 0.1f, 5.0f)
        tile2.pos.y -= 1.1f
        tile2.pos.x += 10.0f

        tile3 = Mesh.cube(shader, Texture(
            Asset("assets/tile_texture.jpg").bytes
        ))
        tile3.scale = Vector3f(5.0f, 0.1f, 5.0f)
        tile3.pos.y -= 1.1f
        tile3.pos.z += 10.0f

        tile4 = Mesh.cube(shader, Texture(
            Asset("assets/tile_texture.jpg").bytes
        ))
        tile4.scale = Vector3f(5.0f, 0.1f, 5.0f)
        tile4.pos.y -= 1.1f
        tile4.pos.z += 10.0f
        tile4.pos.x += 10.0f

        wall = Mesh.cube(shader, Texture(
            Asset("assets/wall_texture.jpg").bytes
        ))
        wall.scale = Vector3f(3.0f, 0.1f, 3.0f)
        wall.rot.y = 90.0f

        input.cursorLock()
    }

    override fun update(deltaTime: Float) {
        skybox.draw()

        mesh.draw()
        mesh2.draw()
        mesh3.draw()

        tile.draw()
        tile2.draw()
        tile3.draw()
        tile4.draw()

        wall.draw()

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
        camera.rot.x = clamp(-90.0f, 90.0f, camera.rot.x)

    }

    override fun input(key: Int) {
        if (key == input.KEY_SPACE) {
            println("sus")
        }
    }

}
import com.holu31.brainwave.core.Config
import com.holu31.brainwave.core.Engine
import com.holu31.brainwave.render.Asset
import com.holu31.brainwave.render.Camera
import com.holu31.brainwave.render.Mesh
import com.holu31.brainwave.render.Shader

class Editor : Engine(960, 540, "Test", config = Config(
    resizable = false,
    msaa = 2
)
) {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            Editor()
        }

    }

    lateinit var camera: Camera
    lateinit var mesh: Mesh

    override fun start() {
        camera = Camera()
        camera.pos.z = 2.0f
        
        mesh = Mesh.cube(Shader(
            Asset("assets/mesh.vert").text,
            Asset("assets/mesh.frag").text
            )
        )
    }

    override fun update(deltaTime: Float) {
        mesh.draw()
    }

    override fun input(key: Int) {

    }
}
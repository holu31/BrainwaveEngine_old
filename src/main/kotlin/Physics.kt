import com.bulletphysics.collision.broadphase.BroadphaseInterface
import com.bulletphysics.collision.broadphase.DbvtBroadphase
import com.bulletphysics.dynamics.DynamicsWorld
import com.bulletphysics.dynamics.RigidBody

class Physics {

    private lateinit var dynamicWorld: DynamicsWorld
    private lateinit var obj: RigidBody
    private var rb: Set<RigidBody> = HashSet<RigidBody>()
    private var applyForce: Boolean = false

    init {
        var broadphase: BroadphaseInterface = DbvtBroadphase()

    }

}
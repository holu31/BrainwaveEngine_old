package physics
import com.bulletphysics.collision.broadphase.BroadphaseInterface
import com.bulletphysics.collision.broadphase.DbvtBroadphase
import com.bulletphysics.collision.dispatch.CollisionDispatcher
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration
import com.bulletphysics.dynamics.DiscreteDynamicsWorld
import com.bulletphysics.dynamics.DynamicsWorld
import com.bulletphysics.dynamics.RigidBody
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver
import javax.vecmath.Vector3f

object Physics {

    var dynamicsWorld: DynamicsWorld
    private lateinit var obj: RigidBody
    private var rb: Set<RigidBody> = HashSet<RigidBody>()
    private var applyForce: Boolean = false

    init {
        var broadphase: BroadphaseInterface = DbvtBroadphase()
        var collisionConfiguration = DefaultCollisionConfiguration()
        var dispatcher = CollisionDispatcher(collisionConfiguration)
        var solver: ConstraintSolver = SequentialImpulseConstraintSolver()
        dynamicsWorld = DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration)
        dynamicsWorld.setGravity(Vector3f(0.0f, -10.0f, 0.0f))
    }

}
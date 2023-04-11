package physics

import com.bulletphysics.collision.dispatch.CollisionObject.DISABLE_DEACTIVATION
import com.bulletphysics.collision.shapes.BoxShape
import com.bulletphysics.collision.shapes.CollisionShape
import com.bulletphysics.dynamics.RigidBody
import com.bulletphysics.dynamics.RigidBodyConstructionInfo
import com.bulletphysics.linearmath.DefaultMotionState
import com.bulletphysics.linearmath.MotionState
import com.bulletphysics.linearmath.Transform
import javax.vecmath.Matrix4f
import javax.vecmath.Quat4f
import javax.vecmath.Vector3f

class PhysicsObject constructor(
    var shape: CollisionShape,
    var transform: MotionState,
    var mass: Float = 0.0f,
    var inertia: Vector3f = Vector3f(0.0f, 0.0f, 0.0f),
    var restitution: Float = 0.25f
) {

    var rb: RigidBody

    companion object {

        fun cube(scale: Vector3f, mass: Float = 0.0f,
                 inertia: Vector3f = Vector3f(0.0f, 0.0f, 0.0f),
                 restitution: Float = 0.25f) = PhysicsObject(
            BoxShape(scale),
            DefaultMotionState(Transform(Matrix4f(
                Quat4f(0.0f, 0.0f, 0.0f, 1.0f),
                Vector3f(0.0f, 0.0f, 0.0f), 1.0f
            ))),
            mass,
            inertia,
            restitution
        )

    }

    init {
        //shape.calculateLocalInertia(mass, inertia)
        var rbConstrInfo = RigidBodyConstructionInfo(mass, transform, shape)
        rbConstrInfo.restitution = restitution
        rb = RigidBody(rbConstrInfo)
        rb.forceActivationState(DISABLE_DEACTIVATION)
        Physics.dynamicsWorld.addRigidBody(rb)
    }

}
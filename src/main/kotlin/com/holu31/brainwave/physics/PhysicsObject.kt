package com.holu31.brainwave.physics

import com.bulletphysics.collision.dispatch.CollisionObject
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
    var inertia: Vector3f? = null,
    var restitution: Float = 0.25f
) {

    var rb: RigidBody

    companion object {

        fun cube(scale: Vector3f, mass: Float = 0.0f,
                 inertia: Vector3f? = null,
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
        var rbConstrInfo = RigidBodyConstructionInfo(mass, transform, shape)
        if(inertia != null) {
            shape.calculateLocalInertia(mass, inertia)
            rbConstrInfo = RigidBodyConstructionInfo(mass, transform, shape, inertia)
        }
        rbConstrInfo.restitution = restitution
        rb = RigidBody(rbConstrInfo)
        rb.forceActivationState(DISABLE_DEACTIVATION)
        Physics.dynamicsWorld.addRigidBody(rb)
    }

    fun isGround(): Boolean {
        val numPairs = Physics.dynamicsWorld.dispatcher.numManifolds

        for (i in 0 until numPairs) {
            val pair = Physics.dynamicsWorld.dispatcher.getManifoldByIndexInternal(i)
            val obj0 = pair.body0 as CollisionObject
            val obj1 = pair.body1 as CollisionObject

            val otherObj = if (obj0 == rb) obj1 else obj0

            val otherTransform = Transform()
            otherObj.getWorldTransform(otherTransform)
            val otherPos = otherTransform.origin

            val rbTransform= Transform()
            otherObj.getWorldTransform(otherTransform)

            val isTouchingGround = otherPos.y - rbTransform.origin.y <= 0.1

            if (isTouchingGround) {
                return true
            }
        }

        return false
    }

}
package render

import org.joml.*

class Camera {
	
	companion object {
		
		var current: Camera? = null
			private set
		
	}
	
	var fov: Float = 90.0f
	var near: Float = 0.01f
	var far: Float = 100.0f
	
	var pos: Vector3f = Vector3f()
	var rot: Vector3f = Vector3f()
	var scale: Vector3f = Vector3f(1.0f)
	
	val projMat: Matrix4f
		get() = Matrix4f().identity().setPerspective(fov.toRad(), 960.0f / 540.0f, near, far, false)
	
	val viewMat: Matrix4f
		get() = Matrix4f().apply {
			identity()
			rotateXYZ(-rot.toRad()) // чтобы было видно что она движется как положено (ну тут не только позиция)
			translate(-pos) // так как это камера, она должна двигаться в другую сторону от своей позиции
			scale(scale)
		}
	
	init {
		if (current == null)
			current = this
	}
	
}
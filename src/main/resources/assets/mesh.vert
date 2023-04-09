#version 330

layout (location = 0) in vec3 vPos;
layout (location = 1) in vec4 vCol;

uniform mat4 projMat, viewMat, transMat;

out vec3 fPos;
out vec4 fCol;

void main() {
    gl_Position = projMat * viewMat * transMat * vec4(vPos, 1.0f);
//	gl_Position = vec4(vPos, 1.0f);
	
	fPos = vPos;
	fCol = vCol;
}
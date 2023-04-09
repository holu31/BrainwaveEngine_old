#version 330

in vec3 fPos;
in vec4 fCol;

uniform mat4 projMat, viewMat, transMat;

out vec4 fragmentColor;

void main() {
	fragmentColor = fCol;
}
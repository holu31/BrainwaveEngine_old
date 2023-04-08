#pragma once

#include <glad/glad.h>
#define GLFW_INCLUDE_NONE
#include <GLFW/glfw3.h>

#include <iostream>

namespace Core {

    enum ObjectsType {
        OBJECT_SKY,
    };

    class Object {

        public:
            Object(ObjectsType type, float r, float g, float b);

        private:
            void SkyRender(float r, float g, float b);

    };

}
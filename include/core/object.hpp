#pragma once

#include <glad/glad.h>
#define GLFW_INCLUDE_NONE
#include <GLFW/glfw3.h>

#include <core/pipeline.hpp>

#include <iostream>

namespace Core {

    enum ObjectsType {
        OBJECT_SKY,
        SHADER,
    };

    class Object {

        public:
            Object(ObjectsType type, float r, float g, float b);
            Object(ObjectsType type, Pipeline pipeline);

        private:
            void SkyRender(float r, float g, float b);
            void ShaderRender(Pipeline pipeline);

    };

}
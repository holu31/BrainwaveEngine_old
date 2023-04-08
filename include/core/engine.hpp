#pragma once

#include <glad/glad.h>
#define GLFW_INCLUDE_NONE
#include <GLFW/glfw3.h>

#include <core/pipeline.hpp>
#include <core/object.hpp>

#include <iostream>
#include <map>
#include <stdint.h>

namespace Core {

    class Engine {

        GLFWwindow* window;

        public:

            Engine(std::string title, int width, int height);
            ~Engine();

            void _process();
            void _exit_window();
    };

};
#include <core/engine.hpp>

#include <linmath.h>

#include <stdexcept>

Core::Engine::~Engine()
{
    std::cout << "destroyed window\n";
    this->_exit_window();

    glfwDestroyWindow(this->window);
    glfwTerminate();
}

void frameBufferSizeCallback(GLFWwindow *window, 
    int width, int height)
{
    glViewport(0, 0, width, height);
}

Core::Engine::Engine(std::string title, 
    int width, int height)
{

    glfwInit();

    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5);

    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

    this->window = glfwCreateWindow(width, height, title.c_str(), nullptr, nullptr);
    if (!window) throw std::runtime_error("failed to create window");

    glfwMakeContextCurrent(this->window);
    if(!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress)){
        throw std::runtime_error("glad run failed");
    }
    glViewport(0, 0, width, height);

    glfwSetFramebufferSizeCallback(window, frameBufferSizeCallback);
    //glfwSetKeyCallback(window, input);

    while(!glfwWindowShouldClose(this->window)) {

        //this->_process();

        //glfwSwapBuffers(window); // меняем буферы местами
        glfwPollEvents(); 
    }
}
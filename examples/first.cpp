#include <iostream>
#include <core/engine.hpp>

Core::Pipeline simple {"core/shaders/simple_shader.vert",
                       "core/shaders/simple_shader.frag"};


void Core::Engine::_process(){
    Core::Object sky(OBJECT_SKY, 0.4f, 0.4f, 0.4f);
    Core::Object triangle(SHADER, simple);
}

void Core::Engine::_exit_window(){
}

auto main() -> int {
    Core::Engine engine("test", 800, 600);
    return 0;
}
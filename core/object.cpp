#include <stdexcept>
#include <core/object.hpp>


Core::Object::Object(Core::ObjectsType type,
    float r, float g, float b)
{
    if(type == Core::ObjectsType::OBJECT_SKY){
        this->SkyRender(r, g, b);
    }
    else {
        throw std::runtime_error("type error!");
    }
}

Core::Object::Object(Core::ObjectsType type, Core::Pipeline pipeline)
{
    if(type == Core::ObjectsType::SHADER){
        this->ShaderRender(pipeline);
    }
    else {
        throw std::runtime_error("type error!");
    }
}

void Core::Object::SkyRender(float r, float g, float b){
    glClearColor(0.4f, 0.4f, 0.4f, 0.0f);
    glClear(GL_COLOR_BUFFER_BIT);
}

void Core::Object::ShaderRender(Core::Pipeline pipeline){
    unsigned int vao;

    glGenVertexArrays(1, &vao);

    glGenVertexArrays(1, &vao);
    int vertexShader = glCreateShader(GL_VERTEX_SHADER);
    auto vertCode = pipeline.vertCode.data();
    glShaderSource(vertexShader, 1, &vertCode, NULL);
    glCompileShader(vertexShader);

    int success;
    char infoLog[512];
    glGetShaderiv(vertexShader, GL_COMPILE_STATUS, &success);
    if(!success){
        glGetShaderInfoLog(vertexShader, 512, NULL, infoLog);
        std::cout << infoLog << std::endl;
    }


    int fragShader = glCreateShader(GL_FRAGMENT_SHADER);
    auto fragCode = pipeline.fragCode.data();
    glShaderSource(fragShader, 1, &fragCode, NULL);
    glCompileShader(fragShader);

    glGetShaderiv(fragShader, GL_COMPILE_STATUS, &success);
    if(!success){
        glGetShaderInfoLog(fragShader, 512, NULL, infoLog);
        std::cout << infoLog << std::endl;
    }

    int shaderProgram = glCreateProgram();
    glAttachShader(shaderProgram, vertexShader);
    glAttachShader(shaderProgram, fragShader);
    glLinkProgram(shaderProgram);

    glGetProgramiv(shaderProgram, GL_LINK_STATUS, &success);
    if(!success){
        glGetProgramInfoLog(shaderProgram, 512, NULL, infoLog);
        std::cout << infoLog << std::endl;
    }
    glDeleteShader(vertexShader);
    glDeleteShader(fragShader);

    glUseProgram(shaderProgram);
    glBindVertexArray(vao);
    glDrawArrays(GL_TRIANGLES, 0, 3);
}
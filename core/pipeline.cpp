#include <core/pipeline.hpp>

#include <fstream>
#include <stdexcept>
#include <iostream>

Core::Pipeline::Pipeline(const std::string &vertFilepath,
    const std::string &fragFilepath)
{
    vertCode = this->readFile(vertFilepath);
    fragCode = this->readFile(fragFilepath);

    std::cout << "vert size - " << vertCode.size() << \
        "\nfrag size - " << fragCode.size() << std::endl;
}

std::vector<char> Core::Pipeline::readFile(const std::string& filepath)
{
    std::ifstream file(filepath, std::ios::ate | std::ios::binary);

    if (!file.is_open()){
        throw std::runtime_error("failed to open file: " + filepath);
    }

    size_t fileSize = static_cast<size_t>(file.tellg());
    std::vector<char> buffer(fileSize);

    file.seekg(0);
    file.read(buffer.data(), fileSize);

    file.close();
    return buffer;
}
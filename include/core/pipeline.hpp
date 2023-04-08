#pragma once

#include <string>
#include <vector>

namespace Core {

    class Pipeline {

        public:
            std::vector<char> vertCode;
            std::vector<char> fragCode;

            Pipeline(const std::string &vertFilepath,
                    const std::string &fragFilepath);
            ~Pipeline() {}

            //Pipeline(const Pipeline&) = delete;

        private:

            static std::vector<char> readFile(const std::string& filepath);
    };

};

CC=g++
OBJECTS=bin/engine.o bin/pipeline.o bin/object.o
CFLAGS = -fPIC -O2 -I include/

LDFLAGS = -lglfw -lvulkan -ldl -lpthread -lX11 -lXxf86vm -lXrandr -lXi

.PHONY: all
all: build.cpp

build.cpp:
	$(CC) $(CFLAGS) -c -o bin/engine.o core/engine.cpp
	$(CC) $(CFLAGS) -c -o bin/pipeline.o core/pipeline.cpp
	$(CC) $(CFLAGS) -c -o bin/object.o core/object.cpp
	
	$(CC) $(CFLAGS) -shared -o engine.so $(OBJECTS) glad/glad.c $(LDFLAGS)

build.game:

	mkdir -p build/$(build)
	mkdir -p build/$(build)/core
	mkdir -p build/$(build)/core/shaders

	$(CC) $(CFLAGS) -o build/$(build)/$(build) $(file) ./engine.so

	cp core/shaders/simple_shader.vert build/$(build)/core/shaders/simple_shader.vert
	cp core/shaders/simple_shader.frag build/$(build)/core/shaders/simple_shader.frag
	cp engine.so build/$(build)/
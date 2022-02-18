package ru.fdoke.ffm.opengl;

import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.SegmentAllocator;
import jdk.incubator.foreign.ValueLayout;
import ru.fdoke.ffm.opengl.api.glfw.GlfwApi;
import ru.fdoke.ffm.opengl.api.opengl.IntReference;
import ru.fdoke.ffm.opengl.api.opengl.OpenglApi;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static ru.fdoke.ffm.opengl.api.glfw.GlfwApi.GLFW_CONTEXT_VERSION_MAJOR;
import static ru.fdoke.ffm.opengl.api.glfw.GlfwApi.GLFW_CONTEXT_VERSION_MINOR;
import static ru.fdoke.ffm.opengl.api.opengl.OpenglConstants.*;

public class App {
    public final GlfwApi glfwApi = new GlfwApi();
    public final OpenglApi openglApi = new OpenglApi();

    public static void main(String[] args) {
        System.loadLibrary("opengl32");
        String dllPath = args.length == 0 ? "dll/" : args[0];
        for (var file : Objects.requireNonNull(new File(dllPath).listFiles())) {
            if (file.getName().endsWith(".dll")) {
                System.load(file.getAbsolutePath());
                System.out.println("Loaded: " + file.getAbsolutePath());
            }
        }

        new App().run();
    }

    private void run() {
        if (!glfwApi.glfwInit()) {
            throw new IllegalStateException("Could not init glfw");
        }
        System.out.println("Glfw init succeed");

        try {
            glfwApi.glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
            glfwApi.glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5);

            MemoryAddress window = glfwApi.glfwCreateWindow(640, 480, "Hello World", MemoryAddress.NULL, MemoryAddress.NULL);
            if (window.equals(MemoryAddress.NULL)) {
                throw new IllegalStateException("Could not init window");
            }
            System.out.println("Glfw window created");

            glfwApi.glfwMakeContextCurrent(window);

            System.out.println("Opengl version: " + openglApi.glGetString(GL_VERSION));
            System.out.println("Opengl renderer: " + openglApi.glGetString(GL_RENDERER));
            System.out.println("Opengl vendor: " + openglApi.glGetString(GL_VENDOR));

            MemorySegment vertices = createVertices();

            IntReference vaoRef = new IntReference();
            openglApi.glGenVertexArrays(1, vaoRef);
            int vao = vaoRef.getValue();
            openglApi.glBindVertexArray(vao);
            System.out.println("VAO bound: " + vao);

            IntReference vboRef = new IntReference();
            openglApi.glGenBuffers(1, vboRef);
            int vbo = vboRef.getValue();
            System.out.println("VBO bound: " + vbo);

            openglApi.glBindBuffer(GL_ARRAY_BUFFER, vbo);
            openglApi.glBufferData(GL_ARRAY_BUFFER, vertices.byteSize(), vertices, GL_STATIC_DRAW);

            openglApi.glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * 4, MemoryAddress.NULL);
            openglApi.glEnableVertexAttribArray(0);

            int shaderProgram = createShaderProgram();

            while (glfwApi.glfwWindowShouldClose(window) == 0) {
                openglApi.glClear(GL_COLOR_BUFFER_BIT);
                openglApi.glUseProgram(shaderProgram);
                openglApi.glBindVertexArray(vao);
                openglApi.glDrawArrays(GL_TRIANGLES, 0, 3);

                glfwApi.glfwSwapBuffers(window);
                glfwApi.glfwPollEvents();
            }
        } finally {
            glfwApi.glfwTerminate();
        }
    }

    private int createShaderProgram() {
        int vertexShader = loadShader("vertex.shader", GL_VERTEX_SHADER);
        int fragmentShader = loadShader("fragment.shader", GL_FRAGMENT_SHADER);

        int shaderProgram = openglApi.glCreateProgram();
        openglApi.glAttachShader(shaderProgram, vertexShader);
        openglApi.glAttachShader(shaderProgram, fragmentShader);
        openglApi.glLinkProgram(shaderProgram);
        IntReference shaderProgramLinkSuccess = new IntReference();
        openglApi.glGetProgramiv(shaderProgram, GL_LINK_STATUS, shaderProgramLinkSuccess);
        if (shaderProgramLinkSuccess.getValue() == 0) {
            MemorySegment infoLog = SegmentAllocator.implicitAllocator().allocate(512);
            infoLog.set(ValueLayout.JAVA_BYTE, 0, (byte) 10);
            openglApi.glGetProgramInfoLog(shaderProgram, 512, MemoryAddress.NULL, infoLog);
            throw new IllegalStateException("Could not link shader program: " + infoLog.getUtf8String(0));
        }

        System.out.println("Shader program linked as: " + shaderProgram);
        return shaderProgram;
    }

    private int loadShader(String shaderResource, int shaderType) {
        int shaderNum = openglApi.glCreateShader(shaderType);
        try {
            String shaderSource = new String(getClass().getClassLoader().getResourceAsStream(shaderResource).readAllBytes(), StandardCharsets.UTF_8);
            openglApi.glShaderSource(shaderNum, 1, shaderSource, MemoryAddress.NULL);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not load shader: " + shaderResource);
        }

        openglApi.glCompileShader(shaderNum);

        IntReference shaderCompileSuccess = new IntReference();
        openglApi.glGetShaderiv(shaderNum, GL_COMPILE_STATUS, shaderCompileSuccess);
        if (shaderCompileSuccess.getValue() == 0) {
            throw new IllegalStateException("Could not compile shader: " + shaderResource);
        }

        System.out.println(shaderResource + " loaded as: " + shaderNum);
        return shaderNum;
    }

    private MemorySegment createVertices() {
        float[] vertices = {
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.0f, 0.5f, 0.0f
        };
        return SegmentAllocator.implicitAllocator().allocateArray(ValueLayout.JAVA_FLOAT, vertices);
    }
}

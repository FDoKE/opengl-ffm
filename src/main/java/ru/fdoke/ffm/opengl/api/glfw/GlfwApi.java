package ru.fdoke.ffm.opengl.api.glfw;

import jdk.incubator.foreign.*;

import java.lang.invoke.MethodHandle;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GlfwApi {
    private final CLinker cLinker = CLinker.systemCLinker();

    public static final int GLFW_CONTEXT_VERSION_MAJOR = 0x00022002;
    public static final int GLFW_CONTEXT_VERSION_MINOR = 0x00022003;

    private final Map<String, MethodHandle> methodHandles = new HashMap<>();

    public boolean glfwInit() {
        try {
            MethodHandle methodHandle = getMethodHandle("glfwInit", FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
            return (boolean) methodHandle.invoke();
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glfwTerminate() {
        try {
            MethodHandle methodHandle = getMethodHandle("glfwTerminate", FunctionDescriptor.ofVoid());
            methodHandle.invoke();
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public MemoryAddress glfwCreateWindow(int width, int height, String title, MemoryAddress monitor, MemoryAddress share) {
        try {
            MethodHandle methodHandle = getMethodHandle("glfwCreateWindow",
                    FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
            MemorySegment str = SegmentAllocator.implicitAllocator().allocateUtf8String(title);
            return (MemoryAddress) methodHandle.invoke(width, height, str, monitor, share);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glfwMakeContextCurrent(MemoryAddress window) {
        try {
            MethodHandle methodHandle = getMethodHandle("glfwMakeContextCurrent", FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));
            methodHandle.invoke(window);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public int glfwWindowShouldClose(MemoryAddress window) {
        try {
            MethodHandle methodHandle = getMethodHandle("glfwWindowShouldClose",
                    FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
            return (int) methodHandle.invoke(window);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glfwSwapBuffers(MemoryAddress window) {
        try {
            MethodHandle methodHandle = getMethodHandle("glfwSwapBuffers", FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));
            methodHandle.invoke(window);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glfwPollEvents() {
        try {
            MethodHandle methodHandle = getMethodHandle("glfwPollEvents", FunctionDescriptor.ofVoid());
            methodHandle.invoke();
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glfwWindowHint(int hint, int value) {
        try {
            MethodHandle methodHandle = getMethodHandle("glfwWindowHint", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
            methodHandle.invoke(hint, value);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    private MethodHandle getMethodHandle(String methodName, FunctionDescriptor descriptor) {
        MethodHandle cachedMethodHandle = methodHandles.get(methodName);
        if (cachedMethodHandle != null) {
            return cachedMethodHandle;
        }

        Optional<NativeSymbol> methodAddress = SymbolLookup.loaderLookup().lookup(methodName);
        if (methodAddress.isEmpty()) {
            throw new IllegalArgumentException("Could not find method: " + methodName);
        }

        MethodHandle methodHandle = cLinker.downcallHandle(methodAddress.get(), descriptor);
        methodHandles.put(methodName, methodHandle);
        return methodHandle;
    }
}

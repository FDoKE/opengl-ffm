package ru.fdoke.ffm.opengl.api.opengl;

import jdk.incubator.foreign.*;

import java.lang.invoke.MethodHandle;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OpenglApi {
    private final CLinker cLinker = CLinker.systemCLinker();

    private final Map<String, MethodHandle> methodHandles = new HashMap<>();

    public void glClear(int mask) {
        try {
            MethodHandle methodHandle = getMethodHandle("glClear", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
            methodHandle.invoke(mask);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public String glGetString(int name) {
        try {
            MethodHandle methodHandle = getMethodHandle("glGetString", FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
            MemoryAddress invoke = (MemoryAddress) methodHandle.invoke(name);
            return invoke.getUtf8String(0);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glGenBuffers(int target, IntReference vbo) {
        try {
            MethodHandle methodHandle = getMethodHandle("glGenBuffers", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
            methodHandle.invoke(target, vbo.getAddress());
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glBindBuffer(int target, int vbo) {
        try {
            MethodHandle methodHandle = getMethodHandle("glBindBuffer", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
            methodHandle.invoke(target, vbo);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glBufferData(int target, long size, MemorySegment data, int usage) {
        try {
            MethodHandle methodHandle = getMethodHandle("glBufferData", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
            methodHandle.invoke(target, size, data, usage);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public int glCreateShader(int shaderType) {
        try {
            MethodHandle methodHandle = getMethodHandle("glCreateShader", FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
            return (int) methodHandle.invoke(shaderType);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glShaderSource(int shader, int count, String source, MemoryAddress length) {
        try {
            MethodHandle methodHandle = getMethodHandle("glShaderSource", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
            MemorySegment arrayOfSourcesMemorySegment = SegmentAllocator.implicitAllocator().allocateArray(ValueLayout.ADDRESS, 1);
            MemorySegment sourceMemorySegment = SegmentAllocator.implicitAllocator().allocateUtf8String(source);
            arrayOfSourcesMemorySegment.set(ValueLayout.ADDRESS, 0, sourceMemorySegment);
            methodHandle.invoke(shader, count, arrayOfSourcesMemorySegment, length);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glCompileShader(int shader) {
        try {
            MethodHandle methodHandle = getMethodHandle("glCompileShader", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
            methodHandle.invoke(shader);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glGetShaderiv(int shader, int pname, IntReference params) {
        try {
            MethodHandle methodHandle = getMethodHandle("glGetShaderiv", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
            methodHandle.invoke(shader, pname, params.getAddress());
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public int glCreateProgram() {
        try {
            MethodHandle methodHandle = getMethodHandle("glCreateProgram", FunctionDescriptor.of(ValueLayout.JAVA_INT));
            return (int) methodHandle.invoke();
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glAttachShader(int program, int shader) {
        try {
            MethodHandle methodHandle = getMethodHandle("glAttachShader", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
            methodHandle.invoke(program, shader);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glLinkProgram(int program) {
        try {
            MethodHandle methodHandle = getMethodHandle("glLinkProgram", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
            methodHandle.invoke(program);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glGetProgramiv(int program, int pname, IntReference params) {
        try {
            MethodHandle methodHandle = getMethodHandle("glGetProgramiv", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
            methodHandle.invoke(program, pname, params.getAddress());
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glGetProgramInfoLog(int program, int maxLength, MemoryAddress length, MemorySegment infoLog) {
        try {
            MethodHandle methodHandle = getMethodHandle("glGetProgramInfoLog", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
            methodHandle.invoke(program, maxLength, length, infoLog);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glUseProgram(int program) {
        try {
            MethodHandle methodHandle = getMethodHandle("glUseProgram", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
            methodHandle.invoke(program);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glVertexAttribPointer(int index, int size, int type, int normalized, int stride, MemoryAddress pointer) {
        try {
            MethodHandle methodHandle = getMethodHandle("glVertexAttribPointer", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
            methodHandle.invoke(index, size, type, normalized, stride, pointer);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glEnableVertexAttribArray(int index) {
        try {
            MethodHandle methodHandle = getMethodHandle("glEnableVertexAttribArray", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
            methodHandle.invoke(index);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glGenVertexArrays(int n, IntReference arrays) {
        try {
            MethodHandle methodHandle = getMethodHandle("glGenVertexArrays", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
            methodHandle.invoke(n, arrays.getAddress());
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glBindVertexArray(int vao) {
        try {
            MethodHandle methodHandle = getMethodHandle("glBindVertexArray", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
            methodHandle.invoke(vao);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glDrawArrays(int mode, int first, int count) {
        try {
            MethodHandle methodHandle = getMethodHandle("glDrawArrays", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
            methodHandle.invoke(mode, first, count);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    private MethodHandle getMethodHandle(String methodName, FunctionDescriptor descriptor) throws Throwable {
        MethodHandle cachedHandle = methodHandles.get(methodName);
        if (cachedHandle != null) {
            return cachedHandle;
        }

        // trying to find method handle by loaderLookup
        Optional<NativeSymbol> directMethodAddress = SymbolLookup.loaderLookup().lookup(methodName);
        if (directMethodAddress.isPresent()) {
            MethodHandle methodHandle = cLinker.downcallHandle(directMethodAddress.get(), descriptor);
            methodHandles.put(methodName, methodHandle);
            return methodHandle;
        }

        // Some opengl functions are not in lookup table (extensions) so we need to call wglGetProcAddress to get pointer to needed function
        MethodHandle extensionMethodHandle = getMethodHandle("wglGetProcAddress", FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
        MemorySegment extensionMethodNameMemorySegment = SegmentAllocator.implicitAllocator().allocateUtf8String(methodName);
        MemoryAddress address = (MemoryAddress) extensionMethodHandle.invoke(extensionMethodNameMemorySegment);

        if (address.equals(MemoryAddress.NULL)) {
            throw new IllegalArgumentException("Could not find method: " + methodName);
        }


        // making call to retrieved function address
        NativeSymbol methodAddress = NativeSymbol.ofAddress(methodName, address, ResourceScope.globalScope());
        MethodHandle methodHandle = cLinker.downcallHandle(methodAddress, descriptor);
        methodHandles.put(methodName, methodHandle);
        return methodHandle;
    }
}

package ru.fdoke.ffm.opengl.api.opengl;

import jdk.incubator.foreign.*;

import java.lang.invoke.MethodHandle;
import java.util.Optional;

public class OpenglApi {
    private final CLinker cLinker = CLinker.systemCLinker();

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
            MethodHandle methodHandle = getExtensionMethodHandle("glGenBuffers", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
            methodHandle.invoke(target, vbo.getAddress());
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glBindBuffer(int target, int vbo) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glBindBuffer", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
            methodHandle.invoke(target, vbo);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glBufferData(int target, long size, MemorySegment data, int usage) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glBufferData", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
            methodHandle.invoke(target, size, data, usage);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public int glCreateShader(int shaderType) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glCreateShader", FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
            return (int) methodHandle.invoke(shaderType);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glShaderSource(int shader, int count, String source, MemoryAddress length) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glShaderSource", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
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
            MethodHandle methodHandle = getExtensionMethodHandle("glCompileShader", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
            methodHandle.invoke(shader);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glGetShaderiv(int shader, int pname, IntReference params) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glGetShaderiv", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
            methodHandle.invoke(shader, pname, params.getAddress());
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public int glCreateProgram() {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glCreateProgram", FunctionDescriptor.of(ValueLayout.JAVA_INT));
            return (int) methodHandle.invoke();
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glAttachShader(int program, int shader) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glAttachShader", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
            methodHandle.invoke(program, shader);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glLinkProgram(int program) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glLinkProgram", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
            methodHandle.invoke(program);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glGetProgramiv(int program, int pname, IntReference params) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glGetProgramiv", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
            methodHandle.invoke(program, pname, params.getAddress());
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glGetProgramInfoLog(int program, int maxLength, MemoryAddress length, MemorySegment infoLog) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glGetProgramInfoLog", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
            methodHandle.invoke(program, maxLength, length, infoLog);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glUseProgram(int program) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glUseProgram", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
            methodHandle.invoke(program);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glVertexAttribPointer(int index, int size, int type, int normalized, int stride, MemoryAddress pointer) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glVertexAttribPointer", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
            methodHandle.invoke(index, size, type, normalized, stride, pointer);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glEnableVertexAttribArray(int index) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glEnableVertexAttribArray", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
            methodHandle.invoke(index);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glGenVertexArrays(int n, IntReference arrays) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glGenVertexArrays", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
            methodHandle.invoke(n, arrays.getAddress());
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glBindVertexArray(int vao) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glBindVertexArray", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
            methodHandle.invoke(vao);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    public void glDrawArrays(int mode, int first, int count) {
        try {
            MethodHandle methodHandle = getExtensionMethodHandle("glDrawArrays", FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
            methodHandle.invoke(mode, first, count);
        } catch (Throwable e) {
            throw new IllegalStateException("Invocation failed", e);
        }
    }

    private MethodHandle getExtensionMethodHandle(String funcName, FunctionDescriptor descriptor) throws Throwable {
        // Some opengl functions are not in lookup table (extensions) so we need to call wglGetProcAddress to pointer to that extension function
        MethodHandle methodHandle = getMethodHandle("wglGetProcAddress", FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
        MemorySegment wglGetProcAddress = SegmentAllocator.implicitAllocator().allocateUtf8String(funcName);
        MemoryAddress address = (MemoryAddress) methodHandle.invoke(wglGetProcAddress);

        // making call to retrieved function address
        NativeSymbol glGenBuffersSymbol = NativeSymbol.ofAddress(funcName, address, ResourceScope.globalScope());
        return cLinker.downcallHandle(glGenBuffersSymbol, descriptor);
    }

    private MethodHandle getMethodHandle(String funcName, FunctionDescriptor function) {
        Optional<NativeSymbol> testMethod = SymbolLookup.loaderLookup().lookup(funcName);
        return cLinker.downcallHandle(testMethod.get(), function);
    }
}

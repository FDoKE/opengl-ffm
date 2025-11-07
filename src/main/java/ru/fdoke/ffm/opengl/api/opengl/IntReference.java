package ru.fdoke.ffm.opengl.api.opengl;

import ru.fdoke.ffm.opengl.api.calling.Memory;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class IntReference {
    private final MemorySegment memorySegment;

    public IntReference() {
        memorySegment = Memory.ALLOCATOR.allocate(ValueLayout.JAVA_INT, 1);
    }

    public MemorySegment getMemorySegment() {
        return memorySegment;
    }

    public int getValue() {
        return memorySegment.get(ValueLayout.JAVA_INT, 0);
    }
}

package ru.fdoke.ffm.opengl.api.opengl;

import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.SegmentAllocator;
import jdk.incubator.foreign.ValueLayout;

public class IntReference {
    private MemoryAddress address;

    public IntReference() {
        address = SegmentAllocator.implicitAllocator().allocate(ValueLayout.JAVA_INT, 0).address();
    }

    public void setValue(int value) {
        this.address.set(ValueLayout.JAVA_INT, 0, value);
    }

    public MemoryAddress getAddress() {
        return address;
    }

    public int getValue() {
        return address.get(ValueLayout.JAVA_INT, 0);
    }
}

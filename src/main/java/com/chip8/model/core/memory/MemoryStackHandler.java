package com.chip8.model.core.memory;

import com.chip8.api.core.memory.Memory;
import com.chip8.api.core.memory.MemoryStack;
import com.chip8.api.core.register.StackPointer;
import org.springframework.stereotype.Component;

@Component
public class MemoryStackHandler implements MemoryStack {

    private final StackPointer sp;

    private final Memory memoryStack;

    public MemoryStackHandler(final StackPointer sp, final Memory memoryStack) {
        this.sp = sp;
        this.memoryStack = memoryStack;
    }

    @Override
    public void push(final Integer data) {
        this.memoryStack.write(this.sp.get(), data);
        this.sp.increase();
    }

    @Override
    public Integer pop() {
        this.sp.decrement();
        return this.memoryStack.read(this.sp.get());
    }

}

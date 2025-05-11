package com.chip8.model.core;

import com.chip8.api.core.Cpu;
import com.chip8.api.core.instruction.Instruction;
import com.chip8.api.core.memory.Memory;
import com.chip8.api.core.register.ProgramCounter;
import com.chip8.api.core.register.TimerRegister;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HexFormat;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class CpuHandler implements Cpu {

    private final List<Instruction> instructions;

    private final ProgramCounter pc;

    private final Memory memoryRam;

    private final TimerRegister delayTimerRegister;

    private ScheduledExecutorService executor;

    public CpuHandler(final List<Instruction> instructions, final ProgramCounter pc, final Memory memoryRam, final TimerRegister delayTimerRegister) {
        this.instructions = instructions;
        this.pc = pc;
        this.memoryRam = memoryRam;
        this.delayTimerRegister = delayTimerRegister;
    }

    @Override
    public void run() throws InterruptedException {
        if (this.executor == null) {
            this.executor = Executors.newSingleThreadScheduledExecutor();
        }

        this.executor.scheduleAtFixedRate(() -> {
            CpuHandler.this.executeOpcode(CpuHandler.this.nextOpcode());
            if (CpuHandler.this.delayTimerRegister.get() > 0) {
                CpuHandler.this.delayTimerRegister.set(0);
            }
        }, 0, 1_000_000 / 500, TimeUnit.MICROSECONDS);
    }

    private String nextOpcode() {
        final Integer pc = this.pc.get();
        this.pc.next();
        return Arrays.stream(this.memoryRam.read(pc, pc + 1))
                .map(integer -> HexFormat.of().toHexDigits(integer))
                .map(s -> s.substring(s.length() - 2).toUpperCase())
                .collect(Collectors.joining());
    }

    private void executeOpcode(final String opcode) {
        this.instructions.stream()
                .filter(instruction -> instruction.isExecutable(opcode))
                .findFirst()
                .ifPresent(instruction -> instruction.run(opcode));
    }

    @Override
    public void stop() {
        this.executor.shutdownNow();
    }
}

package com.ondrejkoula.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OutOfTimeWindowException extends RuntimeException {

    private final LocalDateTime time;

    private final When when;

    private final LocalDateTime windowTime;

     private OutOfTimeWindowException(LocalDateTime time, When when, LocalDateTime windowTime) {
        this.time = time;
        this.when = when;
        this.windowTime = windowTime;
    }

    public static OutOfTimeWindowException before(LocalDateTime time, LocalDateTime windowTime) {
         return new OutOfTimeWindowException(time, When.BEFORE, windowTime);
    }

    public static OutOfTimeWindowException after(LocalDateTime time, LocalDateTime windowTime) {
        return new OutOfTimeWindowException(time, When.AFTER, windowTime);
    }

    public enum When {
        BEFORE, AFTER
    }
}

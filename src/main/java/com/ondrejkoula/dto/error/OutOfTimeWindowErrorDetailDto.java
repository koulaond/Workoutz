package com.ondrejkoula.dto.error;

import com.ondrejkoula.exception.OutOfTimeWindowException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OutOfTimeWindowErrorDetailDto extends ErrorDetailDto {


    private final LocalDateTime time;

    private final OutOfTimeWindowException.When when;

    private final LocalDateTime windowTime;

    @Builder
    public OutOfTimeWindowErrorDetailDto(String errorMessage, String messageCode, LocalDateTime time,
                                         OutOfTimeWindowException.When when, LocalDateTime windowTime) {
        super(errorMessage, messageCode);
        this.time = time;
        this.when = when;
        this.windowTime = windowTime;
    }
}

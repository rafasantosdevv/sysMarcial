package com.rafasantosdevv.sysMarcial.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime timeStamp,
        int status,
        String erro,
        List<String> mensagens
) {
}

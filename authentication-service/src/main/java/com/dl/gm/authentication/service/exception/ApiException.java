package com.dl.gm.authentication.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiException extends RuntimeException {

    protected final List<String> fields;
    protected final String key;

    public ApiException(String message) {
        super(message);
        this.key = null;
        this.fields = Collections.emptyList();
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.key = null;
        this.fields = Collections.emptyList();
    }

    public ApiException(String message, List<String> fields, String key) {
        super(message);
        this.fields = fields == null ? Collections.emptyList() : List.copyOf(fields);
        this.key = key;
    }
}

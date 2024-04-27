package com.example.assessment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class Response<T> {
    protected int statusCode;
    protected HttpStatus status;
    protected String message;
    protected T data;

    @Override
    public String toString() {
        return "Response{" +
                ", statusCode=" + statusCode +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

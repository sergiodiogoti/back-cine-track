package com.catalogo.filmes.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseError {

    private final String status;
    private String error;
    private String message;
    private String path;
    private final LocalDateTime timestamp;


    private List<InvalidFields> invalidFields = null;

    public ApiResponseError(HttpStatus status, String path){
        this.timestamp = LocalDateTime.now();
        this.status = String.valueOf(status.value());
        this.path = path;
    }

    public void addFieldsError(String fieldName, String message){
        if(invalidFields == null){
            invalidFields = new ArrayList<>();
        }
        invalidFields.add(new InvalidFields(fieldName, message));
    }

    public String getStatus() {
        return status;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<InvalidFields> getInvalidFields() {
        return invalidFields;
    }

    public void setInvalidFields(List<InvalidFields> invalidFields) {
        this.invalidFields = invalidFields;
    }

    public static class InvalidFields {

        private String fieldName;
        private String message;

        public InvalidFields(String fieldName, String message){
            this.fieldName = fieldName;
            this.message = message;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}

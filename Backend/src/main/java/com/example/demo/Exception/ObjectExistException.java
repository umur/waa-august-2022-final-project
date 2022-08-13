package com.example.demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectExistException extends RuntimeException {
    private String errorMessage;
}

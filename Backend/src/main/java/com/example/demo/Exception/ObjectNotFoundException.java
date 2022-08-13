package com.example.demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectNotFoundException extends RuntimeException {
    private String errorMessage;
}

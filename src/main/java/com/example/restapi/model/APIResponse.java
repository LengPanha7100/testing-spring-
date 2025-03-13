package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIResponse<T> {
    private String message;
    private String status;
    private T payload;
    private LocalDateTime timestamp;

}

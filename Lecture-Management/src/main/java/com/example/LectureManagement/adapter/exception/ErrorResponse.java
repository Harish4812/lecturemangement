package com.example.LectureManagement.adapter.exception;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    int code;
    String message;
    String error;
    String suggestedAction;
}

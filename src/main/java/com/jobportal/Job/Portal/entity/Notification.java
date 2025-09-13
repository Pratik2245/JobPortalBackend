package com.jobportal.Job.Portal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private Long id;
    private Long userId;
    private String message;
    private String action;
    private String route;
    private LocalDateTime timeStamp;
}

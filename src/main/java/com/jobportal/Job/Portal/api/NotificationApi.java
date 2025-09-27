package com.jobportal.Job.Portal.api;

import com.jobportal.Job.Portal.dto.NotificationDTO;
import com.jobportal.Job.Portal.entity.Notification;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/notifications")
@Validated
public class NotificationApi {
    @Autowired
    NotificationService notificationService;
    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long userId){
        return new ResponseEntity<>(notificationService.getUnReadNotifications(userId), HttpStatus.OK);
    }
    @PutMapping("/read/{notificationId}")
    public ResponseEntity<Void> markAsRead(@PathVariable Long notificationId) throws JobPortalException {
        notificationService.markNotificationAsRead(notificationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

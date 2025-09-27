package com.jobportal.Job.Portal.service;


import com.jobportal.Job.Portal.dto.NotificationDTO;
import com.jobportal.Job.Portal.entity.Notification;
import com.jobportal.Job.Portal.exception.JobPortalException;

import java.util.List;

public interface NotificationService {
    public void sendNotification(NotificationDTO notification) throws JobPortalException;
    public List<Notification> getUnReadNotifications(Long userId);
    void markNotificationAsRead(Long notificationId) throws JobPortalException;
}

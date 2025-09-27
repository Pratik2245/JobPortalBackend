package com.jobportal.Job.Portal.service;

import com.jobportal.Job.Portal.dto.NotificationDTO;
import com.jobportal.Job.Portal.dto.NotificationStatus;
import com.jobportal.Job.Portal.entity.Notification;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.repository.NotificationRepository;
import com.jobportal.Job.Portal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationDTO notificationDto) throws JobPortalException {
       notificationDto.setId(Utilities.generateSequence("notification"));
       notificationDto.setStatus(NotificationStatus.UNREAD);
       notificationDto.setTimeStamp(LocalDateTime.now());
       notificationRepository.save(notificationDto.toEntity());
    }

    @Override
    public List<Notification> getUnReadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    @Override
    public void markNotificationAsRead(Long notificationId) throws JobPortalException {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new JobPortalException("Notification not found with ID: " + notificationId));
        notification.setStatus(NotificationStatus.READ);
        notificationRepository.save(notification);
    }
}

package com.jobportal.Job.Portal.service;

import com.jobportal.Job.Portal.dto.ProfileDTO;
import com.jobportal.Job.Portal.entity.Profile;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.repository.ProfileRepository;
import com.jobportal.Job.Portal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Long createProfile(String email) throws JobPortalException {
        Profile profile=new Profile();
        profile.setId(Utilities.generateSequence("profile"));
        profile.setEmail(email);
        profile.setCertifications(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setSkills(new ArrayList<>());
        profileRepository.save(profile);
        return profile.getId();
    }

    @Override
    public ProfileDTO getProfileId(Long id) throws  JobPortalException {
        return profileRepository.findById(id).orElseThrow(()->new JobPortalException("PROFILE NOT FOUND")).toDTO();
    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException {
        profileRepository.findById(profileDTO.getId()).orElseThrow(()->new JobPortalException("PROFILE NOT FOUND"));
        profileRepository.save(profileDTO.toEntity());
        return profileDTO;
    }
}

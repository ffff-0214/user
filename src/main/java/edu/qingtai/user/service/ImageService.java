package edu.qingtai.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String saveInferImage(MultipartFile file);

    String saveInterviewImage(MultipartFile file);
}

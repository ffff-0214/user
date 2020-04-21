package edu.qingtai.user.service;

import edu.qingtai.user.domain.Image;
import edu.qingtai.user.mapper.ImageMapper;
import edu.qingtai.user.util.ConstData;
import edu.qingtai.user.util.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService{
    private ImageMapper imageMapper;

    @Autowired
    public ImageServiceImpl(final ImageMapper imageMapper){
        this.imageMapper = imageMapper;
    }

    @Override
    public String saveInferImage(MultipartFile file){
        Image image = new Image();
        String inferImage = UploadImage.uploadImages(ConstData.inferLocation, file);
        image.setPicture(inferImage);
        imageMapper.insert(image);
        return inferImage;
    }

    @Override
    public String saveInterviewImage(MultipartFile file){
        Image image = new Image();
        String interviewImage = UploadImage.uploadImages(ConstData.interviewLocation, file);
        image.setPicture(interviewImage);
        imageMapper.insert(image);
        return interviewImage;
    }

}

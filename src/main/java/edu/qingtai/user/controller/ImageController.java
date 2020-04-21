package edu.qingtai.user.controller;

import edu.qingtai.user.service.ImageService;
import edu.qingtai.user.util.ConstData;
import edu.qingtai.user.util.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/image")
public class ImageController {
    private ImageService imageService;

    @Autowired
    public ImageController(final ImageService imageService){
        this.imageService = imageService;
    }

    @PostMapping(value = "/infer")
    public String saveInferImage(@RequestParam("file") MultipartFile file){
        return imageService.saveInferImage(file);
    }

    @PostMapping(value = "/interview")
    public String saveInterviewImage(@RequestParam("file") MultipartFile file){
        return imageService.saveInterviewImage(file);
    }
}

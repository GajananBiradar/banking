package com.houseclay.zebra.service;

import com.houseclay.zebra.model.deletelater.Image;
import com.houseclay.zebra.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImagesService {
    @Autowired
    ImageRepository imagesRepository;

    /**
     * Deprecated
     * @param file
     * @throws IOException
     */
    public void save(MultipartFile file) throws IOException {
        Image image = Image.builder().
                name(StringUtils.cleanPath(file.getOriginalFilename()))
                .contentType(file.getContentType()).image_data(file.getBytes())
                .size(file.getSize()).build();

        imagesRepository.save(image);
    }
}

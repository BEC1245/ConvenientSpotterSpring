package com.convenient.store.product.common.utill;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnailator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUploader {

    @Value("${com.convenient.file.upload}")
    String loc;

    public List<String> uploadFile(List<MultipartFile> multipartFiles, int height, int width){

        if(multipartFiles == null || multipartFiles.size() < 0){
            return null;
        }

        List<String> fileNames = new ArrayList<>();

        for(MultipartFile ele : multipartFiles){

            String fileName = ele.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            String realName = uuid+"_"+fileName;

            File upload = new File(loc, realName);

            try (InputStream in = ele.getInputStream();
                 OutputStream out = new FileOutputStream(upload);
            ) {
                FileCopyUtils.copy(in, out);

                File thumbnail = new File(loc, "s_"+realName);
                Thumbnailator.createThumbnail(upload, thumbnail, height, width);

                fileNames.add(realName);
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        return fileNames;
    }

    public List<String> uploadFile(List<MultipartFile> multipartFiles){

        if(multipartFiles == null || multipartFiles.size() < 0){
            return null;
        }

        List<String> fileNames = new ArrayList<>();

        for(MultipartFile ele : multipartFiles){

            String fileName = ele.getName();
            String uuid = UUID.randomUUID().toString();

            String realName = uuid+"_"+fileName;

            File upload = new File(loc, realName);

            try (InputStream in = ele.getInputStream();
                 OutputStream out = new FileOutputStream(upload);
            ) {
                FileCopyUtils.copy(in, out);
                fileNames.add(realName);
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        return fileNames;
    }

}

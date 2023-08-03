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

    @Value("${com.convenient.file.upload.review}")
    private String reviewLoc;

    @Value("${com.convenient.file.upload.product}")
    private String productLoc;


    public static class UploadException extends RuntimeException {
        public UploadException(String msg){ super(msg); }
    }

    public List<String> uploadFile(String locName, List<MultipartFile> multipartFiles, int height, int width){

        String loc = getLog(locName);

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

    public List<String> uploadFile(String locName, List<MultipartFile> multipartFiles){

        String loc = getLog(locName);

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

    public void deleteFile(String locName, List<String> files){

        String loc = getLog(locName);

        if(files == null || files.size() < 0){
            return;
        }

        for(String ele : files){

            File file = new File(loc, ele);

            File thumb = new File(loc, "s_"+ele);

            if(thumb.exists()){
                thumb.delete();
            }
            file.delete();
        }
    }

    public void deleteFile(String locName, String fileName){

        String loc = getLog(locName);

        if(fileName == null){
            return;
        }

        File file = new File(loc, fileName);

        File thumb = new File(loc, "s_"+fileName);

        if(thumb.exists()){
            thumb.delete();
        }
        file.delete();
    }

    public String getLog(String log){
        switch (log){
            case "review" : return reviewLoc;
            case "product" : return productLoc;
        }
        throw new UploadException("log is not matched in File");
    }

}

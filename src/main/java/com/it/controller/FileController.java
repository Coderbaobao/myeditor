package com.it.controller;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.it.utils.RandomUtil;

@RestController
public class FileController {
	private static String UPLOADED_FOLDER = "/home/ubuntu/images/";
	private static String LOCALPATH="http://193.112.47.88/pic/";
//	private static String UPLOADED_FOLDER = "D:/temp/";
//	private static String LOCALPATH="http://localhost:8080/pic/";
	private static Logger logger = Logger.getLogger(FileController.class);
	//处理文件上传
    @RequestMapping(value="/uploadimg")
    public @ResponseBody Map<String,Object> uploadimg(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String fileName = file.getOriginalFilename();
        //保存
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            Files.write(path, bytes);
            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
            resultMap.put("url",LOCALPATH + fileName);
        } catch (Exception e) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            e.printStackTrace();
        }
        System.out.println(resultMap);
        return resultMap;
    }
    
    @RequestMapping(value="/uploadBase64",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> base64UpLoad(@RequestParam String base64Data,HttpServletRequest request, HttpServletResponse response){  
    	Map<String,Object> resultMap = new HashMap<String,Object>();
        try{  
            logger.debug("上传文件的数据："+base64Data);

            logger.debug("对数据进行判断");
            if(base64Data == null || "".equals(base64Data)){
                throw new Exception("上传失败，上传图片数据为空");
            }

            logger.debug("对数据进行解析，获取文件名和流数据");
            String suffix = ".png";
            String tempFileName = RandomUtil.getRandomFileName() + suffix;
            logger.debug("生成文件名为："+tempFileName);

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(base64Data);
            try{
                //使用apache提供的工具类操作流
                FileUtils.writeByteArrayToFile(new File(UPLOADED_FOLDER, tempFileName), bs);  
            }catch(Exception ee){
                throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
            } 
            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
            resultMap.put("url",LOCALPATH + tempFileName);
            logger.debug("上传成功");
        }catch (Exception e) {  
            logger.debug("上传失败,"+e.getMessage());
            System.out.println(e.getMessage());
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");  
        }  
        return resultMap;
    }
}
    

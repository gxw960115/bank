package com.qd.utils;

import cn.hutool.core.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.management.MBeanAttributeInfo;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File工具类，扩展 hutool 工具包
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);


    /**
     * 系统临时目录
     * <br>
     * windows 包含路径分割符，但Linux 不包含,
     * 在windows \\==\ 前提下，
     * 为安全起见 同意拼装 路径分割符，
     * <pre>
     *       java.io.tmpdir
     *       windows : C:\Users/xxx\AppData\Local\Temp\
     *       linux: /temp
     * </pre>
     */
    public static final String SYS_TEM_DIR = System.getProperty("java.io.tmpdir") + File.separator;
    /**
     * 定义GB的计算常量
     */
    private static final int GB = 1024 * 1024 * 1024;
    /**
     * 定义MB的计算常量
     */
    private static final int MB = 1024 * 1024;
    /**
     * 定义KB的计算常量
     */
    private static final int KB = 1024;

    /**
     * 格式化小数
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    private static final String IMAGE = "图片";
    private static final String TXT = "文档";
    private static final String MUSIC = "音乐";
    private static final String VIDEO = "视频";
    private static final String OTHER = "其他";

    public static File toFile(MultipartFile multipartFile) {
        // 获取文件名
        String filename = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = "." + getExtensionName(filename);
        File file = null;
        try {
            // 用uuid做文件名，防止生成的临时文件重复
            file = new File(SYS_TEM_DIR + IdUtil.simpleUUID() + prefix);
            // MultipartFile to File
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return file;
    }

    /**
     * 获取文件扩展名，不带 .
     *
     * @param filename
     * @return
     */
    private static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf(".");
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     *
     * @param fileName
     * @return
     */
    public static String getFileNameNoEx(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf(".");
            if ((dot > -1) && (dot < fileName.length())) {
                return fileName.substring(0, dot);
            }
        }
        return fileName;
    }

    /**
     * 文件大小转换
     *
     * @param size
     * @return
     */
    public static String getSize(long size) {
        String resultSize;
        if (size / GB >= 1) {
            // 如果当前Byte值大于等于1GB
            resultSize = DF.format(size / (float) GB) + "GB ";
        } else if (size / MB >= 1) {
            // 如果当前Byte的值大于等于1MB
            resultSize = DF.format(size / (float) MB) + "MB ";
        } else if (size / KB >= 1) {
            // 如果当前Byte的值大于等于1KB
            resultSize = DF.format(size / (float) KB) + "KB ";
        } else {
            resultSize = size + "B ";
        }
        return resultSize;
    }

    /**
     * 将文件名解析成文件的上传路径
     *
     * @param file
     * @param filePath
     * @return
     */
    public static File upload(MultipartFile file, String filePath) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssS");
        String name = getFileNameNoEx(file.getOriginalFilename());
        String suffix = getExtensionName(file.getOriginalFilename());
        String nowStr = "-" + format.format(date);
        try {
            String fileName = name + nowStr + "." + suffix;
            String path = filePath + fileName;
            // getCanonicalFile 可解析正确各种路径
            File dest = new File(path).getCanonicalFile();
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                if (!dest.getParentFile().mkdirs()) {
                    System.out.println("was not successful.");
                }
            }
            // 文件写入
            file.transferTo(dest);
            return dest;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}

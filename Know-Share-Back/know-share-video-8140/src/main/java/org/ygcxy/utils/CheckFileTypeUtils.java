package org.ygcxy.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.ygcxy.common.FileType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Project: org.ygcxy.utils
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/23 09:14
 * @Description:
 */
public class CheckFileTypeUtils {
    private static String bytes2hex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                hex.append("0");
            }
            hex.append(temp.toLowerCase());
        }
        return hex.toString();
    }

    /**
     * 读取文件头
     */
    private static String getFileHeader(String filePath) throws IOException {
        byte[] b = new byte[28];//这里需要注意的是,每个文件的magic word的长度都不相同,因此需要使用startwith
        InputStream inputStream = null;
        inputStream = new FileInputStream(filePath);
        inputStream.read(b, 0, 28);
        inputStream.close();

        return bytes2hex(b);
    }
    public static FileType getType(String filePath) throws IOException {

        String fileHead = getFileHeader(filePath);
        if (fileHead == null || fileHead.length() == 0) {
            return null;
        }
        fileHead = fileHead.toUpperCase();
        FileType[] fileTypes = FileType.values();
        for (FileType type : fileTypes) {
            if (fileHead.startsWith(type.getValue())) {
                return type;
            }
        }
        return null;
    }

    public static FileType getType(byte[] bytes) {
        String s = bytes2hex(bytes);
        if(s == null || s.length() == 0){
            return null;
        }
        String fileHead = s.toUpperCase();
        FileType[] fileTypes = FileType.values();
        for(FileType type: fileTypes) {
            if(fileHead.startsWith(type.getValue())){
                return type;
            }
        }
        return null;
    }

}


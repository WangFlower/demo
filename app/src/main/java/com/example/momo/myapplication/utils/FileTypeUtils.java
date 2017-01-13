package com.example.momo.myapplication.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by wang.renguang on 17/1/6.
 *
 * 解析文件头来取得文件类型
 *
 */

public class FileTypeUtils {

    private static final HashMap<String, String> mFileTypes = new HashMap<>();

    static {
        //自定义表情只支持这三种类型
        mFileTypes.put("FFD8FF", "jpg");
        mFileTypes.put("89504E47", "png");
        mFileTypes.put("47494638", "gif");

        //NOTE 需要就加
        //        mFileTypes.put("49492A00", "tif");
        //        mFileTypes.put("41433130", "dwg");
        //        mFileTypes.put("38425053", "psd");
        //        mFileTypes.put("7B5C727466", "rtf");
        //        mFileTypes.put("3C3F786D6C", "xml");
        //        mFileTypes.put("68746D6C3E", "html");
        //        mFileTypes.put("44656C69766572792D646174653A", "eml");
        //        mFileTypes.put("D0CF11E0", "doc");
        //        mFileTypes.put("5374616E64617264204A", "mdb");
        //        mFileTypes.put("252150532D41646F6265", "ps");
        //        mFileTypes.put("255044462D312E", "pdf");
        //        mFileTypes.put("504B0304", "zip");
        //        mFileTypes.put("52617221", "rar");
        //        mFileTypes.put("57415645", "wav");
        //        mFileTypes.put("41564920", "avi");
        //        mFileTypes.put("2E524D46", "rm");
        //        mFileTypes.put("000001BA", "mpg");
        //        mFileTypes.put("000001B3", "mpg");
        //        mFileTypes.put("6D6F6F76", "mov");
        //        mFileTypes.put("3026B2758E66CF11", "asf");
        //        mFileTypes.put("4D546864", "mid");
        //        mFileTypes.put("1F8B08", "gz");
    }

    public static String getFileType(String filePath) throws Exception {
        String header = getFileHeader(filePath);
        if (header == null) {
            return null;
        }
        // 比如gif/png类型的 速度比较快 不用对比字符串
        String fileType = mFileTypes.get(header);
        if (fileType != null) {
            return fileType;
        }

        Iterator iterator = mFileTypes.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            // 长度相同的前面已经判断过
            if (key.length() != header.length()) {
                if (key.length() > header.length() ? key.startsWith(header) : header.startsWith
                        (key)) {
                    return mFileTypes.get(key);
                }
            }
        }
        return mFileTypes.get(header);
    }

    //获取文件头信息
    public static String getFileHeader(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists() || file.length() < 4) {
            return null;
        }
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(file);
            byte[] b = new byte[4];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }


}

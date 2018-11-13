package com.minilive.library.util;

import java.io.File;

/**
 * Created by Administrator on 2018/1/12.
 */

public class FileUtils {

    public static boolean deleteFile(String path){
        File file = new File(path);
        if (file.isFile() && file.exists()){
            file.delete();
            return true;
        }
        return false;
    }

}

package com.minilive.library.compress;

import android.content.Context;

public class PathUtil {

    public static String generateNewPath(Context context, String oldPath){
        String newPath=context.getFilesDir().getPath();
        int len = oldPath.split("\\.").length;
        newPath = newPath+"/compress."+oldPath.split("\\.")[len-1];
        return newPath;
    }


}

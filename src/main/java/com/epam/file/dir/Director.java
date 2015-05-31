package com.epam.file.dir;

import java.io.File;

public class Director {
    public static File createDir(String path){

          File file=new File(path);
       file.mkdirs();
        return file;
    }
    public static File createFile(String path){
        File file=new File(path);
        return file;
    }
    public static void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                File f = new File(dir, children[i]);
                deleteDirectory(f);
            }
            dir.delete();
        } else dir.delete();
    }
}

package com.epam.file.dir;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.*;

public class Archiv {
    public static void directoryToZip(File directory, File zipFile) throws IOException {
        URI base = directory.toURI();
        Deque<File> queue = new LinkedList<File>();
        queue.push(directory);
        OutputStream out = new FileOutputStream(zipFile);
        Closeable res = out;

        try {
            ZipOutputStream zout = new ZipOutputStream(out);
            res = zout;
            while (!queue.isEmpty()) {
                directory = queue.pop();
                for (File child : directory.listFiles()) {
                    String name = base.relativize(child.toURI()).getPath();
                    if (child.isDirectory()) {
                        queue.push(child);
                        name = name.endsWith("/") ? name : name + "/";
                        zout.putNextEntry(new ZipEntry(name));
                    } else {
                        zout.putNextEntry(new ZipEntry(name));


                        InputStream in = new FileInputStream(child);
                        try {
                            byte[] buffer = new byte[1024];
                            while (true) {
                                int readCount = in.read(buffer);
                                if (readCount < 0) {
                                    break;
                                }
                                zout.write(buffer, 0, readCount);
                            }
                        } finally {
                            in.close();
                        }
                        zout.closeEntry();
                    }
                }
            }
        } finally {
            res.close();
        }
    }
    }

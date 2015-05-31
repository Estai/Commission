package com.epam.file.dir;


import org.h2.util.StringUtils;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;

public class DownloadFile {

    public static String encodeContentDispositionForDownload(HttpServletRequest request, String fileName, boolean isInline)
            throws UnsupportedEncodingException {
        String contentDisposition = isInline ? "inline; " : "attachment; ";
        String newFileName = MimeUtility.encodeText(fileName, "UTF8", "Q");
        if (newFileName != null) {
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            int pos = newFileName.indexOf("=?UTF8?Q?");
            if (pos >= 0) {
                newFileName = StringUtils.replaceAll(newFileName, "=?UTF8?Q?", "");
                newFileName = StringUtils.replaceAll(newFileName, "\r\n", "");
                newFileName = StringUtils.replaceAll(newFileName, "_?=", "");
                newFileName = StringUtils.replaceAll(newFileName, "?=", "");
                newFileName = StringUtils.replaceAll(newFileName, " ", "%20");
                newFileName = newFileName.replaceAll("=", "%");
                if (agent != null && agent.indexOf("opera") == -1 && agent.indexOf("msie") != -1)
                    // IE
                    contentDisposition += "filename=\"" + newFileName + "\"";
                else
                    // Opera
                    // see RFC [2184] for details (http://www.ietf.org/rfc/rfc2184.txt)
                    contentDisposition += "filename*=UTF8''" + newFileName;
            } else
                // Firefox and others
                contentDisposition += "filename=\"" + MimeUtility.encodeText(fileName, "UTF8", "B") + "\"";
        }
        return contentDisposition;
    }
}

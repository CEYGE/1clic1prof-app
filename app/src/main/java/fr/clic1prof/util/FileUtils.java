package fr.clic1prof.util;

import android.net.Uri;
import android.webkit.MimeTypeMap;

public class FileUtils {

    public static String getFileExtension(Uri uri) {
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(uri.getPath());
    }

    public static String getMimeType(Uri uri) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(uri.getPath());

        if(extension == null) return null;

        MimeTypeMap map = MimeTypeMap.getSingleton();

        return map.getMimeTypeFromExtension(extension);
    }
}

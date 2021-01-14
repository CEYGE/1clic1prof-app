package fr.clic1prof.viewmodels.invoice;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import fr.clic1prof.activities.dashboard.student.MainStudentActivity;

import static android.content.Context.DOWNLOAD_SERVICE;
import static androidx.core.app.ActivityCompat.requestPermissions;

public class fileCreation {
    private static final int REQUEST_ID_WRITE_PERMISSION = 200;
    private static final String LOG_TAG = "1clic1prof";
    private Context context;
    private InputStream data;
    private String name;

    public fileCreation(Context context, InputStream data, String name) {
        this.context = context;
        this.data = data;
        this.name = name;
    }

    // Code from https://devstory.net/10541/android-external-storage
    // With Android Level >= 23, you have to ask the user
    // for permission with device (For example read/write data on the device).
    public void askPermissionAndWriteFile() {
        boolean canWrite = this.askPermission(REQUEST_ID_WRITE_PERMISSION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!canWrite) {
            Toast.makeText(context,
                    "You do not allow this app to write files.", Toast.LENGTH_SHORT).show();
            return;
        }
        //
        this.writeFile();
    }

    private void writeFile () {

        try {
            File extStore = this.getAppExternalFilesDir();

            // ==> /storage/emulated/0/note.txt  (API < 29)
            // ==> /storage/emulated/0/Android/data/AppName/files/note.txt (API >=29)
            String path = extStore.getAbsolutePath() + "/" + name;

            File myFile = new File(path);

            copyInputStreamToFile(this.data, myFile);

            //Appearance in the download manager, much convenient for opening the file
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                downloadManager.addCompletedDownload(myFile.getName(), myFile.getName(), true, "text/plain",myFile.getAbsolutePath(),myFile.length(),true);
            }


            Toast.makeText(context, path + " saved", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, "Write Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(LOG_TAG, "Write Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private File getAppExternalFilesDir() {
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            // /storage/emulated/0/Android/data/AppName/files
            return context.getExternalFilesDir(null);
        } else {
            // @Deprecated in API 29.
            // /storage/emulated/0
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }
    }


    private boolean askPermission(int requestId, String permissionName) {
        Log.i(LOG_TAG, "Ask for Permission: " + permissionName);
        Log.i(LOG_TAG, "Build.VERSION.SDK_INT: " + Build.VERSION.SDK_INT);

        if (Build.VERSION.SDK_INT >= 23) {

            // Check if we have permission
            int permission = ActivityCompat.checkSelfPermission(context, permissionName);

            Log.i(LOG_TAG, "permission: " + permission);
            Log.i(LOG_TAG, "PackageManager.PERMISSION_GRANTED: " + PackageManager.PERMISSION_GRANTED);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                requestPermissions((Activity) context,
                        new String[]{permissionName},
                        requestId
                );
                return false;
            }
        }
        return true;
    }

    private static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        final int DEFAULT_BUFFER_SIZE = 8192;

        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }
}

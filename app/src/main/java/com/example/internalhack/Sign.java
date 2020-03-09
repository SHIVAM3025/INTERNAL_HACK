package com.example.internalhack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.internalhack.Utils.Util;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Sign extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_SINGLE_PERMISSION = 1;
    private SignaturePad signaturePad;
    private String ImagePath = null;
    private Button mclear , msave;

    //TODO add date

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (restorePrefData()) {

            Intent mainActivity = new Intent(getApplicationContext(), Speech_activity.class);
            startActivity(mainActivity);
            finish();
        }

        setContentView(R.layout.activity_sign);

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


        signaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mclear = (Button) findViewById(R.id.clear);
        msave = (Button) findViewById(R.id.save);

        mclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signaturePad.animate();
                signaturePad.clear();

            }
        });


        msave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Sign.this);
                final AlertDialog.Builder progressbuilder = new AlertDialog.Builder(Sign.this);
                View mview = getLayoutInflater().inflate(R.layout.dialog_box_login, null);
                final View progress = getLayoutInflater().inflate(R.layout.dialog_process, null);
                Button myes = (Button) mview.findViewById(R.id.yes);
                Button mno = (Button) mview.findViewById(R.id.no);

                mBuilder.setView(mview);
                progressbuilder.setView(progress);

                final AlertDialog dialog = mBuilder.create();
                final AlertDialog progressdialog = progressbuilder.create();


                myes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        progressdialog.show();

                        Bitmap signatureBitmap = signaturePad.getTransparentSignatureBitmap();
                        if (addJpgSignatureToGallery(signatureBitmap)) {
                            // Toast.makeText(Sign.this, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                            //progressdialog.dismiss();


                            Intent intent = new Intent(Sign.this, Speech_activity.class);
                            startActivity(intent);

                            savePrefsData();

                            finish();

                            //Toast.makeText(Sign.this, ImagePath, Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Sign.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
                            progressdialog.dismiss();
                        }


                    }
                });

                mno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                    }
                });

                dialog.show();

            }
        });


    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }

        return file;
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature.jpg", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);

            ImagePath = photo.getAbsolutePath();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        Sign.this.sendBroadcast(mediaScanIntent);
    }


    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isSigned", true);
        editor.commit();


    }

    private boolean restorePrefData() {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isSigned", false);
        return isIntroActivityOpnendBefore;


    }


}

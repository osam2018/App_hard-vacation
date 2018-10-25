package com.example.user.hradvacation;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class myQRcreator {
    private String inputvalue;
    private int smallerDimension;
    private Bitmap bitmap;
    private String savePath;
    private QRGEncoder qrgEncoder;

    public String CreateQRCode(String url, String Path, String filename){
        try {
            smallerDimension = 100;
            inputvalue = url;
            savePath = Environment.getExternalStorageDirectory() + Path;
            qrgEncoder = new QRGEncoder(inputvalue, null, QRGContents.Type.TEXT, smallerDimension);
            bitmap = qrgEncoder.encodeAsBitmap();
            boolean save = QRGSaver.save(savePath, filename, bitmap, QRGContents.ImageType.IMAGE_JPEG);
            String result = save ? "true" : "false";
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}

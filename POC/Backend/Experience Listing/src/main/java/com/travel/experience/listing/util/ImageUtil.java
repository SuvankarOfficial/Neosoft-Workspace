package com.travel.experience.listing.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtil {

    public static byte[] compressImage(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4*1024];
        while (!deflater.finished()){
            int size = deflater.deflate(temp);
            byteArrayOutputStream.write(temp,0,size);
        }
        try{
            byteArrayOutputStream.close();
        } catch (Exception e) {
            System.out.println("cannot Compress");
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4*1024];
        try {
            while (!inflater.finished()){
                int count = inflater.inflate(temp);
                byteArrayOutputStream.write(temp,0,count);
            }
            byteArrayOutputStream.close();
        } catch (Exception e) {
            System.out.println("cannot Decompress");
        }
        return byteArrayOutputStream.toByteArray();
    }
}

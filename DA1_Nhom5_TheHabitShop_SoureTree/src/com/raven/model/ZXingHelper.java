/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

/**
 *
 * @author ADMIN
 */
public class ZXingHelper {
    public static byte[] getQRCodeImage(String text,int width,int height){
        try {
            QRCodeWriter qrCode = new QRCodeWriter();
            BitMatrix bitMa = qrCode.encode(text, BarcodeFormat.CODABAR, width, height);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMa, "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }
    public static byte [] getBarCodeImage(String text,int width,int height){
        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            Writer write = new Code128Writer();
            BitMatrix bitMa = write.encode(text, BarcodeFormat.CODE_128, width, width);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMa, "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

package com.weibank.com.weibankapp.utils;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * Created by Administrator on 2016/1/22.
 */
public class QRCodeUtil {
        /**
         * 生成图片,并添加logo
         * @param text
         * @param w
         * @param h
         * @param logo
         * @return
         */
        public static Bitmap createImage(String text,int w,int h,Bitmap logo) {
            try {
                Bitmap scaleLogo = getScaleLogo(logo,w,h);
                int offsetX = 0;
                int offsetY = 0;
                if(scaleLogo != null){
                    offsetX = (w - scaleLogo.getWidth())/2;
                    offsetY = (h - scaleLogo.getHeight())/2;
                }
                Hashtable<EncodeHintType, String> hints = new Hashtable<>();
                hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
//                    BarcodeFormat.QR_CODE, 400, 400, hints);
                BitMatrix bitMatrix = new QRCodeWriter().encode(text,
                        BarcodeFormat.QR_CODE, w, h, hints);
                int[] pixels = new int[w * h];
                for(int y = 0; y < h; y++){
                    for(int x = 0; x < w; x++){
                        //判断是否在logo图片中
                        if(offsetX != 0 && offsetY != 0
                                && x >= offsetX
                                && x < offsetX+scaleLogo.getWidth()
                                && y>= offsetY
                                && y < offsetY+scaleLogo.getHeight()){
                            int pixel = scaleLogo.getPixel(x-offsetX,y-offsetY);
                            //如果logo像素是透明则写入二维码信息
//                        if(pixel == 0){
//                            if(bitMatrix.get(x, y)){
//                                pixel = 0xff000000;
//                            }
//                            else{
//                                pixel = 0xffffffff;
//                            }
//                        }
                            pixels[y * w + x] = pixel;

                        }else{
                            if (bitMatrix.get(x, y)) {
                                pixels[y * w + x] = 0xff000000;
                            }
//                        else {
//                            pixels[y * w + x] = 0xffffffff;
//                        }
                        }
                    }
                }
                Bitmap bitmap = Bitmap.createBitmap(w, h,
                        Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
                return bitmap;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        /**
         * 生成不带图片的二维码
         */
        public static Bitmap createImageNoLogo(String contents,int width,int height){
            try{

                MultiFormatWriter writer = new MultiFormatWriter();
                Hashtable hashtable = new Hashtable();

                //设置字符集
                hashtable.put(EncodeHintType.CHARACTER_SET,"utf-8");
                hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                BitMatrix matrix = writer.encode(contents,BarcodeFormat.QR_CODE,
                                                 2 * width,2 * height,hashtable);
                int xwidth = matrix.getWidth();
                int xheight = matrix.getHeight();

                int[] pilxes = new int[xheight * xheight];
                //迭代列
                for(int i = 0; i < xheight; i++){
                    //迭代行
                    for(int j = 0; j < xwidth; j++){

                        if(matrix.get(i,j)){

                            pilxes[i * xwidth + j] = 0xff000000;
                        }
                    }
                }

                Bitmap bitmap = Bitmap.createBitmap(xwidth,xheight,Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pilxes,0,xwidth,0,0,xwidth,xheight);

                return bitmap;
            }catch (Exception e){

                e.printStackTrace();
            }
            return null;
        }
        /**
         * 缩放logo到二维码的1/5
         * @param logo
         * @param w
         * @param h
         * @return
         */
        private static Bitmap getScaleLogo(Bitmap logo,int w,int h){
            if(logo == null){

                return null;
            }
            Matrix matrix = new Matrix();
            float sx = 2 * IMAGEHALFWIDHT / logo.getWidth();
            float sy = 2 * IMAGEHALFWIDHT;
            float scaleFactor = Math.min(w * 1.0f / 5 / logo.getWidth(),
                    h * 1.0f / 5 / logo.getHeight());
            matrix.postScale(scaleFactor,scaleFactor);
            Bitmap result = Bitmap.createBitmap(logo, 0, 0, logo.getWidth(),
                    logo.getHeight(), matrix, true);
            return result;
        }

        private static final float IMAGEHALFWIDHT = 40;
}

package util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class VerifyUtil {
	
	public static Map<String, Object> generateVerify(){
        //建立一張圖片
        BufferedImage verifyPic = new BufferedImage(120,40,BufferedImage.TYPE_3BYTE_BGR);
        //通過圖片獲取畫筆
        Graphics2D g = verifyPic.createGraphics();
        //準備一個字母+數字的字典
        String letters = "23456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //規定驗證碼的位數
        int verifyLength = 4;
        //生成隨機驗證碼
        String verifyCode = "";
        //迴圈取值
        for(int i=0; i<verifyLength; i++) {
            verifyCode +=letters.charAt((int)(Math.random()*letters.length()));
        }
        //將圖片的底板由黑變白
        g.setColor(Color.white);
        g.fillRect(0, 0, 120, 40);
        //將驗證碼畫在圖片之上
        g.setFont(new Font("微軟雅黑",Font.BOLD, 24));
        for (int i = 0; i < verifyLength; i++) {
            //隨機產生一個角度
            double theta = Math.random() * Math.PI / 4 * ((int)(Math.random()*2) == 0?1:-1);
            //產生偏轉
            g.rotate(theta, 24+i*22, 20);
            //每畫一個字幕之前都隨機給一個顏色
            g.setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
            g.drawString(verifyCode.charAt(i)+"", 20+i*22 , 26);
            //回正
            g.rotate(-theta, 24+i*22, 20);
        }
        //加入干擾線
        for (int i = 0; i < 5; i++) {
            //給隨機顏色
            g.setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
            //畫線
            g.drawLine((int)(Math.random()*120), (int)(Math.random()*40),
                    (int)(Math.random()*120), (int)(Math.random()*40));
        }
        //設定邊框顏色
        g.setColor(Color.yellow);
        //給驗證碼一個外邊框
        g.drawRect(0, 0, 118, 38);

        //將驗證碼和圖片一起存入map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("verifyCode", verifyCode);
        data.put("verifyPic", verifyPic);

        return data;
    }

}

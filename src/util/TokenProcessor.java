package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class TokenProcessor {


    private TokenProcessor() {
    }

    private final static TokenProcessor TOKEN_PROCESSOR = new TokenProcessor();

    public static TokenProcessor getInstance() {
        return TOKEN_PROCESSOR;
    }


    public String makeToken() {


    	//這個隨機生成出來的Token的長度是不確定的
        String token = String.valueOf(System.currentTimeMillis() + new Random().nextInt(999999999));

        try {
            //我们想要随机数的长度一致，就要获取到数据指纹
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] md5 = messageDigest.digest(token.getBytes());

            //如果我們直接 return  new String(md5)出去，得到的隨機數會亂碼。
            //因為隨機數是任意的01010101010，在轉換成字符串的時候，會查gb2312的碼表，gb2312碼表不一定支持該二進制數據，得到的就是亂碼
            
            //於是乎經過base64編碼成了明文的數據
            Base64.Encoder base64Encoder = Base64.getEncoder();
            return base64Encoder.encodeToString(md5);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) {
    	//生出隨機數
        TokenProcessor tokenProcessor = TokenProcessor.getInstance();
        String token = tokenProcessor.makeToken();
        
        System.out.println(token);

//      //將隨機數存進Session中
//      request.getSession().setAttribute("token", token);
//
//      //跳轉到顯示頁面
//      request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}

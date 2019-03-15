package com.wjc.assess.utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/* 验证码 */
public class VerifyCode {
    //随机字符从这里取
    private static String randomString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //图片宽
    private static int width = 95;
    //图片高
    private static int height = 25;
    //干扰线数量
    private static int line = 4;
    //图像对象
    private static BufferedImage buffImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    //随机类
    private static Random random = new Random();

    //返回验证码
    public static void getVerifyCode(HttpServletResponse httpServletResponse){
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        g.setColor(randomColor());
//        // 绘制干扰线
//        for (int i = 0; i <= lineSize; i++) {
//            drowLine(g);
//        }
        // 绘制随机字符
        String randomStr = "";
        for (int i = 1; i <= line; i++) {
            randomStr = drawString(g, randomString, i);
        }
        //1：将随机生成的验证码放入Cookie中
        Cookie cookie = new Cookie(key,randomString);
        response.addCookie(cookie);
        //2：将随机生成的验证码放入session中
        String sessionid = request.getSession().getId();
        request.getSession().setAttribute(sessionid+key, randomString);
        System.out.println("*************" + randomString);

        //总结：这两种方式都不是很好，
        //（1）：使用cookie的方式，将验证码发送到前台浏览器，不安全！不建议使用。
        //（2）：使用session的方式，虽然能解决验证码不发送到浏览器，安全性较高了，但是如果用户量太大，这样的存储方式会对服务器造成压力，影响服务器的性能。不建议使用。
        //这里暂时实现用这种方式，好的办法是，在项目中使用的缓存，将生成的验证码存放到缓存中，设置失效时间，这样既可以实现安全性也能减轻服务器的压力。
        g.dispose();
        try {
            ByteArrayOutputStream tmp = new ByteArrayOutputStream();
            ImageIO.write(image, "png", tmp);
            tmp.close();
            Integer contentLength = tmp.size();
            response.setHeader("content-length", contentLength + "");
            response.getOutputStream().write(tmp.toByteArray());// 将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
/*******************/
        // 禁止图像缓存。
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);

        httpServletResponse.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = null;
        try {
            sos = httpServletResponse.getOutputStream();
            ImageIO.write(buffImg, "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //画背景


    //设置字体样式
    private static Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }
    //画字符串
    private static String drawString(Graphics g,String str,int i){
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString());
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }
    //随机颜色
    private static Color randomColor(){
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }
    //画干扰线
    private static void drawLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }
    //随机生成一个字符
    private static String getRandomString(){
        return String.valueOf(randomString.charAt(random.nextInt(randomString.length())));
    }

}

package Sevlet;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
//火狐base64,ie和google urlutf-8

/**
 * @author lqc
 */

//只能把文件放在webapp里才能被加载
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String downloadFileName="fj.png";
        ServletContext servletContext=getServletContext();
        InputStream resourceAsStream =servletContext.getResourceAsStream("/file/"+downloadFileName);

        //获取要下载的文件类型
        String type = servletContext.getMimeType("/file/" + downloadFileName);
        System.out.println("type:  "+type);

        //Content-Disposition响应头，表示收到的数据怎么处理
        //attachment 表示附件 表示下载使用
        resp.setContentType(type);
        //不设置的话，就会直接显示在网页上，文件名可以任意，但是不能为中文，要想中文可以utf8编码
        if (req.getHeader("User-Agent").contains("Firefox")){
            //firefox,google
            String content="家具股份的健康.jpg";
            BASE64Encoder base64Encode=new BASE64Encoder();
            String encodeString=base64Encode.encode(content.getBytes("UTF-8"));
            resp.setHeader("Content-Disposition","attachment; filename==?UTF-8?B?" +encodeString+"?=");
        }else {
            //ie,google
            resp.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode( "快快快.png","UTF-8"));
        }

        //resp.setHeader("Content-Disposition","attachment; filename=" +downloadFileName);
        OutputStream  outputStream = resp.getOutputStream();
        //读取输入流中全部数据，复制给输出流，输出给客户端
        IOUtils.copy(resourceAsStream ,outputStream);
        //outputStream.write();

    }
}

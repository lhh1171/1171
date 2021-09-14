package Sevlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author lqc
 */

public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("文件上传过来了");
        ServletInputStream inputStream=req.getInputStream();
        byte[] buffer=new byte[10240000];
        int read= inputStream.read(buffer);
        System.out.println(new String(buffer,0,read));
        if (ServletFileUpload.isMultipartContent(req)){
            //创建工厂实现类
            FileItemFactory fileItemFactory=new DiskFileItemFactory();
            //创建用于解析上传数据的工具类serverupload
            ServletFileUpload servletFileUpload=new ServletFileUpload(fileItemFactory);

            //解析上传的数据
            try {
                List<FileItem> list=servletFileUpload.parseRequest(req);
                for (FileItem fileItem:list){
                    if (fileItem.isFormField()){
                        //普通表单项
                        System.out.println("name:   "+fileItem.getFieldName());
                        System.out.println("value:  "+fileItem.getString("UTF-8"));
                    }else {
                        //上传的文件
                        System.out.println("name:   "+fileItem.getFieldName());
                        System.out.println("filename:   "+fileItem.getName());
                        try {
                            fileItem.write(new File("/opt/fff/"+fileItem.getName()));
                            System.out.println("完成");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }
    //用来处理文件上传的数据
}

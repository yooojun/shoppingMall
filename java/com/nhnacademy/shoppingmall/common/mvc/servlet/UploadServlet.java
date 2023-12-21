package com.nhnacademy.shoppingmall.common.mvc.servlet;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 100,
        maxFileSize         = 1024 * 1024 * 100,
        maxRequestSize      = 1024 * 1024 * 100,
        location = "/Users/jun/Documents/java-servlet-jsp-shoppingmall/src/main/webapp/resources"
)
@WebServlet(name = "uploadServlet", urlPatterns = "/admin/file/upload")
public class UploadServlet extends HttpServlet {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/Users/jun/Documents/java-servlet-jsp-shoppingmall/src/main/webapp/resources";

    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            for(Part part : req.getParts()){
                String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

                if (contentDisposition.contains("filename=")) {

                    String fileName = extractFileName(contentDisposition);
                    fileName = LocalDateTime.now() + fileName ;
                    log.debug("fileName = {}", fileName);
                    req.setAttribute("productImage", fileName);
                    if (part.getSize() > 0) {
                        part.write(UPLOAD_DIR + File.separator + fileName);
                        part.delete();
                    }
                } else {
                    String formValue = req.getParameter(part.getName());
                    log.error("{}={}", part.getName(), formValue);
                }
            }


            RequestDispatcher rd = req.getRequestDispatcher("/admin/product/registerAction.do");
            rd.forward(req, resp);
        } catch (IOException e) {
            log.debug("error = {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (ServletException e) {
            log.debug("error = {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private String extractFileName(String contentDisposition) {
        log.error("contentDisposition:{}",contentDisposition);
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=")+2, token.length()-1);
            }
        }
        return null;
    }
}

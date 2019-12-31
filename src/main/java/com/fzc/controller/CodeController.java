package com.fzc.controller;

import com.fzc.util.SecurityCode;
import com.fzc.util.SecurityImage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName CodeController
 * @Description
 * @Author
 * @Date 2019-12-17 15:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/code")
public class CodeController {
    @RequestMapping("/getCode")
    public void getCode(HttpSession session, HttpServletResponse response) throws IOException {
        String code = SecurityCode.getSecurityCode();
        session.setAttribute("code", code);
        BufferedImage image = SecurityImage.createImage(code);
        OutputStream stream = null;
        stream = response.getOutputStream();
        ImageIO.write(image, "png", stream);
    }
}

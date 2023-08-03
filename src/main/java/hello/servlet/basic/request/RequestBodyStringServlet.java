package hello.servlet.basic.request;

import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBody", urlPatterns = "/request-body")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletInputStream inputStream = req.getInputStream();  //  message body 의 내용을 byte 코드로 받는다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println(messageBody);

        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(messageBody);

    }
}

package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.dto.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBody", urlPatterns = "/request-body")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ServletInputStream inputStream = req.getInputStream();  //  message body 의 내용을 byte 코드로 받는다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        HelloData helloData;
        try {
            //  ToDO Post 요청을 보내는 것도 확인해보자! x-www-form-urlencoded 로 post 요청
            helloData = objectMapper.readValue(messageBody, HelloData.class);
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().write(helloData.toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("변환 실패");
        }

    }

}

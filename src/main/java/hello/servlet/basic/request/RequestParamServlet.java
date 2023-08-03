package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


@WebServlet(name = "paramServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("전체 파라미터 조회");
        //  req.getParameterNames() : 모든 요청 파라미터를 조회할 수 있다.
        Enumeration<String> parameterNames = req.getParameterNames();
        parameterNames.asIterator().forEachRemaining(System.out::println);
        System.out.println("전체 파라미터 종료\n");


        System.out.println("단일 파라미터 조회");
        //  단일 파라미터 조회
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        System.out.println(username + "  :  " + age);
        System.out.println("단일 파라미터 종료\n");

        //  username=hello1&username=hello2 와 같이 동일한 parameter name 으로 요청을 보낼 수도 있다.
        String[] parameterValues = req.getParameterValues("username");
        for (String name : parameterValues) {
            System.out.println(name);
        }

        resp.getWriter().write("ok");
    }

}






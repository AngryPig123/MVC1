package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
//  name : 서블릿 이름, urlPatterns : url 매핑 주소
public class HelloServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  ToDO 블로그 정리 필요
        System.out.println("HelloServlet.service");
        System.out.println("req = " + req);
        System.out.println("resp = " + resp);

        //  Url 파라미터로 값 넣어서 뿌려보기
        Enumeration<String> parameterNames = req.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            System.out.println(key);
            System.out.println(req.getParameter(key));
        }

        //  응답 메세지 보내 보기,   RequestHeader, ResponseHeader 확인해보기.
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println("안녕? 이건 성공 메세지야");

        //  appliaction.properties : logging.level.org.apache.coyote.http11=debug
        //  해당 설정을 하면 request, response 정보를 콘솔로 확인할 수 있다.
        //  서블릿 컨테이너 동작 방식 정리. 이전에 정리했던 servlet container 보여주면 될듯.




    }

}

package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeader", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.setHeader("Content-type", "text/plain;charset=utf-8;");
        this.content(resp);
        this.cookie(resp);
        resp.setHeader("Cache-Control", "no-cache, no-store, no-revalidate");

        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("myHeader", "hi");

        StringBuilder stringBuilder = new StringBuilder();
        resp.getHeaderNames().iterator().forEachRemaining(item -> {
            stringBuilder.append(item).append(" : ").append(resp.getHeader(item)).append("\n");
        });

        this.redirect(resp);
        resp.getWriter().write(stringBuilder.toString());   //  이렇게 하면 리 다이렉트 되었기 때문에 사용할 수 없다고 나옴

    }

    private void content(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    private void cookie(HttpServletResponse resp) {
        Cookie cookie = new Cookie("myCookie", "myCookieValue");
        cookie.setDomain("localhost");
        cookie.setMaxAge(500);
        resp.addCookie(cookie);
    }

    private void redirect(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/basic/hello-form.html");
    }

}

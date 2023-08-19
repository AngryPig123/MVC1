package hello.servlet.web.servletmvc;

import hello.servlet.repotiroy.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    private final MemberRepository memberRepository;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();

        String viewPath = "/templates/new-form.html";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewPath);

        String contextPath = req.getContextPath();
        log.info("contextPath = {}", contextPath);

        log.info("requestDispatcher = {}", requestDispatcher);
        requestDispatcher.forward(req, resp);

    }


}

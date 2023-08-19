package hello.servlet.web.frontcontrollerv1;

import hello.servlet.repotiroy.MemberRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebServlet(name = "myFrontControllerV1", urlPatterns = "/v1/*")
public class MyFrontControllerV1 extends HttpServlet {

    private final Map<String, FrontControllerV1> controllerMap;

    public MyFrontControllerV1(MemberRepository memberRepository) {
        this.controllerMap = new HashMap<>();
        controllerMap.put("/v1/new-form", new FormControllerV1());
        controllerMap.put("/v1/save", new SaveControllerV1(memberRepository));
        controllerMap.put("/v1/members", new MembersControllerV1(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        log.info("requestURI = {}", requestURI);

        if (controllerMap.containsKey(requestURI)) {
            FrontControllerV1 frontControllerV1 = controllerMap.get(requestURI);
            frontControllerV1.process(req, resp);
        } else {
            log.error("error");
        }

    }

}

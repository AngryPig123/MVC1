package hello.servlet.web.frontcontroller;

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

    private final Map<String, MyFrontController> controllerMap;

    public MyFrontControllerV1(MemberRepository memberRepository) {
        this.controllerMap = new HashMap<>();
        controllerMap.put("/v1/new-form", new MyFormController());
        controllerMap.put("/v1/save", new MySaveController(memberRepository));
        controllerMap.put("/v1/members", new MyMembersController(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        log.info("requestURI = {}", requestURI);

        if (controllerMap.containsKey(requestURI)) {
            MyFrontController myFrontController = controllerMap.get(requestURI);
            myFrontController.process(req, resp);
        } else {
            log.error("error");
        }

    }

}

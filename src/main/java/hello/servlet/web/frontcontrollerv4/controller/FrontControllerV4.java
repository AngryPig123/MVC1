package hello.servlet.web.frontcontrollerv4.controller;

import hello.servlet.repotiroy.MemberRepository;
import hello.servlet.web.MyView;
import hello.servlet.web.frontcontrollerv4.ControllerV4;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@WebServlet(name = "frontControllerV4", urlPatterns = "/v4/*")
public class FrontControllerV4 extends HttpServlet {

    private final Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerV4(MemberRepository memberRepository) {
        controllerMap.put("/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/v4/members/save", new MemberSaveControllerV4(memberRepository));
        controllerMap.put("/v4/members", new MemberListControllerV4(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();

        if (controllerMap.containsKey(requestURI)) {

            Map<String, Object> model = new HashMap<>();
            Map<String, String> parameter = createParamMap(req);

            ControllerV4 controllerV4 = controllerMap.get(requestURI);

            String process = controllerV4.process(parameter, model);
            MyView view = viewResolver(process);
            view.render(model, req, resp);

        } else {
            log.error("error");
        }

    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        return Collections.list(req.getParameterNames())
                .stream()
                .collect(Collectors.toMap(paramName -> paramName, req::getParameter));
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}

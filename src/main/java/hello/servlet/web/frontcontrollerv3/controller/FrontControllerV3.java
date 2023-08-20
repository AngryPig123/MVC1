package hello.servlet.web.frontcontrollerv3.controller;

import hello.servlet.repotiroy.MemberRepository;
import hello.servlet.web.ModelView;
import hello.servlet.web.MyView;
import hello.servlet.web.frontcontrollerv3.ControllerV3;
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
@WebServlet(name = "frontControllerV3", urlPatterns = "/v3/*")
public class FrontControllerV3 extends HttpServlet {

    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerV3(MemberRepository memberRepository) {
        controllerMap.put("/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/v3/members/save", new MemberSaveControllerV3(memberRepository));
        controllerMap.put("/v3/members", new MemberListControllerV3(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();

        if (controllerMap.containsKey(requestURI)) {

            Map<String, String> parameter = createParamMap(req);

            ControllerV3 controllerV3 = controllerMap.get(requestURI);
            ModelView modelView = controllerV3.process(parameter);

            MyView view = viewResolver(modelView.getViewName());
            view.render(modelView.getModel(), req, resp);

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

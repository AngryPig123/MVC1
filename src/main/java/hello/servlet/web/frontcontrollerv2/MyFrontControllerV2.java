package hello.servlet.web.frontcontrollerv2;

import hello.servlet.web.MyView;
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
@WebServlet(name = "myFrontControllerV2", urlPatterns = "/v2/*")
public class MyFrontControllerV2 extends HttpServlet {

    private final Map<String, FrontControllerV2> controllerMap;

    public MyFrontControllerV2() {
        this.controllerMap = new HashMap<>();
        controllerMap.put("/v2/new-form", new FrontFormControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();

        if (controllerMap.containsKey(requestURI)) {

            FrontControllerV2 frontControllerV2 = controllerMap.get(requestURI);
            MyView process = frontControllerV2.process(req, resp);
            process.render(req, resp);

        } else {
            log.error("error");
        }

    }
}

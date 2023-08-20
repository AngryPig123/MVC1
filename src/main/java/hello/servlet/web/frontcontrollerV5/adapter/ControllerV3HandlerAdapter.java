package hello.servlet.web.frontcontrollerV5.adapter;

import hello.servlet.web.ModelView;
import hello.servlet.web.frontcontrollerV5.MyHandlerAdapter;
import hello.servlet.web.frontcontrollerv3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return handler instanceof ControllerV3;
    }

    @Override
    public ModelView handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;
        return controller.process(gerParameterMap(request));
    }

    public Map<String, String> gerParameterMap(HttpServletRequest request) {
        return Collections.list(request.getParameterNames())
                .stream()
                .collect(Collectors.toMap(paramName -> paramName, request::getParameter));
    }

}

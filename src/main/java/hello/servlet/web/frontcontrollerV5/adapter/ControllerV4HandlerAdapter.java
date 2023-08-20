package hello.servlet.web.frontcontrollerV5.adapter;

import hello.servlet.web.ModelView;
import hello.servlet.web.frontcontrollerV5.MyHandlerAdapter;
import hello.servlet.web.frontcontrollerv4.ControllerV4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {


    @Override
    public boolean support(Object handler) {
        return handler instanceof ControllerV4;
    }

    @Override
    public ModelView handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;
        Map<String, Object> model = new HashMap<>();

        String process = controller.process(gerParameterMap(request), model);

        ModelView modelView = new ModelView(process);
        modelView.setModel(model);

        return modelView;
    }

    public Map<String, String> gerParameterMap(HttpServletRequest request) {
        return Collections.list(request.getParameterNames())
                .stream()
                .collect(Collectors.toMap(paramName -> paramName, request::getParameter));
    }

}

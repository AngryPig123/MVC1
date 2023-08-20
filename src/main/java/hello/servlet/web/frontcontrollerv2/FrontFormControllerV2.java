package hello.servlet.web.frontcontrollerv2;

import hello.servlet.web.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

public class FrontFormControllerV2 implements FrontControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("new-form");
    }

    @Override
    public Map<String, Object> getModel(HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            return Collections.list(headerNames)
                    .stream()
                    .collect(Collectors.toMap(item -> item, item -> request.getParameterMap()));
        } else {
            return null;
        }

    }

}

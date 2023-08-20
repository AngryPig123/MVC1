package hello.servlet.web.frontcontrollerv2;

import hello.servlet.web.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface FrontControllerV2 {

    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    Map<String, Object> getModel(HttpServletRequest request);

}

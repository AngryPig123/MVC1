package hello.servlet.web.frontcontrollerv3;

import hello.servlet.web.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);

}

package hello.servlet.web.frontcontrollerV5.adapter;

import hello.servlet.repotiroy.MemberRepository;
import hello.servlet.web.ModelView;
import hello.servlet.web.MyView;
import hello.servlet.web.frontcontrollerV5.MyHandlerAdapter;
import hello.servlet.web.frontcontrollerv3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontrollerv3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontrollerv3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontrollerv4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontrollerv4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontrollerv4.controller.MemberSaveControllerV4;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5(MemberRepository memberRepository) {
        initHandlerMappingMap(memberRepository);
        initHandlerAdapters();
    }

    private void initHandlerMappingMap(MemberRepository memberRepository) {
        handlerMappingMap.put("/front/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front/v5/v3/members/save", new MemberSaveControllerV3(memberRepository));
        handlerMappingMap.put("/front/v5/v3/members", new MemberListControllerV3(memberRepository));

        handlerMappingMap.put("/front/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front/v5/v4/members/save", new MemberSaveControllerV4(memberRepository));
        handlerMappingMap.put("/front/v5/v4/members", new MemberListControllerV4(memberRepository));

    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Object handler = getHandler(req);
        if (handler != null) {

            MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);
            ModelView modelView = handlerAdapter.handler(req, resp, handler);

            MyView view = viewResolver(modelView.getViewName());
            view.render(modelView.getModel(), req, resp);

        } else {
            log.error("error");
        }
z
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) throws NoSuchObjectException {
        return handlerAdapters.stream().filter(adapter -> adapter.support(handler)).findFirst().orElseThrow(() -> new NoSuchObjectException("오브젝트를 찾지 못했습니다."));
    }

    private Object getHandler(HttpServletRequest req) {
        return handlerMappingMap.get(req.getRequestURI());
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}











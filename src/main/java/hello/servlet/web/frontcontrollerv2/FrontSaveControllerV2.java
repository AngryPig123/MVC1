package hello.servlet.web.frontcontrollerv2;

import hello.servlet.domain.member.Member;
import hello.servlet.repotiroy.MemberRepository;
import hello.servlet.web.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

public class FrontSaveControllerV2 implements FrontControllerV2 {

    private final MemberRepository memberRepository;

    public FrontSaveControllerV2(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = memberRepository.save(
                Member.builder()
                        .username(username)
                        .age(age)
                        .build()
        );

        request.setAttribute("member",member);
        return new MyView("save-result");
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

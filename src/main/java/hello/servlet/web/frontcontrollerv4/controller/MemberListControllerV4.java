package hello.servlet.web.frontcontrollerv4.controller;

import hello.servlet.repotiroy.MemberRepository;
import hello.servlet.web.frontcontrollerv4.ControllerV4;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MemberListControllerV4 implements ControllerV4 {

    private final MemberRepository memberRepository;

    public MemberListControllerV4(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public String process(Map<String, String> parameterMap, Map<String, Object> model) {
        model.put("members", memberRepository.findAll());
        return "members";
    }

}

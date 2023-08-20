package hello.servlet.web.frontcontrollerv4.controller;


import hello.servlet.domain.member.Member;
import hello.servlet.repotiroy.MemberRepository;
import hello.servlet.web.ModelView;
import hello.servlet.web.frontcontrollerv4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private final MemberRepository memberRepository;

    public MemberSaveControllerV4(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public String process(Map<String, String> parameterMap, Map<String, Object> model) {

        String username = parameterMap.get("username");
        int age = Integer.parseInt(parameterMap.get("age"));

        Member save = memberRepository.save(
                Member.builder()
                        .username(username)
                        .age(age)
                        .build()
        );

        model.put("member",save);
        return "save-result";

    }

}

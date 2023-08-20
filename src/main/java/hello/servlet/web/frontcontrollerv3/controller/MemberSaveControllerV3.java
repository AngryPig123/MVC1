package hello.servlet.web.frontcontrollerv3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.repotiroy.MemberRepository;
import hello.servlet.web.ModelView;
import hello.servlet.web.frontcontrollerv3.ControllerV3;

import java.util.Map;


public class MemberSaveControllerV3 implements ControllerV3 {
    private final MemberRepository memberRepository;

    public MemberSaveControllerV3(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public ModelView process(Map<String, String> paramMap) {

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member save = memberRepository.save(
                Member.builder()
                        .username(username)
                        .age(age)
                        .build()
        );

        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", save);
        return modelView;

    }

}

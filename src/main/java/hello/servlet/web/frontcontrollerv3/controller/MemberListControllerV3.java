package hello.servlet.web.frontcontrollerv3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.repotiroy.MemberRepository;
import hello.servlet.web.ModelView;
import hello.servlet.web.frontcontrollerv3.ControllerV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MemberListControllerV3 implements ControllerV3 {

    private final MemberRepository memberRepository;

    public MemberListControllerV3(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public ModelView process(Map<String, String> paramMap) {

        List<Member> members = memberRepository.findAll();
        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members", members);
        return modelView;

    }

}

package hello.servlet.web.frontcontroller;

import hello.servlet.domain.member.Member;
import hello.servlet.repotiroy.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class MyMembersController implements MyFrontController {

    private final MemberRepository memberRepository;

    public MyMembersController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/members.jsp");
        request.setAttribute("members", members);
        requestDispatcher.forward(request, response);
    }

}

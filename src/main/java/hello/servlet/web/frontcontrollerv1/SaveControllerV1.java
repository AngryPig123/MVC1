package hello.servlet.web.frontcontrollerv1;

import hello.servlet.domain.member.Member;
import hello.servlet.repotiroy.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SaveControllerV1 implements FrontControllerV1 {

    private final MemberRepository memberRepository;

    public SaveControllerV1(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member save = memberRepository.save(Member.builder()
                .username(username)
                .age(age)
                .build());
        log.info("save = {}", save);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/save-result.jsp");
        request.setAttribute("member", save);
        requestDispatcher.forward(request, response);
    }

}

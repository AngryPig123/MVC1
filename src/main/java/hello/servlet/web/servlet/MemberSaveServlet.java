package hello.servlet.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.domain.member.Member;
import hello.servlet.repotiroy.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final TemplateEngine templateEngine;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        memberRepository.save(Member.builder()
                .username(username)
                .age(age)
                .build());

        List<Member> memberList = memberRepository.findAll();
        PrintWriter writer = resp.getWriter();
        writer.write(getListForm(memberList));

    }

    private String getListForm(List<Member> memberList) {
        Context context = new Context();
        context.setVariable("memberList", memberList);
        return templateEngine.process("member-list", context);
    }

}

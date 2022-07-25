package com.mysite.sbb.controller;

import com.mysite.sbb.dao.AnswerRepository;
import com.mysite.sbb.domain.Answer;
import com.mysite.sbb.domain.AnswerForm;
import com.mysite.sbb.domain.Question;
import com.mysite.sbb.domain.SiteUser;
import com.mysite.sbb.service.AnswerService;
import com.mysite.sbb.service.QuestionService;
import com.mysite.sbb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
@RequiredArgsConstructor
@Controller
@RequestMapping("/answer")
public class AnswerController {

    private final QuestionService questionService;

    private final AnswerService answerService;

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    /*
    PreAuthorize : 검문을 실행함
    isAuthenticated() : 로그인이 되어있는지
    해석 : 로그인이 되어있는지 검문함
    */
    @PostMapping("create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "/usr/question/question_detail";
        }

        this.answerService.create(question, answerForm.getContent(), siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }

}

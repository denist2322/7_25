package com.mysite.sbb.controller;

import com.mysite.sbb.dao.QuestionRepository;
import com.mysite.sbb.domain.AnswerForm;
import com.mysite.sbb.domain.Question;
import com.mysite.sbb.domain.QuestionForm;
import com.mysite.sbb.domain.SiteUser;
import com.mysite.sbb.service.QuestionService;
import com.mysite.sbb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    private final UserService userService;

    @RequestMapping("list")
    public String showList(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page);
        model.addAttribute("paging", paging);
        return "usr/question/question_list";
    }

    @RequestMapping("detail/{id}")
    public String showDetail(@PathVariable("id") Integer id, Model model, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "usr/question/question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "usr/question/question_form";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "usr/question/question_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list";
    }
}

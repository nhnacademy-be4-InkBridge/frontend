package com.nhnacademy.inkbridge.front.controller;

import com.nhnacademy.inkbridge.front.dto.tag.Tag;
import com.nhnacademy.inkbridge.front.dto.tag.TagCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagUpdateRequestDto;
import com.nhnacademy.inkbridge.front.service.TagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * class: AdminTagController.
 *
 * @author jeongbyeonghun
 * @version 3/4/24
 */
@Controller
@RequestMapping("/admin/tags")
@RequiredArgsConstructor
public class AdminTagController {

    private final TagService tagService;
    private static final String TAG_URL = "redirect:/admin/tags";

    @GetMapping
    public String getTagList(Model model) {
        List<Tag> tagList = tagService.getTagList();
        model.addAttribute("tagList", tagList);
        return "/admin/tag-list";
    }

    @PostMapping("/create")
    public String createTag(@ModelAttribute TagCreateRequestDto newTag) {
        tagService.createTag(newTag);
        return TAG_URL;
    }

    @PutMapping("/update/{tagId}")
    public String modifyTag(@ModelAttribute TagUpdateRequestDto tagUpdateRequestDto,
        @PathVariable Long tagId) {
        tagService.updateTag(tagId, tagUpdateRequestDto);
        return TAG_URL;
    }

    @PostMapping("/delete/{tagId}")
    public String deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
        return TAG_URL;
    }

    @ExceptionHandler(value = Exception.class)
    public String tagExceptionHandler(Exception e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getMessage());
        return TAG_URL;
    }
}

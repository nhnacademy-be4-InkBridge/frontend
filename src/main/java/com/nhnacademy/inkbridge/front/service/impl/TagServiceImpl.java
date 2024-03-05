package com.nhnacademy.inkbridge.front.service.impl;

import com.nhnacademy.inkbridge.front.adaptor.TagAdaptor;
import com.nhnacademy.inkbridge.front.dto.tag.Tag;
import com.nhnacademy.inkbridge.front.dto.tag.TagCreateRequestDto;
import com.nhnacademy.inkbridge.front.dto.tag.TagUpdateRequestDto;
import com.nhnacademy.inkbridge.front.service.TagService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * class: TagServiceImpl.
 *
 * @author jeongbyeonghun
 * @version 3/4/24
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagAdaptor tagAdaptor;

    @Override
    public List<Tag> getTagList() {
        return tagAdaptor.getTagList().stream().map(Tag::new).collect(Collectors.toList());
    }

    @Override
    public void createTag(TagCreateRequestDto newTag) {
        tagAdaptor.createTag(newTag);
    }

    @Override
    public void deleteTag(Long tagId) {
        tagAdaptor.deleteTag(tagId);
    }

    @Override
    public void updateTag(Long tagId, TagUpdateRequestDto tagUpdateRequestDto) {
        tagAdaptor.updateTag(tagId, tagUpdateRequestDto);
    }
}

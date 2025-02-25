package com.app.delicare.service;

import com.app.delicare.entitys.category.Title;
import com.app.delicare.dtos.category.TitleDTO;
import com.app.delicare.entitys.user.User;
import com.app.delicare.mappers.TitleMapper;
import com.app.delicare.repositories.category.TitleRepository;
import com.app.delicare.repositories.user.UserRepository;
import com.app.delicare.responses.category.TitleResponse;
import com.app.delicare.service.implement.ITtiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TitleService implements ITtiteService {
    private final TitleRepository titleRepository;
    private final UserRepository userRepository;
    private final TitleMapper titleMapper;
    @Override
    public TitleResponse createTitle(TitleDTO titleDTO) {
        User user = userRepository.getReferenceById(titleDTO.getCreatedById());
        Title newTitle = titleMapper.mapEntityToModel(titleDTO);
        newTitle.setCreatedByUser(user);
        titleRepository.save(newTitle);
        titleRepository.flush();
        return titleMapper.mapResponseToEntity(newTitle);
    }

    @Override
    public List<TitleResponse> getAllTitle() {
        return titleRepository.findAll().stream().map(title -> {
            return titleMapper.mapResponseToEntity(title);
        }).toList();
    }

    @Override
    public Page<TitleResponse> getListPageTitle(PageRequest pageRequest) {
        return titleRepository.findAll(pageRequest).map(title -> {
           return titleMapper.mapResponseToEntity(title);
        });
    }

    @Override
    public TitleResponse getTitleById(Long id) {
        Title title = titleRepository.findById(id).orElseThrow(() -> new RuntimeException("title is null"));
        return titleMapper.mapResponseToEntity(title);
    }

    @Override
    public TitleResponse updateTitle(Long titleId, TitleDTO titleDTO) {
        User user = userRepository.getReferenceById(titleDTO.getModifiedById());
        Title existstingTitle = titleRepository.getReferenceById(titleId);
        existstingTitle.setTitleCode(titleDTO.getTitleCode());
        existstingTitle.setTitleName(titleDTO.getTitleCode());
        existstingTitle.setTitleType(titleDTO.getTitleType());
        existstingTitle.setModifiedByUser(user);
        titleRepository.save(existstingTitle);
        return titleMapper.mapResponseToEntity(existstingTitle);
    }

    @Override
    public void deleteTitle(Long id) {
        titleRepository.deleteById(id);
    }
}

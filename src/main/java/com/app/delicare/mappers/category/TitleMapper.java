package com.app.delicare.mappers.category;

import com.app.delicare.dtos.category.TitleDTO;
import com.app.delicare.entitys.category.Title;
import com.app.delicare.mappers.base.BaseMapper;
import com.app.delicare.responses.category.TitleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TitleMapper extends BaseMapper {
    public Title mapEntityToModel(TitleDTO titleDTO){
        Title title = Title.builder()
                .titleCode(titleDTO.getTitleCode())
                .titleName(titleDTO.getTitleName())
                .titleType(titleDTO.getTitleType())
                .build();
        title.setStatus(titleDTO.getStatus());
        title.setDescription(titleDTO.getDescription());
        return title;
    }
    public Title mapEntityToModel(Long id, TitleDTO titleDTO){
        Title title = mapEntityToModel(titleDTO);
        title.setId(id);
        return title;
    }
    public TitleResponse mapResponseToEntity(Title title){
        TitleResponse titleResponse = TitleResponse.builder()
                .id(title.getId())
                .titleCode(title.getTitleCode())
                .titleName(title.getTitleName())
                .titleType(title.getTitleType())
                .build();
        Optional.ofNullable(title)
                .map(Title::getCreatedByUser)
                .ifPresent(user1 -> {
                    titleResponse.setCreateByUserResponse(mapCreateByUserResponseToEntity(title.getCreatedByUser()));
                });
        Optional.ofNullable(title)
                .map(Title::getCreatedByUser)
                .ifPresent(user1 -> {
                    titleResponse.setModifiedByUserResponse(mapModifiedByUserResponseToEntity(title.getModifiedByUser()));
                });
        titleResponse.setDeleted(title.getDeleted());
        titleResponse.setDescription(title.getDescription());
        titleResponse.setCreatedAt(title.getCreatedAt());
        titleResponse.setCreatedAt(title.getCreatedAt());
        titleResponse.setModifiedAt(title.getModifiedAt());
        titleResponse.setStatus(title.getStatus());
        return titleResponse;
    }
}

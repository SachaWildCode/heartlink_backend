package fr.slghive.heartlink.dtos.type.type_post;

import fr.slghive.heartlink.entities.TypeEntity;

public class TypePostMapper {

    public static TypePostResponse toDto(TypeEntity entity) {

        return new TypePostResponse(entity.getId(), entity.getName());
    }

    public static TypeEntity toEntity(TypePostRequest request) {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setName(request.name());
        return typeEntity;
    }
}

package fr.slghive.heartlink.dtos.type.type_post;

import fr.slghive.heartlink.entities.TypeEntity;

public class TypePostMapper {

    public static TypePostResponse toDto(TypeEntity entity) {
        return new TypePostResponse(entity.getId(), entity.getLibTheme());
    }

    public static TypeEntity toEntity(TypePostRequest request) {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setLibTheme(request.libTheme());
        return typeEntity;
    }
}

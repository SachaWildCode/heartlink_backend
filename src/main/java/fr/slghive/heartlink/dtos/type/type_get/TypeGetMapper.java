package fr.slghive.heartlink.dtos.type.type_get;

import fr.slghive.heartlink.entities.TypeEntity;

public class TypeGetMapper {

    public static TypeGetResponse toDto(TypeEntity type) {
        return new TypeGetResponse(type.getId(), type.getLibTheme(), type.getColor());
    }
}

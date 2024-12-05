package ru.ssau.tk.jabalab.lr2.services;

import ru.ssau.tk.jabalab.lr2.DTO.PointDTO;
import ru.ssau.tk.jabalab.lr2.entity.PointEntity;

public class PointMapper {

    public static PointDTO pointEntityToDTO(PointEntity entity) {
        if (entity == null) {
            return null;
        }

        PointDTO dto = new PointDTO();
        dto.setId(entity.getId());
        dto.setFunctionId(entity.getFunctionEntity() != null ? entity.getFunctionEntity().getId() : 0);
        dto.setXVal(entity.getXVal() != null ? entity.getXVal() : 0.0);
        dto.setYVal(entity.getYVal() != null ? entity.getYVal() : 0.0);

        return dto;
    }

    public static PointEntity pointDTOToPointEntity(PointDTO dto) {
        if (dto == null) {
            return null;
        }

        PointEntity entity = new PointEntity();
        entity.setId(dto.getId());
        entity.setXVal(dto.getXVal());
        entity.setYVal(dto.getYVal());

        return entity;
    }

}
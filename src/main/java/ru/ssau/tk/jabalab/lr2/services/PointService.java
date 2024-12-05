package ru.ssau.tk.jabalab.lr2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.tk.jabalab.lr2.DTO.PointDTO;
import ru.ssau.tk.jabalab.lr2.entity.MathFunctionEntity;
import ru.ssau.tk.jabalab.lr2.entity.PointEntity;
import ru.ssau.tk.jabalab.lr2.repository.MathFunctionRepository;
import ru.ssau.tk.jabalab.lr2.repository.PointRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    private final MathFunctionRepository mathFunctionRepository;

    public List<PointDTO> findAllPoints(int functionId) {
        MathFunctionEntity function = this.mathFunctionRepository.findById(functionId).orElse(null);
        if (function == null) {
            return null;
        }

        return this.pointRepository.findByFunctionEntity(function)
                .stream()
                .map(PointMapper::pointEntityToDTO)
                .collect(Collectors.toList());
    }

    public PointDTO create(PointDTO pointDTO) {
        PointEntity point = PointMapper.pointDTOToPointEntity(pointDTO);
        PointEntity newPoint = this.pointRepository.save(point);

        return PointMapper.pointEntityToDTO(newPoint);
    }

    public PointDTO read(int id) {
        return this.pointRepository.findById(id)
                .map(PointMapper::pointEntityToDTO)
                .orElse(null);
    }

    public PointDTO update(PointDTO pointDTO) {
        PointEntity point = PointMapper.pointDTOToPointEntity(pointDTO);
        PointEntity editedPoint = this.pointRepository.save(point);

        return PointMapper.pointEntityToDTO(editedPoint);
    }

    public void delete(int id) {
        this.pointRepository.deleteById(id);
    }

}
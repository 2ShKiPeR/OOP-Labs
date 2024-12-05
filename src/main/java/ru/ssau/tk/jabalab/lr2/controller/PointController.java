package ru.ssau.tk.jabalab.lr2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.jabalab.lr2.DTO.PointDTO;
import ru.ssau.tk.jabalab.lr2.services.PointService;

import java.util.List;


public class PointController {
    @RestController
    @RequiredArgsConstructor
    @RequestMapping("lab/points/")
    public class PointRestController {

        private final PointService pointService;

        @GetMapping("function/{functionId:\\d+}")
        public ResponseEntity<List<PointDTO>> findAllPoints(@PathVariable int functionId) {
            List<PointDTO> pointDTOList = this.pointService.findAllPoints(functionId);
            if (pointDTOList == null)
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(pointDTOList);
        }

        @PostMapping
        public ResponseEntity<PointDTO> create(@RequestBody PointDTO pointDTO) {
            PointDTO createdPoint = this.pointService.create(pointDTO);
            return ResponseEntity.ok(createdPoint);
        }

        @GetMapping("{pointId:\\d+}")
        public ResponseEntity<PointDTO> read(@PathVariable int pointId) {
            PointDTO pointDTO = this.pointService.read(pointId);
            if (pointDTO == null)
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(pointDTO);
        }

        @PatchMapping("{pointId:\\d+}")
        public ResponseEntity<PointDTO> update(@RequestBody PointDTO pointDTO, @PathVariable int pointId) {
            pointDTO.setId(pointId);
            PointDTO editedPoint = this.pointService.update(pointDTO);

            return ResponseEntity.ok(editedPoint);
        }

        @DeleteMapping("{pointId:\\d+}")
        public ResponseEntity<Void> delete(@PathVariable int pointId) {
            this.pointService.delete(pointId);

            return ResponseEntity.noContent().build();
        }

    }
}

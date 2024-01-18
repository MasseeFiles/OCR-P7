//package com.nnk.springboot.services;
//
//import com.nnk.springboot.domain.CurvePoint;
//import com.nnk.springboot.repositories.CurvePointRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CurvePointServiceImpl implements CurvePointService {
//    @Autowired
//    private CurvePointRepository curvePointRepository;
//
//    @Override
//    public List<CurvePoint> findAll() {
//        return curvePointRepository.findAll();
//    }
//
//    @Override
//    public CurvePoint findById(Integer id) {
//        CurvePoint curvePointToFind = curvePointRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("CurvePoint not found : Id used " + id));
//
//        return curvePointToFind;
//    }
//
//    @Override
//    public void save(CurvePoint curvePoint) {
//        curvePointRepository.save(curvePoint);
//    }
//
//    @Override
//    public void update(CurvePoint curvePoint) {
//        CurvePoint curvePointToUpdate = curvePointRepository.findById(curvePoint.getId())
//                .orElseThrow(() -> new RuntimeException("CurvePoint to update not found : Id used " + curvePoint.getId()));
//
//        curvePointRepository.delete(curvePointToUpdate);
//        curvePointRepository.save(curvePoint);
//    }
//
//    @Override
//    public void delete(Integer id) {
//        CurvePoint curvePointToDelete = curvePointRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("CurvePoint to delete not found : Id used " + id));
//
//        curvePointRepository.delete(curvePointToDelete);
//    }
//}

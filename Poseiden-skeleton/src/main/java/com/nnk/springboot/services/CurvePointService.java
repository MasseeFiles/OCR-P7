package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

public interface CurvePointService {
    List<CurvePoint> findAll();

    CurvePoint findById(Integer id);

    void add(CurvePoint curvePoint);

    void update(CurvePoint curvePoint);

    void delete(Integer id);
}

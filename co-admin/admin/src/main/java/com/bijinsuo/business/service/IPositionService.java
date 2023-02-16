package com.bijinsuo.business.service;


import com.bijinsuo.business.entity.Position;

import java.util.List;

public interface IPositionService {
  List<Position> selectPositionList(Position position);
}

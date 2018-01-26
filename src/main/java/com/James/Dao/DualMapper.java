package com.James.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.James.Model.Dual;


/**
 * Created by James on 2017/7/5.
 */
public interface DualMapper {

  @Select("select '1' as DUMMY from dual")
  List<Dual> selectOne();

  @Select("select count(1) from dual")
  int count();

}

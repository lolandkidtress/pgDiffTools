package com.James.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.James.Model.PgColumn;


/**
 * Created by James on 2018/1/24.
 */
public interface ColumnMapper {

  @Select("SELECT table_schema as Schema\n"
      + "    , table_schema || '.' || table_name || '.' || column_name  AS compareName\n"
      + "        , table_name as TabName\n" + "    , column_name as columnName\n" + "    , data_type\n"
      + "    , is_nullable\n" + "    , column_default\n" + "    , character_maximum_length\n" + "    , is_identity\n"
      + "    , identity_generation\n" + "FROM information_schema.columns \n" + "WHERE is_updatable = 'YES'\n"
      + "AND table_schema NOT LIKE 'pg_%' \n" + "AND table_schema <> 'information_schema' \n"
      + "ORDER BY compareName ASC;")
  List<PgColumn> selectAll();
}

package com.James.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.James.Configure.pgToolsConfig;
import com.James.Dao.ColumnMapper;
import com.James.Model.ColumnDiffResult;
import com.James.Model.PgColumn;


/**
 * Created by James on 2018/1/25.
 */
@Service
public class ColumnDiffSevice {

  public List<ColumnDiffResult> getDiff() {
    SqlSession lSession = pgToolsConfig.getlSqlSessionFactory().openSession();
    SqlSession rSession = pgToolsConfig.getrSqlSessionFactory().openSession();

    List<PgColumn> lPgColumns = lSession.getMapper(ColumnMapper.class).selectAll();
    List<PgColumn> rPgColumns = rSession.getMapper(ColumnMapper.class).selectAll();

    //key:columnname
    ConcurrentHashMap<String,ColumnDiffResult> diffResultsMap = new ConcurrentHashMap<>();

    lPgColumns.stream().forEach(pgColumn->{
      ColumnDiffResult columnDiffResult = new ColumnDiffResult(pgColumn.getColumnName());
      columnDiffResult.addlInfos(pgColumn);
      columnDiffResult.setlExist(true);

      diffResultsMap.put(pgColumn.getTabName(), columnDiffResult);

    });

    rPgColumns.stream().forEach(pgColumn->{
      //没有就新增进map
      ColumnDiffResult columnDiffResult = diffResultsMap.getOrDefault(pgColumn.getColumnName(),new ColumnDiffResult(pgColumn.getColumnName()));
      columnDiffResult.addrInfos(pgColumn);
      columnDiffResult.setrExist(true);

    });

    List<ColumnDiffResult> diffResultsList = new ArrayList<>();

    diffResultsMap.values().forEach(columnDiffResult->{
      if(columnDiffResult.checkDiff()){
        diffResultsList.add(columnDiffResult);
      }
    });

    return diffResultsList;
  }
}

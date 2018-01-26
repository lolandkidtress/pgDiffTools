package com.James.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.James.Configure.pgToolsConfig;
import com.James.Dao.TableMapper;
import com.James.Model.PgTable;
import com.James.Model.TableDiffResult;


/**
 * Created by James on 2018/1/24.
 */
@Service
public class TableDiffService {

  public List<TableDiffResult> getDiff(){
    SqlSession lSession = pgToolsConfig.getlSqlSessionFactory().openSession();
    SqlSession rSession = pgToolsConfig.getrSqlSessionFactory().openSession();

    List<PgTable> lPgTables = lSession.getMapper(TableMapper.class).selectAll();
    List<PgTable> rPgTables = rSession.getMapper(TableMapper.class).selectAll();

    //key:tablename
    ConcurrentHashMap<String,TableDiffResult> diffResultsMap = new ConcurrentHashMap<>();

    lPgTables.stream().forEach(pgTable->{
      TableDiffResult tableDiffResult = new TableDiffResult(pgTable.getTabName());
      tableDiffResult.addlSchemaInfo(pgTable.getSchema());
      tableDiffResult.setlExist(true);

      diffResultsMap.put(pgTable.getTabName(), tableDiffResult);

    });

    rPgTables.stream().forEach(pgTable->{
      //没有就新增进map
      TableDiffResult tableDiffResult = diffResultsMap.getOrDefault(pgTable.getTabName(),new TableDiffResult(pgTable.getTabName()));
      tableDiffResult.addrSchemaInfo(pgTable.getSchema());
      tableDiffResult.setrExist(true);

    });

    List<TableDiffResult> diffResultsList = new ArrayList<>();

    diffResultsMap.values().forEach(tableDiffResult->{
      System.out.println(tableDiffResult.getTabName());
      if(tableDiffResult.checkDiff()){
        diffResultsList.add(tableDiffResult);
      }
    });

    return diffResultsList;
  }
}

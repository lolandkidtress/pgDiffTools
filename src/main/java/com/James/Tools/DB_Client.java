package com.James.Tools;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.James.Dao.ColumnMapper;
import com.James.Dao.DualMapper;
import com.James.Dao.TableMapper;
import com.James.Dao.ViewMapper;

import javax.sql.DataSource;


public class DB_Client {

    private static Logger LOGGER = LogManager.getLogger(DB_Client.class.getClass());

    public SqlSessionFactory createSqlSessionFactory(String url,String username,String password){
        //
        // Get DataSource object.
        //
        DataSource dataSource =
            createDataSourceConfig(url, username, password);

        //
        // Creates a transaction factory.
        //
        TransactionFactory trxFactory = new JdbcTransactionFactory();

        //
        // Creates an environment object with the specified name, transaction
        // factory and a data source.
        //
        Environment env = new Environment("dev", trxFactory, dataSource);

        //DualMapper
        // Creates a Configuration object base on the Environment object.
        // We can also add type aliases and mappers.
        //
        Configuration config = new Configuration(env);
//        TypeAliasRegistry aliases = config.getTypeAliasRegistry();
//        aliases.registerAlias("record", Dual.class);



        //
        // Build the SqlSessionFactory based on the created Configuration object.
        // Open a session and query a record using the RecordMapper.
        //
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        factory.getConfiguration().addMapper(DualMapper.class);
        factory.getConfiguration().addMapper(TableMapper.class);
        factory.getConfiguration().addMapper(ViewMapper.class);
        factory.getConfiguration().addMapper(ColumnMapper.class);



        return factory;
    }

    /**
     * create a DataSource object.
     *
     * @return a DataSource.
     */
    public DataSource createDataSourceConfig(String url,String username,String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

}

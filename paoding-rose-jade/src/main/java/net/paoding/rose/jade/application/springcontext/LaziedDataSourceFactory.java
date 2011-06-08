package net.paoding.rose.jade.application.springcontext;

import java.util.Map;

import javax.sql.DataSource;

import net.paoding.rose.jade.dataaccess.DataSourceFactory;
import net.paoding.rose.jade.statement.StatementMetaData;

import org.springframework.beans.factory.ListableBeanFactory;

/**
 * 
 * @author qieqie
 * 
 */
public class LaziedDataSourceFactory implements DataSourceFactory {

    private ListableBeanFactory beanFactory;

    private DataSourceFactory dataSourceFactory;

    public LaziedDataSourceFactory(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public DataSource getDataSource(StatementMetaData metaData,
            Map<String, Object> runtimeProperties) {
        if (dataSourceFactory == null) {
            ListableBeanFactory beanFactory = this.beanFactory;
            if (beanFactory != null) {
                @SuppressWarnings("unchecked")
                Map<String, Object> beansOfType = beanFactory
                        .getBeansOfType(DataSourceFactory.class);
                if (beansOfType.size() > 0) {
                    dataSourceFactory = (DataSourceFactory) beansOfType.values().iterator().next();
                } else {
                    dataSourceFactory = new DefaultDataSourceFactory(beanFactory);
                }
                this.beanFactory = null;
            }
        }
        return dataSourceFactory.getDataSource(metaData, runtimeProperties);
    }

}

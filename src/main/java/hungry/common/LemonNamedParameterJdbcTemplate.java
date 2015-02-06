package hungry.common;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class LemonNamedParameterJdbcTemplate {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public <T> List<T> query(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper)
            throws DataAccessException {
        return namedParameterJdbcTemplate.query(sql, paramSource, rowMapper);
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws DataAccessException {
        return namedParameterJdbcTemplate.query(sql, rowMapper);
    }

    public int update(String sql, SqlParameterSource paramSource) throws DataAccessException {
        return namedParameterJdbcTemplate.update(sql,paramSource);
    }

    //パラメータの文字列変換処理
    private String getParameterString(SqlParameterSource paramSource) {
        StringBuilder params = new StringBuilder();
        params.append("[");
        if(paramSource instanceof BeanPropertySqlParameterSource) {
            BeanPropertySqlParameterSource beanParamSource = (BeanPropertySqlParameterSource) paramSource;
            String[] paramNames = beanParamSource.getReadablePropertyNames();
            for(String key : paramNames) {
                params.append(key);
                params.append(":");
                params.append(beanParamSource.getValue(key));
                params.append(",");
            }
        }else if (paramSource instanceof MapSqlParameterSource){
            MapSqlParameterSource mapParamSource = (MapSqlParameterSource) paramSource;
            Map<String,Object> paramNames = mapParamSource.getValues();
            for(String key : paramNames.keySet()) {
                params.append(key);
                params.append(":");
                params.append(paramNames.get(key));
                params.append(",");
            }
        }else if (paramSource instanceof BeanPropertySqlParameterSource){
        }
        params.append("]");
        return params.toString();
    }

    private String getParameterString(SqlParameterSource[] batchArgs) {
        StringBuilder params = new StringBuilder();
        params.append("[");
        for (SqlParameterSource source : batchArgs) {
            params.append(getParameterString(source));
        }
        params.append("]");
        return params.toString();
    }

    private String getParameterString(Map<String, ?>[] batchValues) {
        StringBuilder params = new StringBuilder();
        params.append("[");
        for (Map<String, ?> batchValue : batchValues) {
            params.append(getParameterString(batchValue));
        }
        params.append("]");
        return (batchValues.toString());
    }

    private String getParameterString(Map<String, ?> paramMap) {
        StringBuilder params = new StringBuilder();
        params.append("[");
        for(String key : paramMap.keySet()) {
            params.append(key);
            params.append(":");
            params.append(paramMap.get(key));
            params.append(",");
        }
        params.append("]");
        return params.toString();
    }
}

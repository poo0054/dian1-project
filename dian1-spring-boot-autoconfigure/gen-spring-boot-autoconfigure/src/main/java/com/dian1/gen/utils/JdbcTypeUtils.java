package com.dian1.gen.utils;

import cn.hutool.core.util.ClassUtil;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.mysql.cj.MysqlType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * @author zhangzhi
 * @date 2023/3/20
 */
public class JdbcTypeUtils implements ApplicationContextAware {
    private static TypeHandlerRegistry typeHandlerRegistry;

    public static String getJavaType(String columnType) {
        MysqlType mysqlType = MysqlType.getByName(columnType);
        Assert.notNull(mysqlType, "获取mysqlType失败");
        TypeHandlerRegistry typeHandlerRegistry = getTypeHandlerRegistry();
        Assert.notNull(typeHandlerRegistry, "mybatis全局对象获取失败");
        TypeHandler<?> typeHandler = typeHandlerRegistry.getTypeHandler(JdbcType.forCode(mysqlType.getJdbcType()));
        Assert.notNull(typeHandlerRegistry, "获取typeHandler失败,未找到" + mysqlType + "的类型转换器");
        Class<?> typeArgument = ClassUtil.getTypeArgument(typeHandler.getClass());
        Assert.notNull(typeArgument, "获取泛型失败,未找到泛型");
        return typeArgument.getSimpleName();
    }

    private static TypeHandlerRegistry getTypeHandlerRegistry() {
        if (null == typeHandlerRegistry) {
            SqlSessionFactory sqlSessionFactory = GlobalConfigUtils.defaults().getSqlSessionFactory();
            Assert.notNull(sqlSessionFactory, "获取sqlSessionFactory ");
            return sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
        }
        return typeHandlerRegistry;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SqlSessionFactory sessionFactory = applicationContext.getBean(SqlSessionFactory.class);
        Configuration configuration = sessionFactory.getConfiguration();
        typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    }
}

package com.study.frame.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @Author：xiangguangye
 * @Desc：
 * @Date：2020/7/13 13:45
 */
public class DubboAdmin implements InitializingBean, ServletContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(DubboAdmin.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        LOGGER.info("===== 开始解压zheng-admin =====");
        String version = PropertiesFileUtil.getInstance("zheng-admin-client").get("zheng.admin.version");
        LOGGER.info("zheng-admin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/zheng-admin-" + version + ".jar");
        LOGGER.info("zheng-admin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/zheng-admin";
        LOGGER.info("zheng-admin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        LOGGER.info("===== 解压zheng-admin完成 =====");
    }
}

/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.mallcloud.admin.config;

import cn.mallcloud.admin.event.RoutesOutdatedEvent;
import cn.mallcloud.admin.registry.ApplicationRegistry;
import cn.mallcloud.admin.web.client.HttpHeadersProvider;
import cn.mallcloud.admin.zuul.ApplicationRouteLocator;
import cn.mallcloud.admin.zuul.filters.pre.ApplicationHeadersFilter;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientConnectionManagerFactory;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientFactory;
import org.springframework.cloud.commons.httpclient.HttpClientConfiguration;
import org.springframework.cloud.netflix.zuul.RoutesEndpoint;
import org.springframework.cloud.netflix.zuul.ZuulServerAutoConfiguration;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.TraceProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter;
import org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

/**
 * 服务治理 zuul反向代理配置
 *
 * @author liuht
 * @date 2017/12/27 20:49
 */
@Configuration
@AutoConfigureAfter( {MallcloudAdminServerAutoConfigration.class})
@Import(HttpClientConfiguration.class)
public class MallcloudRevereseZuulProxyConfiguration extends ZuulServerAutoConfiguration {

    @Autowired
    private ApplicationRegistry registry;

    @Autowired
    private MallcloudAdminServerProperties adminServer;

    @Autowired
    private HttpTraceRepository traceRepository;

    @Bean
    @Order(0)
    public ApplicationRouteLocator applicationRouteLocator() {
        ApplicationRouteLocator routeLocator = new ApplicationRouteLocator(this.server.getServlet().getPath(), registry,
                adminServer.getContextPath() + "/api/applications/");
        routeLocator.setEndpoints(adminServer.getRoutes().getEndpoints());
        return routeLocator;
    }

    @Bean
    public ProxyRequestHelper proxyRequestHelper() {
        TraceProxyRequestHelper helper = new TraceProxyRequestHelper();
        helper.setTraces(traceRepository);
        helper.setIgnoredHeaders(this.zuulProperties.getIgnoredHeaders());
        helper.setTraceRequestBody(this.zuulProperties.isTraceRequestBody());
        return helper;
    }

    /**
     *  pre filters
     */
    @Bean
    public PreDecorationFilter preDecorationFilter(RouteLocator routeLocator) {
        return new PreDecorationFilter(routeLocator, this.server.getServlet().getPath(), zuulProperties,
                proxyRequestHelper());
    }

    @Bean
    public ApplicationHeadersFilter applicationHeadersFilter(ApplicationRouteLocator routeLocator,
                                                             HttpHeadersProvider headersProvider) {
        return new ApplicationHeadersFilter(headersProvider, routeLocator);
    }

    @Bean
    @ConditionalOnMissingBean({SimpleHostRoutingFilter.class, CloseableHttpClient.class})
    public SimpleHostRoutingFilter simpleHostRoutingFilter(ProxyRequestHelper helper,
                                                           ZuulProperties zuulProperties,
                                                           ApacheHttpClientConnectionManagerFactory connectionManagerFactory,
                                                           ApacheHttpClientFactory httpClientFactory) {
        return new SimpleHostRoutingFilter(helper, zuulProperties,
                connectionManagerFactory, httpClientFactory);
    }

    @Bean
    @ConditionalOnMissingBean({SimpleHostRoutingFilter.class})
    public SimpleHostRoutingFilter simpleHostRoutingFilter2(ProxyRequestHelper helper,
                                                            ZuulProperties zuulProperties,
                                                            CloseableHttpClient httpClient) {
        return new SimpleHostRoutingFilter(helper, zuulProperties,
                httpClient);
    }

    /**
     * 路由刷新监听器
     */
    @Bean
    @Override
    public ApplicationListener<ApplicationEvent> zuulRefreshRoutesListener() {
        return new ZuulRefreshListener(zuulHandlerMapping(null));
    }

    @Configuration
    @ConditionalOnClass(Endpoint.class)
    protected static class RoutesEndpointConfiguration {
        @Bean
        public RoutesEndpoint zuulEndpoint(RouteLocator routeLocator) {
            return new RoutesEndpoint(routeLocator);
        }
    }

    private static class ZuulRefreshListener implements ApplicationListener<ApplicationEvent> {
        private ZuulHandlerMapping zuulHandlerMapping;

        private ZuulRefreshListener(ZuulHandlerMapping zuulHandlerMapping) {
            this.zuulHandlerMapping = zuulHandlerMapping;
        }

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            if (event instanceof PayloadApplicationEvent &&
                    ((PayloadApplicationEvent<?>) event).getPayload() instanceof RoutesOutdatedEvent) {
                zuulHandlerMapping.setDirty(true);
            }
        }
    }
}

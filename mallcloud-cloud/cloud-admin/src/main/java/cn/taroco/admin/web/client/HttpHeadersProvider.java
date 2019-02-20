/*
 * Copyright 2013-2016 the original author or authors.
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
package cn.mallcloud.admin.web.client;

import cn.mallcloud.admin.model.Instance;
import org.springframework.http.HttpHeaders;

/**
 * 根据application获取指定header
 *
 * @author liuht
 * @date 2017/12/27 20:49
 */
public interface HttpHeadersProvider {

    /**
     * 读取实例所需Header
     *
     * @param instance 实例
     * @return HttpHeaders
     */
    HttpHeaders getHeaders(Instance instance);

}

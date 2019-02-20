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
package cn.mallcloud.admin.event;

import cn.mallcloud.admin.model.Instance;
import lombok.Data;

import java.io.Serializable;

/**
 * Abstract Event regearding spring boot admin clients
 *
 * @author liuht
 */
@Data
public class ClientApplicationEvent implements Serializable {

	private static final long serialVersionUID = -5376293152556637178L;
	private final String serviceId;
	private final Instance instance;

	private final long timestamp;
	private final String type;

	protected ClientApplicationEvent(String serviceId, Instance instance, String type) {
		this.serviceId = serviceId;
		this.instance = instance;
		this.timestamp = System.currentTimeMillis();
		this.type = type;
	}
}

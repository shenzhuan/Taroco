package cn.mallcloud.admin.web.client;

import cn.mallcloud.admin.model.Application;
import cn.mallcloud.admin.model.Instance;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * Provides Basic Auth headers for the {@link Application} using the metadata for "user.name" and
 * "user.password".
 *
 * @author liuht
 * @date 2017/12/27 20:49
 */
public class BasicAuthHttpHeaderProvider implements HttpHeadersProvider {

	@Override
	public HttpHeaders getHeaders(Instance instance) {
		String username = instance.getMetadata().get("user.name");
		String password = instance.getMetadata().get("user.password");

		HttpHeaders headers = new HttpHeaders();

		if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
			headers.set(HttpHeaders.AUTHORIZATION, encode(username, password));
		}

		return headers;
	}

	protected String encode(String username, String password) {
		String token = Base64Utils
				.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
		return "Basic " + token;
	}

}

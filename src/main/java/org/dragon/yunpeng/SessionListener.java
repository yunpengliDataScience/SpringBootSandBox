package org.dragon.yunpeng;

import java.util.List;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Component
public class SessionListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {

		System.out.println("ApplicationEvent==>" + event);

		if (event instanceof HttpSessionDestroyedEvent) {
			System.out.println("HttpSessionDestroyedEvent detected!");

			List<SecurityContext> lstSecurityContext = ((HttpSessionDestroyedEvent) event).getSecurityContexts();

			for (SecurityContext securityContext : lstSecurityContext) {
				Authentication authentication = securityContext.getAuthentication();
				if (authentication != null) {
					System.out.println("Principal: " + authentication.getPrincipal());
				}
			}

		} else if (event instanceof HttpSessionCreatedEvent) {
			System.out.println("HttpSessionCreatedEvent detected!");
		}
	}
}

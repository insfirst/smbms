package cn.jbit.smbms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.jbit.smbms.Util.CommentData;
/**
 * 拦截器
 * @author Administrator
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(null!=request.getSession().getAttribute(CommentData.LOGIN_SESSION)) {
			return true;
		}else {
			response.sendRedirect(request.getContextPath()+"/index");
			return false;
		}
		
	}
}

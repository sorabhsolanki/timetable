package server.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import server.util.CommonConstants;

/**
 */
public class AuthFilter implements Filter{

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    Cookie[] cookies = httpServletRequest.getCookies();

    if(cookies != null) {
      for (Cookie cookie : cookies) {
        if (CommonConstants.TOKEN_NAME.equals(cookie.getName())) {
          String token = cookie.getValue();
          if (token.equals(CommonConstants.TOKEN_VALUE)) {
            servletRequest.setAttribute("auth", "true");
          }
        }
      }
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}

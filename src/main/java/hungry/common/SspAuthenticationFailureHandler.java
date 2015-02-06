package hungry.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class SspAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    //ロガー
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
      super.onAuthenticationFailure(request, response, exception);
      if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
          logger.info("BAD_CREDENTIAL");
      } else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
          logger.info("USER_DISABLED");
      }
      request.getSession().setAttribute("errorMessage", "error.authenticationFail");
      request.getSession().setAttribute("hasError", "1");
    }
}

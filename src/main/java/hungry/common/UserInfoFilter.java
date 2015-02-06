package hungry.common;

import hungry.utility.StringUtility;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class UserInfoFilter implements Filter {
    //ロガー
    private final Logger logger = Logger.getLogger(this.getClass());

    public void init(FilterConfig paramFilterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        // セッションスコープに保持する
        if ("/loginCheck".equals(req.getRequestURI().substring(req.getContextPath().length()))) {
            String saveAddress = request.getParameter("saveAddress");
            if (!StringUtility.isNullOrEmpty(saveAddress)) {
                HttpSession session = req.getSession();
                if(session != null) {
                    session.setAttribute("saveAddress", saveAddress);
                }
                logger.debug(saveAddress);
            }
        }

        filterChain.doFilter(request, response);

    }

    public void destroy() {
        // 処理なし
    }
}

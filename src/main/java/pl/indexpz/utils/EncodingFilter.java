package pl.indexpz.utils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {

    private String  charactersetEncoding = "utf-8";
    private String contentType = "text/html";

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        request.setCharacterEncoding(charactersetEncoding);
        response.setContentType(contentType);
        response.setCharacterEncoding(charactersetEncoding);
        System.out.println("Encoding Filter działa");
        chain.doFilter(request, response);
    }
}

package xin.trooper.storage.interceptor;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class AuthorityCheckInterceptor extends MethodFilterInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		System.out.println("AuthorityCheckInterceptor.intercept(ActionInvocation invocation) throws Exception");
		
		System.out.println(ActionContext.getContext().getSession());
		String userStatus = (String) ActionContext.getContext().getSession().get("userStatus");
		//String userStatus = (String) session.getAttribute("userStatus");
		if((userStatus.equals("logined")) || (ActionContext.getContext().getSession() == null)) {
			System.out.println("user is login");
			return invocation.invoke();
		}else {
			System.out.println("user is logoff");
			HttpServletResponse response = ServletActionContext.getResponse();
			response.sendRedirect("login.jsp");
			return invocation.invoke();
		}
		
		
	}

}

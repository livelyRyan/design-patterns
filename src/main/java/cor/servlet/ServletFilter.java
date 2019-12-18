package cor.servlet;

import java.util.ArrayList;
import java.util.List;

/*
 * 模拟 javax.servlet包中的Filter和FilterChain实现
 * 
 * 假设当前设置了三个过滤器1, 2, 3
 * 要求处理顺序为 : req 1 -> req 2 -> req 3 -> resp 3 -> resp 2 -> resp 1
 * 
*/
public class ServletFilter {

	public static void main(String[] args) {
		Request req = new Request();
		Response resp = new Response();
		
		FilterChain chain = new FilterChain();
		chain.add(new FaceFilter()).add(new URLFilter()).add(new FaceFilter());
		chain.doFilter(req, resp);
	}

}

class FaceFilter implements Filter {
	@Override
	public void doFilter(Request req, Response resp, FilterChain fc) {
		System.out.println("do req face filter");
		fc.doFilter(req, resp);
		System.out.println("do resp face filter");
	}
}

class URLFilter implements Filter {
	@Override
	public void doFilter(Request req, Response resp, FilterChain fc) {
		System.out.println("do req url filter");
		fc.doFilter(req, resp);
		System.out.println("do resp url filter");
	}
}

class FilterChain {
	
	List<Filter> fs = new ArrayList<>();
	int index = 0;
	FilterChain add(Filter f) {
		fs.add(f);
		return this;
	}
	
	public void doFilter(Request req, Response resp) {
		if ( index == fs.size() ) {
			return; 
		}
		Filter filter = fs.get(index);
		index++;
		filter.doFilter(req, resp, this);
	}
}

interface Filter {
	void doFilter(Request req, Response resp, FilterChain fc);
}

class Request {
}

class Response {
}
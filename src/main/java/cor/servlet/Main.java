package cor.servlet;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Request req = new Request();
		req.str = "hi, :) ! github.com ";
		
		Response resp = new Response();
		resp.str = "response";
		
		FilterChain fc = new FilterChain();
		fc.add(new FaceFilter()).add(new URLFilter()).add(new FaceFilter());
		fc.doFilter(req, resp, fc);
	}

}

class ServletFilter implements Filter {

	@Override
	public Boolean doFilter(Request req, Response resp, FilterChain fc) {
		return true;
	}
	
}

class FaceFilter implements Filter {
	@Override
	public Boolean doFilter(Request req, Response resp, FilterChain fc) {
		System.out.println("do req face filter");
		fc.doFilter(req, resp, fc);
		System.out.println("do resp face filter");
		return true;
	}
}

class URLFilter implements Filter {
	@Override
	public Boolean doFilter(Request req, Response resp, FilterChain fc) {
		System.out.println("do req url filter");
		fc.doFilter(req, resp, fc);
		System.out.println("do resp url filter");
		return true;
	}
}


class FilterChain implements Filter {
	
	List<Filter> fs = new ArrayList<>();
	
	FilterChain add(Filter f) {
		fs.add(f);
		return this;
	}
	
	Filter getAndRemoveFirst() {
		if ( fs.isEmpty() ) {
			return null;
		}
		return fs.remove(0);
	}
	
	@Override
	public Boolean doFilter(Request req, Response resp, FilterChain fc) {
		Filter filter = fc.getAndRemoveFirst();
		if ( filter != null ) {
			filter.doFilter(req, resp, fc);
		}
		return true;
	}
}

interface Filter {
	Boolean doFilter(Request req, Response resp, FilterChain fc);
}

class Request {
    String str;
}

class Response {
    String str;
}
package cor;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Msg m = new Msg();
		m.setMsg("hi :) <h2> github.com. I love 996!");
		
		FilterChain fc = new FilterChain();
		fc.add(new FaceFilter()).add(new SensitiveFilter());
		fc.add(new ScriptFilter()).add(new URLFilter());
		
		fc.doFilter(m);
		System.out.println(m.getMsg());
	}

}

class Msg {
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}

class FaceFilter implements Filter {
	@Override
	public Boolean doFilter(Msg m) {
		m.setMsg(m.getMsg().replace(":)", "^v^"));
		return true;
	}
}

class URLFilter implements Filter {
	@Override
	public Boolean doFilter(Msg m) {
		m.setMsg(m.getMsg().replace("github.com", "https://www.github.com"));
		return true;
	}
}

class ScriptFilter implements Filter {
	@Override
	public Boolean doFilter(Msg m) {
		m.setMsg(m.getMsg().replace("<", "["));
		m.setMsg(m.getMsg().replace(">", "]"));
		return true;
	}
}

class SensitiveFilter implements Filter {
	@Override
	public Boolean doFilter(Msg m) {
		m.setMsg(m.getMsg().replace("996", "955"));
		return false;
	}
}

class FilterChain implements Filter {
	
	List<Filter> fs = new ArrayList<>();
	
	FilterChain add(Filter f) {
		fs.add(f);
		return this;
	}
	
	@Override
	public Boolean doFilter(Msg m) {
		for (Filter f : fs) {
			// 返回false就不走后面的filters了，直接返回
			if ( !f.doFilter(m) ) return false;
		}
		return true;
	}
}

interface Filter {
	Boolean doFilter(Msg m);
}
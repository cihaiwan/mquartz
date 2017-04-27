package com.codezjsos.base.utils;


public class JsonRender  {
	
	private static final String contentType = "application/json; charset=" + getEncoding();
	private static final String contentTypeForIE = "text/html; charset=" + getEncoding();
	private boolean forIE = false;
	
	public JsonRender forIE() {
		forIE = true;
		return this;
	}
	
	public static String getEncoding(){
		
		return "UTF-8";
	}
	
//	public void render(HttpServletResponse response, String jsonText) throws Exception {
//		
//		PrintWriter writer = null;
//		try {
//			response.setHeader("Pragma", "no-cache");	// HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
//			response.setHeader("Cache-Control", "no-cache");
//			response.setDateHeader("Expires", 0);
//			
//			response.setContentType(forIE ? contentTypeForIE : contentType);
//			writer = response.getWriter();
//	        writer.write(jsonText);
//	        writer.flush();
//		} catch (IOException e) {
//			throw new Exception(e);
//		}
//		finally {
//			if (writer != null) {
//				writer.close();
//			}
//		}
//	}
	
	
	
	public Boolean getForIE() {
		return forIE;
	}
}


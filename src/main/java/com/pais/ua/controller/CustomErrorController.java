package com.pais.ua.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class CustomErrorController implements ErrorController {
	
	// ErrorAttributes object is used to save all error attributes value.
	private final ErrorAttributes errorAttributes;

	@Autowired
	public CustomErrorController(ErrorAttributes errorAttributes) {
		Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
		this.errorAttributes = errorAttributes;
	}

	@GetMapping("/error")
	/*
	 * @ResponseBody annotation will return the error page content instead of the
	 * template error page name.
	 */
	public String processError(HttpServletRequest request, WebRequest webRequest, Model model) {

		// Get error status code.
		Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (statusCode == HttpStatus.NOT_FOUND.value()) {
			
			model.addAttribute("titulo", 	"Error 404");
			
			return "error/error-404";
		} else if (statusCode.intValue() == 500) {
			// If you want to return template error page, then remove the @ResponseBody
			// annotation of this method.
			return "error/error-500";
		} else {
			
			// Get error stack trace map object.
			Map<String, Object> body = errorAttributes.getErrorAttributes(webRequest, true);
			// Extract stack trace string.
			String trace 	= (String) body.get("trace");
			String mensaje 	= (String) body.get("message");
			String error 	= (String) body.get("error");
			Date timestamp 	= (Date) body.get("timestamp");
			String time		= timestamp.toString() != null ? timestamp.toString().trim() : "";		

			model.addAttribute("titulo", 	"Error 500");
			model.addAttribute("time", 		time != null ? time : "");
			model.addAttribute("status", 	String.valueOf(statusCode) != null ? String.valueOf(statusCode) : "");
			model.addAttribute("error", 	error != null ? error : "");
			model.addAttribute("message", 	mensaje != null ? mensaje : "");
			model.addAttribute("trace", 	trace != null ? trace : "");
			
			return "error/error-400";
		}
	}
	
	/* Return the error page path. */
	@Override
	public String getErrorPath() {
		return "/error";
	}
}

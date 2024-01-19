package com.cimb.demo.controller.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 * The error response follows RFC7807 - Problem Details for HTTP APIs (<a href="https://tools.ietf.org/html/rfc7807">https://tools.ietf.org/html/rfc7807</a>).
 */
@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {
}

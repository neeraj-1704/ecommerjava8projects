//package com.commerce.ecommerce_api.excpetions;
//
//import com.commerce.ecommerce_api.dto.ErrorDetails;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    // Catch product missing errors (HTTP 404)
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleProductNotFound(ProductNotFoundException ex, WebRequest request) {
//        ErrorDetails error = new ErrorDetails(ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
//
//    // Catch checkout validation errors (HTTP 400)
//    @ExceptionHandler(InsufficientStockException.class)
//    public ResponseEntity<ErrorDetails> handleInsufficientStock(InsufficientStockException ex, WebRequest request) {
//        ErrorDetails error = new ErrorDetails(ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//    // Catch-all for database connectivity issues, null pointers, etc. (HTTP 500)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
//        ErrorDetails error = new ErrorDetails("An unexpected server error occurred.", request.getDescription(false));
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}

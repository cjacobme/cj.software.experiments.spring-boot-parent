package cj.software.experiments.springboot._01_exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cj.software.experiments.springboot._01_exceptions.controller.NotFoundException;

@ControllerAdvice
public class EmployeeExceptionHandler
		extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleFailedValidations(
			ConstraintViolationException pException,
			WebRequest pWebRequest)
	{
		ErrorResponse lErrorResponse = new ErrorResponse();
		lErrorResponse.setMessage(pException.getMessage());
		for (ConstraintViolation<?> bViolation : pException.getConstraintViolations())
		{
			String lMessage = bViolation.getMessage();
			Path lPropertyPath = bViolation.getPropertyPath();
			Object lLeafBean = bViolation.getLeafBean();
			String lMsg = String.format(
					"path %s, leaf %s, msg %s",
					lPropertyPath.toString(),
					lLeafBean,
					lMessage);
			lErrorResponse.addDetail(lMsg);
		}
		return new ResponseEntity<Object>(lErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFound(
			NotFoundException pException,
			WebRequest pWebRequest)
	{
		ErrorResponse lErrorResponse = new ErrorResponse();
		lErrorResponse.setMessage("There was something not found");
		lErrorResponse.addDetail(pException.getLocalizedMessage());
		return new ResponseEntity<Object>(lErrorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(
			Exception pException,
			WebRequest pRequest)
	{
		List<String> lDetails = new ArrayList<>();
		lDetails.add(pException.getLocalizedMessage());
		ErrorResponse lErrorResponse = new ErrorResponse();
		lErrorResponse.setMessage("ups");
		lErrorResponse.addDetail(pException.getLocalizedMessage());
		return new ResponseEntity<Object>(lErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

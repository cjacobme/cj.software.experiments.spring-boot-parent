package cj.software.experiments.springboot._01_exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException pException,
			HttpHeaders pHeaders,
			HttpStatus pStatus,
			WebRequest pRequest)
	{
		ErrorResponse lErrorResponse = new ErrorResponse();
		lErrorResponse.setMessage("validation failed");
		for (ObjectError bError : pException.getBindingResult().getAllErrors())
		{
			String lCode = bError.getCode();
			String lDefaultMessage = bError.getDefaultMessage();
			String lObjectName = bError.getObjectName();
			Object[] lArguments = bError.getArguments();
			lErrorResponse.addDetail("code: " + lCode);
			lErrorResponse.addDetail("default-Message: " + lDefaultMessage);
			lErrorResponse.addDetail("object name: " + lObjectName);
			for (Object bArg : lArguments)
			{
				if (bArg instanceof DefaultMessageSourceResolvable)
				{
					DefaultMessageSourceResolvable lResolvable = (DefaultMessageSourceResolvable) bArg;
					StringBuilder lSB = new StringBuilder("[");
					String[] lCodes = lResolvable.getCodes();
					for (String bCode : lCodes)
					{
						lSB.append(bCode).append(",");
					}
					lSB.append("]");
					lErrorResponse.addDetail("resolvable args: " + lSB.toString());
				}
				else
				{
					lErrorResponse.addDetail("arg: " + bArg);
				}
			}
			lErrorResponse.addDetail("######################");
		}
		return new ResponseEntity<Object>(lErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(
			BindException pException,
			HttpHeaders pHeaders,
			HttpStatus pStatus,
			WebRequest pRequest)
	{
		Throwable lCause = this.getRoot(pException);
		ErrorResponse lErrorResponse = new ErrorResponse();
		lErrorResponse.setMessage(lCause.getLocalizedMessage());
		lErrorResponse.addDetail(lCause.getClass().getName());
		ResponseEntity<Object> lResult = new ResponseEntity<Object>(
				lErrorResponse,
				HttpStatus.BAD_REQUEST);
		return lResult;
	}

	private Throwable getRoot(Throwable pStart)
	{
		Throwable lResult = pStart;
		Throwable lCause = lResult.getCause();
		while (lCause != null)
		{
			lResult = lCause;
			lCause = lResult.getCause();
		}
		return lResult;
	}
}

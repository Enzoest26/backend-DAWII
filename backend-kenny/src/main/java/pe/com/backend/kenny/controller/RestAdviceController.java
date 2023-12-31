package pe.com.backend.kenny.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import pe.com.backend.kenny.exception.AuthorizationNoEncontradoException;
import pe.com.backend.kenny.exception.ItemNoEncontradoException;
import pe.com.backend.kenny.exception.LoginFailedException;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.util.Constantes;

@RestControllerAdvice
public class RestAdviceController {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public BaseResponse errorException(Exception exception)
	{
		exception.printStackTrace();
		return BaseResponse.builder().codRespuesta(Constantes.CODIGO_ERROR)
				.msjRespuesta(Constantes.MENSAJE_ERROR)
				.descripcion(exception.getMessage())
				.build();
	}
	
	@ExceptionHandler(ItemNoEncontradoException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public BaseResponse errorItemNoEncontradoException(ItemNoEncontradoException exception)
	{
		return BaseResponse.builder()
				.codRespuesta(Constantes.CODIGO_ERROR)
				.msjRespuesta(exception.getMessage())
				.build();
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public BaseResponse errorHttpMessageNotReadableException(HttpMessageNotReadableException exception)
	{
		return BaseResponse.builder()
				.codRespuesta(Constantes.CODIGO_ERROR_HTTP_MESSAGE_NOT_READABLE)
				.msjRespuesta(Constantes.MENSAJE_ERROR_HTTP_MESSAGE_NOT_READABLE)
				.descripcion(exception.getMessage())
				.build();
	}
	
	@ExceptionHandler(AuthorizationNoEncontradoException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public BaseResponse errorAuthorizationNoEncontradoException(AuthorizationNoEncontradoException exception)
	{
		return BaseResponse.builder()
				.codRespuesta(Constantes.CODIGO_AUTHORIZATION_ERROR)
				.msjRespuesta(Constantes.MENSAJE_ERROR_AUTHORIZATION)
				.descripcion(exception.getMessage())
				.build();
	}
	
	@ExceptionHandler(LoginFailedException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public BaseResponse errorLoginFailedException(LoginFailedException exception)
	{
		return BaseResponse.builder()
				.codRespuesta(Constantes.CODIGO_LOGIN_FAILED)
				.msjRespuesta(Constantes.MENSAJE_ERROR_LOGIN_FAILED)
				.descripcion(exception.getMessage())
				.build();
	}
	
	
}

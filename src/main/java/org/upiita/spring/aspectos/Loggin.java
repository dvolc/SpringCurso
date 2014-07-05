package org.upiita.spring.aspectos;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Loggin {
	
	@Before("execution(* org.upiita.spring.dao.UsuarioDAO.buscaPorId(..))")
	public void antesdeInvocar(JoinPoint joinPoint){
		
		System.out.println("Antes de Invocar");
		System.out.println("Argumentos: " + Arrays.asList(joinPoint.getArgs()));
	}
	
	public void despuesdeInvocar(){
		System.out.println("Despues  de Invocar");
	}

	
	public Object alrededor(ProceedingJoinPoint joinPoint) throws Throwable {
		Object resultado;				
		resultado = joinPoint.proceed();		
		return resultado;
	}

}

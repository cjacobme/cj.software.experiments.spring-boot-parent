package cj.software.experiments.springboot._01_exceptions.controller;

public class NotFoundException
		extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public NotFoundException(String pText)
	{
		super(pText);
	}
}

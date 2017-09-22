package com.zxd.blackt.blackt.Util.tomyown;

public class CommonException extends RuntimeException
{

	public CommonException()
	{
		super();
	}

	public CommonException(String detailMessage, Throwable throwable)
	{
		super(detailMessage, throwable);
	}

	public CommonException(String detailMessage)
	{
		super(detailMessage);
	}

	public CommonException(Throwable throwable)
	{
		super(throwable);
	}

}

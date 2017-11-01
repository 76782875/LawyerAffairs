package com.lecshop.utils;

/**
 * 参数校验工具类
 * @author dujinkai
 *
 */
public final class ParamsValidateUtil 
{
	
	private  static ParamsValidateUtil paramsValidateUtil = new ParamsValidateUtil();
	
	private ParamsValidateUtil()
	{
		
	}
	
	public static ParamsValidateUtil getInstance()
	{
		return paramsValidateUtil;
	}

	/**
	 * 参数校验
	 *
	 * @param o
	 */
	public final void validateParams(Object o) {
		if (null == o) {
			throw new IllegalArgumentException("Param is empty ");
		}
	}
	
}

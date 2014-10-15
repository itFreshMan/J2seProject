package cn.edu.ahpu.utils.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EmptyClassPropitiesUtils {
	
	private static final String SETTER_FUNC_HEADER = "set";
	private static final String GETTER_FUNC_HEADER = "get";
	
	private static final String[] NATURE_PARAMETER_TYPES= {"java.lang.String","java.lang.Integer","java.util.Date","java.lang.Double","java.math.BigDecimal"};
	
	public static void emptyClassPropities(Object o,String ... ignoreProperties){
		List<String> propertiesList = new ArrayList<String>();
		if(ignoreProperties != null && ignoreProperties.length > 0){
			propertiesList = Arrays.asList(ignoreProperties);
		}
		emptyClassPropities(o,propertiesList);
	}
	
	public static void emptyClassPropities(Object o,List<String> propertiesList){
		if(o == null ){
			return ;
		}
		List<String> parameterTypesList = Arrays.asList(NATURE_PARAMETER_TYPES);
		Class clazz = o.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		if(methods != null)
			for(Method method : methods){
				String methodName = method.getName();
				if(methodName != null && methodName.startsWith(SETTER_FUNC_HEADER)){
					String propertyName = methodName.substring(methodName.indexOf(SETTER_FUNC_HEADER)+SETTER_FUNC_HEADER.length());
					propertyName = propertyName.toLowerCase().charAt(0)+propertyName.substring(1);
					if(propertiesList != null && propertiesList.contains(propertyName)){
						//ignore
					}else{
						//确保set方法 有且只有一个参数;
						Class parameterTypesClazz = method.getParameterTypes() == null ? null : method.getParameterTypes().length > 1? null : method.getParameterTypes()[0];
						if(parameterTypesClazz != null ){
							String parameterName = parameterTypesClazz.getName();
							try {
								if(parameterTypesList.contains(parameterName)){
									if(parameterName.indexOf("java.lang.String") >= 0){
										method.invoke(o, "");
									}else if(parameterName.indexOf("java.util.Date") >= 0){
										method.invoke(o,new Date());
									}else if(parameterName.indexOf("java.lang.Integer") >= 0){
										method.invoke(o,0);
									}else if(parameterName.indexOf("java.lang.Long") >= 0){
										method.invoke(o,0l);
									}else if(parameterName.indexOf("java.lang.Double") >= 0){
										method.invoke(o,0d);
									}
									
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
	}
	
	public static void main(String[] args) {
		TUser user = new TUser(1l,"里奥南多",18,new Date(),6800.00d);
		System.out.println(user);
		System.out.println("--------------------------");
		emptyClassPropities(user,"id");
		System.out.println(user);
	}
}

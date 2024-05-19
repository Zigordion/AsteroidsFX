package dk.sdu.mmmi.cbse.common.util;

import java.util.*;

public class ServiceLocator {
	@SuppressWarnings("rawtypes")
	private static final Map<Class, ServiceLoader> serviceLoaders = new HashMap<>();

	private ServiceLocator() {
	}
	@SuppressWarnings("unchecked")
	public static <T> List<T> getServices(Class<T> service) {
		ServiceLoader<T> loader = serviceLoaders.get(service);
		if (loader == null) {
			loader = ServiceLoader.load(service);
			serviceLoaders.put(service, loader);
		}
		List<T> list = new ArrayList<T>();
		try {
			for (T instance : loader) {
				list.add(instance);
			}
		} catch (ServiceConfigurationError e) {
			e.printStackTrace();
		}
		return list;
	}

}

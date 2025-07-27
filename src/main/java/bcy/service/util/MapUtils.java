package bcy.service.util;
import java.lang.reflect.Field;
import java.util.Map;

public class MapUtils {
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            int mod = field.getModifiers();
            if (java.lang.reflect.Modifier.isStatic(mod) || java.lang.reflect.Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            if (map.containsKey(field.getName())) {
                field.set(obj, map.get(field.getName()));
            }
        }
        return obj;
    }
}
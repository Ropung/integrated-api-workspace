package com.joara.jwt.util;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@RequiredArgsConstructor
public class Claims {
	
	private final io.jsonwebtoken.Claims delegator;
	
	public String getIssuer() {
		return delegator.getIssuer();
	}
	
	public String getSubject() {
		return delegator.getSubject();
	}
	
	public String getAudience() {
		return delegator.getAudience();
	}
	
	public Date getExpiration() {
		return delegator.getExpiration();
	}
	
	public Date getNotBefore() {
		return delegator.getNotBefore();
	}
	
	public Date getIssuedAt() {
		return delegator.getIssuedAt();
	}
	
	public String getId() {
		return delegator.getId();
	}
	
	public <T> T get(String claimName, Class<T> requiredType) {
		return delegator.get(claimName, requiredType);
	}
	
	public int size() {
		return delegator.size();
	}
	
	public boolean isEmpty() {
		return delegator.isEmpty();
	}
	
	public boolean containsKey(Object key) {
		return delegator.containsKey(key);
	}
	
	public boolean containsValue(Object value) {
		return delegator.containsValue(value);
	}
	
	public Object get(Object key) {
		return delegator.get(key);
	}
	
	public Set<String> keySet() {
		return delegator.keySet();
	}
	
	public Collection<Object> values() {
		return delegator.values();
	}
	
	public Set<Entry<String, Object>> entrySet() {
		return delegator.entrySet();
	}
	
	public Object getOrDefault(Object key, Object defaultValue) {
		return delegator.getOrDefault(key, defaultValue);
	}
	
	public void forEach(BiConsumer<? super String, ? super Object> action) {
		delegator.forEach(action);
	}
	
	public Object computeIfAbsent(String key, Function<? super String, ?> mappingFunction) {
		return delegator.computeIfAbsent(key, mappingFunction);
	}
	
	public Object computeIfPresent(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
		return delegator.computeIfPresent(key, remappingFunction);
	}
	
	public Object compute(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
		return delegator.compute(key, remappingFunction);
	}
}

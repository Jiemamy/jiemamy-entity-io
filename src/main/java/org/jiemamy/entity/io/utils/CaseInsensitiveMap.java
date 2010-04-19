/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 7, 2009
 *
 * This file is part of Jiemamy.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.jiemamy.entity.io.utils;

import java.util.Map;

import org.jiemamy.utils.ArrayMap;

/**
 * キーで大文字小文字を気にしない {@link ArrayMap}です。
 * 
 * @author j5ik2o
 * 
 */
public class CaseInsensitiveMap<K, V> extends ArrayMap<K, V> {
	
	private static final long serialVersionUID = 1L;
	

	private static String convertKey(String key) {
		return key.toLowerCase();
	}
	
	/**
	 * {@link CaseInsensitiveMap}を作成する。
	 */
	public CaseInsensitiveMap() {
		super();
	}
	
	/**
	 * {@link CaseInsensitiveMap}を作成する。
	 * 
	 * @param capacity
	 */
	public CaseInsensitiveMap(int capacity) {
		super(capacity);
	}
	
	@Override
	public boolean containsKey(Object key) {
		return super.containsKey(convertKey(key.toString()));
	}
	
	/**
	 * キーが含まれているかどうかを取得する。
	 * 
	 * @param key
	 * @return キーが含まれているかどうか
	 */
	public final boolean containsKey(String key) {
		return super.containsKey(convertKey(key));
	}
	
	@Override
	public V get(Object key) {
		return super.get(convertKey(key.toString()));
	}
	
	@Override
	public V put(Object key, Object value) {
		return super.put((K) convertKey(key.toString()), (V) value);
	}
	
	@Override
	public final void putAll(Map map) {
		for (Object element : map.entrySet()) {
			Map.Entry entry = (Map.Entry) element;
			put(convertKey(entry.getKey().toString()), entry.getValue());
		}
	}
	
	@Override
	public final V remove(Object key) {
		return super.remove(convertKey(key.toString()));
	}
	
}

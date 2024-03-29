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

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 大文字小文字を気にしない {@link Set}。
 * 
 * @author j5ik2o
 * 
 */
@SuppressWarnings("unchecked")
public class CaseInsensitiveSet extends AbstractSet implements Set, Serializable {
	
	static final long serialVersionUID = 0L;
	
	private transient Map map;
	
	private static final Object PRESENT = new Object();
	

	/**
	 * {@link CaseInsensitiveSet}を作成する。
	 */
	public CaseInsensitiveSet() {
		map = new CaseInsensitiveMap();
	}
	
	/**
	 * {@link CaseInsensitiveSet}を作成する。
	 * 
	 * @param c
	 */
	public CaseInsensitiveSet(Collection c) {
		map = new CaseInsensitiveMap(Math.max((int) (c.size() / .75f) + 1, 16));
		addAll(c);
	}
	
	/**
	 * {@link CaseInsensitiveSet}を作成する。
	 * 
	 * @param initialCapacity
	 */
	public CaseInsensitiveSet(int initialCapacity) {
		map = new CaseInsensitiveMap(initialCapacity);
	}
	
	@Override
	public boolean add(Object o) {
		return map.put(o, PRESENT) == null;
	}
	
	@Override
	public void clear() {
		map.clear();
	}
	
	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}
	
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	@Override
	public Iterator iterator() {
		return map.keySet().iterator();
	}
	
	@Override
	public boolean remove(Object o) {
		return map.remove(o) == PRESENT;
	}
	
	@Override
	public int size() {
		return map.size();
	}
}

/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
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
package org.jiemamy.entity.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * プロダクト情報。
 * 
 * @author j5ik2o
 */
public class ProductInfo {
	
	/** プロダクト名 */
	protected static final String PRODUCT_NAME = "Jiemamy EntityIO";
	
	/** 不明な値 */
	protected static final String UNKNOWN = "unknown";
	

	/**
	 * シングルトンの{@link ProductInfo}を取得する。
	 * 
	 * @return シングルトンの{@link ProductInfo}
	 */
	public static ProductInfo getInstance() {
		return ProductInfoHolder.PRODUCT_INFO;
	}
	
	/**
	 * プロダクト情報を標準出力へ出力する。
	 * 
	 * @param args プログラム引数
	 */
	public static void main(String[] args) {
		ProductInfo info = ProductInfoHolder.PRODUCT_INFO;
		System.out.printf("jiemamy entity io name : %s\n", info.getName());
		System.out.printf("jiemamy entity io version : %s\n", info.getVersion());
		System.out.printf("jiemamy entity io groupId : %s\n", info.getGroupId());
		System.out.printf("jiemamy entity io artifactId : %s\n", info.getArtifactId());
	}
	

	/** groupId */
	protected final String groupId;
	
	/** artifactId */
	protected final String artifactId;
	
	/** バージョン */
	protected final String version;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param version バージョン
	 * @param groupId グループID
	 * @param artifactId アーティファクトID
	 */
	protected ProductInfo(String version, String groupId, String artifactId) {
		this.version = version != null ? version : UNKNOWN;
		this.groupId = groupId != null ? groupId : UNKNOWN;
		this.artifactId = artifactId != null ? artifactId : UNKNOWN;
	}
	
	/**
	 * artifactIdを取得する。
	 * 
	 * @return artifactId
	 */
	public String getArtifactId() {
		return artifactId;
	}
	
	/**
	 * groupIdを取得する。
	 * 
	 * @return groupId
	 */
	public String getGroupId() {
		return groupId;
	}
	
	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	public String getName() {
		return PRODUCT_NAME;
	}
	
	/**
	 * バージョンを取得する。
	 * 
	 * @return バージョン
	 */
	public String getVersion() {
		return version;
	}
	

	/**
	 * プロダクト情報のホルダ。
	 * 
	 * @author j5ik2o
	 */
	protected static class ProductInfoHolder {
		
		/** POMのプロパティファイルのパス */
		protected static final String POM_PROPERTIES_PATH =
				"META-INF/maven/org.jiemamy/jiemamy-entity-io/pom.properties";
		
		/** プロダクト情報 */
		protected static final ProductInfo PRODUCT_INFO = createProductInfo();
		

		/**
		 * プロダクト情報を取得する。
		 * 
		 * @return プロダクト情報
		 */
		protected static ProductInfo createProductInfo() {
			Properties props = loadProperties();
			return new ProductInfo(props.getProperty("version"), props.getProperty("groupId"), props
				.getProperty("artifactId"));
		}
		
		/**
		 * プロパティをロードし取得する。
		 * 
		 * @return プロパティ
		 */
		protected static Properties loadProperties() {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(POM_PROPERTIES_PATH);
			Properties props = new Properties();
			if (is != null) {
				try {
					props.load(is);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return props;
		}
		
		/**
		 * インスタンスを生成する。
		 */
		protected ProductInfoHolder() {
		}
	}
	
}

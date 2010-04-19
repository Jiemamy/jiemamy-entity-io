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
package org.jiemamy.entity.io.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Comparator;

/**
 * {@link File}に関するユーティリティクラス。
 * 
 * @author j5ik2o
 */
public class FileUtil {
	
	/**
	 * ファイルを扱うインタフェースです・
	 * 
	 * @author j5ik2o
	 */
	public interface FileHandler {
		
		/**
		 * 処理する。
		 * 
		 * @param file {@link File}
		 */
		void handle(File file);
	}
	

	/**
	 * ファイルをコピーし追加する。
	 * 
	 * @param src コピー元ファイル
	 * @param dest コピー先ファイル
	 * @throws IOException 入出力が失敗した場合
	 */
	public static void append(File src, File dest) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(src);
			out = new FileOutputStream(dest, true);
			copyInternal(in, out);
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * ファイルをコピーする。
	 * 
	 * @param src コピー元ファイル
	 * @param dest コピー先ファイル
	 * @throws IOException 入出力が失敗した場合
	 */
	public static void copy(File src, File dest) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(src);
			out = new FileOutputStream(dest);
			copyInternal(in, out);
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 内部的にコピーする。
	 * 
	 * @param in コピー元
	 * @param out コピー先
	 * @throws IOException 入出力が失敗した場合
	 */
	protected static void copyInternal(FileInputStream in, FileOutputStream out) throws IOException {
		FileChannel src = in.getChannel();
		FileChannel dest = out.getChannel();
		src.transferTo(0, src.size(), dest);
	}
	
	/**
	 * Javaファイルを作成する。
	 * 
	 * @param baseDir ベースディレクトリ
	 * @param packageName パッケージ名
	 * @param shortClassName クラスの単純名
	 * @return Javaファイル
	 */
	public static File createJavaFile(File baseDir, String packageName, String shortClassName) {
		File packageDir;
		if (packageName == null) {
			packageDir = baseDir;
		} else {
			packageDir = new File(baseDir, packageName.replace('.', File.separatorChar));
		}
		return new File(packageDir, shortClassName + ".java");
	}
	
	/**
	 * 新しいファイルを不可分 (atomic) に生成する。
	 * 
	 * @param file ファイル
	 * @return 指定されたファイルが存在せず、ファイルの生成に成功した場合は{@code true}、示されたファイルがすでに存在する場合は
	 *         {@code false}
	 * @throws IOException 入出力が失敗した場合
	 */
	public static boolean createNewFile(File file) throws IOException {
		return file.createNewFile();
	}
	
	/**
	 * 一時ファイルを作成する。
	 * 
	 * @param prefix 接頭辞文字列。3 文字以上の長さが必要である
	 * @param suffix 接尾辞文字列。null も指定でき、その場合は、接尾辞 ".tmp" が使用される
	 * @return {@link File}
	 * @throws IOException 入出力が失敗した場合
	 */
	public static File createTempFile(String prefix, String suffix) throws IOException {
		return File.createTempFile(prefix, suffix);
	}
	
	/**
	 * ファイルの正規の形式を取得する。
	 * 
	 * @param file ファイル
	 * @return 正規の形式
	 * @throws IOException 入出力が失敗した場合
	 */
	public static File getCanonicalFile(File file) throws IOException {
		return file.getCanonicalFile();
	}
	
	/**
	 * ファイルの正規のパス名文字列を取得する。
	 * 
	 * @param file ファイル
	 * @return ファイルの正規パス名文字列
	 * @throws IOException 入出力が失敗した場合
	 */
	public static String getCanonicalPath(File file) throws IOException {
		return file.getCanonicalPath();
	}
	
	/**
	 * ディレクトリを横断する。
	 * 
	 * @param dir ディレクトリ
	 * @param filter フィルタ
	 * @param comparator コンパレータ
	 * @param handler ハンドラ
	 */
	public static void traverseDirectory(File dir, FilenameFilter filter, Comparator<File> comparator,
			FileHandler handler) {
		if (!dir.exists()) {
			return;
		}
		File[] files = dir.listFiles(filter);
		if (files == null) {
			return;
		}
		Arrays.sort(files, comparator);
		for (File file : files) {
			if (file.isDirectory()) {
				traverseDirectory(file, filter, comparator, handler);
			}
			handler.handle(file);
		}
	}
	
	/**
	 * 
	 */
	protected FileUtil() {
	}
}

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
package org.jiemamy.entity.io.gen.generator.impl;

import java.io.File;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import org.jiemamy.utils.FileInputStreamUtil;

/**
 * 空のファイルを削除する{@link Writer}の実装。
 * 
 * @author j5ik2o
 */
public class DeleteEmptyFileWriter extends FilterWriter {
	
	/** 書き込みが行われた場合{@code true} */
	protected boolean written;
	
	/** 削除された場合{@code true} */
	protected boolean deleted;
	
	/** 書き込み先のファイル */
	protected File file;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param writer ライタ
	 * @param file 書き込み先のファイル
	 */
	public DeleteEmptyFileWriter(Writer writer, File file) {
		super(writer);
		if (file == null) {
			throw new NullPointerException("file");
		}
		this.file = file;
	}
	
	@Override
	public void close() throws IOException {
		super.close();
		if (!written && file.exists() && isEmpty()) {
			deleted = file.delete();
		}
	}
	
	/**
	 * ファイルが削除された場合のフラグを取得する。
	 * 
	 * @return 削除された場合{@code true}、そうでない場合{@code false}
	 */
	public boolean isDeleted() {
		return deleted;
	}
	
	/**
	 * ファイルが空の場合のフラグを取得する。
	 * 
	 * @return ファイルが空の場合は{@code true}、そうでない場合{@code false}
	 * @throws IOException 入出力が失敗した場合
	 */
	protected boolean isEmpty() throws IOException {
		InputStream is = FileInputStreamUtil.create(file);
		try {
			return is.read() == -1;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
	
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		if (len <= 0) {
			return;
		}
		written = true;
		super.write(cbuf, off, len);
	}
	
	@Override
	public void write(int c) throws IOException {
		written = true;
		super.write(c);
	}
	
	@Override
	public void write(String str, int off, int len) throws IOException {
		if (len <= 0) {
			return;
		}
		written = true;
		super.write(str, off, len);
	}
	
}

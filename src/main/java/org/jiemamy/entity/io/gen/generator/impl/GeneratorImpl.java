/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 * Created on Apr 13, 2009
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Locale;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.apache.commons.lang.Validate;

import org.jiemamy.entity.io.gen.generator.GenerateException;
import org.jiemamy.entity.io.gen.generator.Generator;
import org.jiemamy.entity.io.gen.generator.GeneratorContext;
import org.jiemamy.utils.FileOutputStreamUtil;

/**
 * エンティティを生成するためのジェネレータクラス。
 * 
 * @author j5ik2o
 */
public class GeneratorImpl implements Generator {
	
	private Configuration configuration;
	
	/** デフォルトのテンプレートディレクトリの名前 */
	protected static final String DEFAULT_TEMPLATE_DIR_NAME = "org/jiemamy/entity/io/templates";
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param configuration FreeMarkerの設定
	 */
	public GeneratorImpl(Configuration configuration) {
		Validate.notNull(configuration);
		this.configuration = configuration;
	}
	
	/**
	* インスタンスを生成する。
	* 
	* @param templateFileEncoding テンプレートファイルのエンコーディング
	* @param templateFilePrimaryDir テンプレートファイルを格納したプライマリディレクトリ、プライマリディレクトリを使用しない場合{@code null}
	 * @throws IOException 入出力が失敗した場合
	*/
	public GeneratorImpl(String templateFileEncoding, File templateFilePrimaryDir) throws IOException {
		Validate.notNull(templateFileEncoding);
		configuration = new Configuration();
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		configuration.setSharedVariable("include", new IncludeDirective());
		configuration.setSharedVariable("currentDate", new OnDemandDateModel());
		configuration.setEncoding(Locale.getDefault(), templateFileEncoding);
		configuration.setNumberFormat("0.#####");
		configuration.setTemplateLoader(createTemplateLoader(templateFilePrimaryDir));
	}
	
	/**
	 * {@link TemplateLoader}を作成する。
	 * 
	 * @param templateFilePrimaryDir テンプレートファイルを格納したプライマリディレクトリ、プライマリディレクトリを使用しない場合{@code null}
	 * @return {@link TemplateLoader}
	 * @throws IOException 入出力が失敗した場合
	 */
	protected TemplateLoader createTemplateLoader(File templateFilePrimaryDir) throws IOException {
		TemplateLoader primary = null;
		if (templateFilePrimaryDir != null) {
			primary = new FileTemplateLoader(templateFilePrimaryDir);
		}
		TemplateLoader secondary = new ResourceTemplateLoader(DEFAULT_TEMPLATE_DIR_NAME);
		if (primary == null) {
			return secondary;
		}
		return new MultiTemplateLoader(new TemplateLoader[] {
			primary,
			secondary
		});
	}
	
	/**
	 * {@code file}が存在する場合に{@code true}を取得する。
	 * 
	 * @param file ファイル
	 * @return {@code file}が存在する場合は{@code true}、そうでない場合は{@code false}
	 */
	protected boolean exists(File file) {
		Validate.notNull(file);
		return file.exists();
	}
	
	public void generate(GeneratorContext context) throws GenerateException {
		Validate.notNull(context);
		boolean exists = exists(context.getFile());
		if (!context.isOverwrite() && exists) {
			return;
		}
		File dir = context.getFile().getParentFile();
		if (dir != null) {
			mkdirs(dir);
		}
		Writer writer = null;
		try {
			writer = openWriter(context);
		} catch (FileNotFoundException e) {
			throw new GenerateException(e);
		}
		try {
			Template template = getTemplate(context.getTemplateName());
			process(template, context.getModel(), writer);
		} catch (TemplateException e) {
			e.printStackTrace();
			throw new GenerateException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new GenerateException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					throw new GenerateException(e);
				}
			}
		}
		if (writer instanceof DeleteEmptyFileWriter) {
			if (((DeleteEmptyFileWriter) writer).isDeleted()) {
				return;
			}
		}
	}
	
	/**
	* テンプレートを取得する。
	* 
	* @param name テンプレートの名前
	* @return テンプレート
	 * @throws IOException 入出力が失敗した場合
	*/
	protected Template getTemplate(String name) throws IOException {
		Validate.notNull(name);
		return configuration.getTemplate(name);
	}
	
	/**
	 * テンプレートを取得する。
	 * 
	 * @param name テンプレートの名前
	 * @return テンプレート
	 * @throws IOException 入出力が失敗した場合
	 */
	protected Template getTemplate1(String name) throws IOException {
		Validate.notNull(name);
		return configuration.getTemplate(name);
	}
	
	/**
	 * ディレクトリを生成する。
	 * 
	 * @param dir ディレクトリ
	 */
	protected void mkdirs(File dir) {
		dir.mkdirs();
	}
	
	/**
	 * {@link Writer}を開きます。
	 * 
	 * @param context コンテキスト
	 * @return {@link Writer}
	 * @throws FileNotFoundException ファイルが見つからなかった場合
	 */
	protected Writer openWriter(GeneratorContext context) throws FileNotFoundException {
		Charset charset = Charset.forName(context.getEncoding());
		FileOutputStream fos = FileOutputStreamUtil.create(context.getFile());
		OutputStreamWriter osw = new OutputStreamWriter(fos, charset);
		BufferedWriter bw = new BufferedWriter(osw);
		return new DeleteEmptyFileWriter(bw, context.getFile());
	}
	
	/**
	 * テンプレートを処理する。
	 * 
	 * @param template テンプレート
	 * @param dataModel データモデル
	 * @param writer ライタ
	 * @throws IOException 入出力が失敗した場合
	 * @throws TemplateException テンプレートの処理に失敗した場合
	 */
	protected void process(Template template, Object dataModel, Writer writer) throws TemplateException, IOException {
		template.process(dataModel, writer);
	}
}

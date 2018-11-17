package br.com.leuras.jartisan.command.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jtwig.JtwigModel;
import org.springframework.beans.factory.annotation.Value;

import br.com.leuras.jartisan.constant.TemplateVariables;

public abstract class AbstractjArtisanCommand {
	
	protected String classname;
	
	protected String targetEntity;
	
	protected String classPackage;
	
	protected JtwigModel model;
	
	@Value(value = "${default.package}")
	protected String defaultPackage;
	
	@Value(value = "${default.subpackage}")
	protected String defaultSubpackage;
	
	public AbstractjArtisanCommand withClassName(final String classname) {
		this.classname = classname;
		
		return this;
	}
	
	public AbstractjArtisanCommand withClassPackage(final String classPackage) {
		this.classPackage = classPackage;
		
		return this;
	}
	
	public AbstractjArtisanCommand withTargetEntity(final String targetEntity) {
		this.targetEntity = targetEntity;
		
		return this;
	}
	
	protected final void setVariables(Map<String, Object> variables) {
		this.model = JtwigModel.newModel(variables);
	}
	
	protected final void useDefaultVariables() {
		Map<String, Object> variables = new HashMap<String, Object>();
		
		this.resolvePackage();
		
		variables.put(TemplateVariables.ENTITY, this.targetEntity);
		variables.put(TemplateVariables.CLASSNAME, this.classname);
		variables.put(TemplateVariables.PACKAGE, this.classPackage);
		variables.put(TemplateVariables.ROOT, this.defaultPackage.trim());
		
		this.setVariables(variables);
	}
	
	protected void resolvePackage() {
		if (StringUtils.isBlank(this.classPackage)) {
			this.classPackage = String.format("%s.%s", this.defaultPackage.trim(), this.defaultSubpackage.trim());
		}
	}
}

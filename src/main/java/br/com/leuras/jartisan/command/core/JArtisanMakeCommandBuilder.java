package br.com.leuras.jartisan.command.core;

import br.com.leuras.jartisan.enumerator.TemplateTypeEnum;

public interface JArtisanMakeCommandBuilder extends JArtisanCommandBuilder {
	
	JArtisanMakeCommandBuilder withClassName(String classname);
	
	JArtisanMakeCommandBuilder withClassPackage(String classPackage);
	
	JArtisanMakeCommandBuilder withTargetEntity(String targetEntity);
	
	JArtisanMakeCommandBuilder withTemplateType(TemplateTypeEnum type);	
}

package br.com.leuras.jartisan.command.core;

import br.com.leuras.jartisan.builder.JArtisanMakeCommandBuilderImpl;
import br.com.leuras.jartisan.enumerator.TemplateTypeEnum;

public interface JArtisanMakeCommandBuilder extends JArtisanCommandBuilder {
	
	JArtisanMakeCommandBuilder withClassName(String classname);
	
	JArtisanMakeCommandBuilder withClassPackage(String classPackage);
	
	JArtisanMakeCommandBuilder withTargetEntity(String targetEntity);
	
	JArtisanMakeCommandBuilderImpl withDAO(String dao);	

	JArtisanMakeCommandBuilder withTemplateType(TemplateTypeEnum type);
	
	JArtisanMakeCommandBuilder withVar(String name, Object value);
}

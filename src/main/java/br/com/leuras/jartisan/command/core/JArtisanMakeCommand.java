package br.com.leuras.jartisan.command.core;

import br.com.leuras.jartisan.enumerator.TemplateTypeEnum;
import br.com.leuras.jartisan.service.ConsoleService;
import br.com.leuras.jartisan.service.TemplateService;

public interface JArtisanMakeCommand extends JArtisanCommand {
	
	String getClassName();
	
	void setClassName(String className);
	
	String getClassPackage();
	
	void setClassPackage(String classPackage);
	
	String getTargetEntity();
	
	void setTargetEntity(String targetEntity);
	
	TemplateTypeEnum getTemplateType();
	
	void setTemplateType(TemplateTypeEnum type);
	
	void setConsoleService(ConsoleService service);
	
	void setTemplateService(TemplateService service);
}

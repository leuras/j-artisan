package br.com.leuras.jartisan.command.core;

import java.util.Map;

import br.com.leuras.jartisan.enumerator.TemplateTypeEnum;
import br.com.leuras.jartisan.service.ConsoleService;
import br.com.leuras.jartisan.service.TemplateService;

public interface JArtisanMakeCommand extends JArtisanCommand {
	
	TemplateTypeEnum getTemplateType();
	
	void setTemplateType(TemplateTypeEnum type);
	
	Map<String, Object> getVariables();
	
	void setConsoleService(ConsoleService service);
	
	void setTemplateService(TemplateService service);
}

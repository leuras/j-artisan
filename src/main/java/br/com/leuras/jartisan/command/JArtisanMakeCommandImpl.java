package br.com.leuras.jartisan.command;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Map;

import br.com.leuras.jartisan.command.core.JArtisanMakeCommand;
import br.com.leuras.jartisan.constant.ConsoleNotificationMessage;
import br.com.leuras.jartisan.enumerator.TemplateTypeEnum;
import br.com.leuras.jartisan.service.ConsoleService;
import br.com.leuras.jartisan.service.TemplateService;

public class JArtisanMakeCommandImpl implements JArtisanMakeCommand {
	
	private TemplateTypeEnum templateType;
	
	private Map<String, Object> variables;
	
	private ConsoleService consoleService;
	
	private TemplateService templateService;
	
	public JArtisanMakeCommandImpl(final Map<String, Object> variables) {
	    this.variables = variables;
	}
	
	@Override
	public void run() {
		try {
		    
			final String filename = this.templateService.generate(this.templateType, this.variables);
			this.consoleService.printInGreen(ConsoleNotificationMessage.FILE_CREATED, filename);
			
		} catch (FileAlreadyExistsException e) {
			this.consoleService.printInYellow(ConsoleNotificationMessage.FILE_UNMODIFIED, e.getFile());
		} catch (FileNotFoundException e) {
			this.consoleService.printInRed(e.getMessage());
		}
	}
	
	@Override
	public TemplateTypeEnum getTemplateType() {
		return templateType;
	}

	@Override
	public void setTemplateType(final TemplateTypeEnum templateType) {
		this.templateType = templateType;
	}
	
	@Override
	public void setConsoleService(final ConsoleService service) {
		this.consoleService = service;
	}

	@Override
	public void setTemplateService(final TemplateService service) {
		this.templateService = service;
	}

    @Override
    public Map<String, Object> getVariables() {
        return this.variables;
    }
    
}

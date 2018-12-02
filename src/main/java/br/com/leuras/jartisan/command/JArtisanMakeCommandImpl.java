package br.com.leuras.jartisan.command;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

import org.springframework.core.env.Environment;

import br.com.leuras.jartisan.command.core.AbstractJArtisanCommand;
import br.com.leuras.jartisan.command.core.JArtisanMakeCommand;
import br.com.leuras.jartisan.constant.ConsoleNotificationMessage;
import br.com.leuras.jartisan.constant.TemplateVariables;
import br.com.leuras.jartisan.enumerator.TemplateTypeEnum;
import br.com.leuras.jartisan.service.ConsoleService;
import br.com.leuras.jartisan.service.TemplateService;

public class JArtisanMakeCommandImpl extends AbstractJArtisanCommand implements JArtisanMakeCommand {
	
	private String className;
	
	private String classPackage;
	
	private String targetEntity;
	
	private TemplateTypeEnum templateType;
	
	private ConsoleService consoleService;
	
	private TemplateService templateService;
	
	public JArtisanMakeCommandImpl(final Environment env) {
	    super(env);
	}
	
	@Override
	public void run() {
		try {
		    
		    this.setupVariables();
		    
			final String filename = this.templateService.generate(this.templateType, this.getVariables());
			this.consoleService.printInGreen(ConsoleNotificationMessage.FILE_CREATED, filename);
			
		} catch (FileAlreadyExistsException e) {
			this.consoleService.printInYellow(ConsoleNotificationMessage.FILE_UNMODIFIED, e.getFile());
		} catch (FileNotFoundException e) {
			this.consoleService.printInRed(e.getMessage());
		}
	}
	
	@Override
	public String getClassName() {
		return className;
	}
	
	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String getClassPackage() {
		return classPackage;
	}
	
	@Override
	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}
	
	@Override
	public String getTargetEntity() {
		return targetEntity;
	}

	@Override
	public void setTargetEntity(String targetEntity) {
		this.targetEntity = targetEntity;
	}
	
	@Override
	public TemplateTypeEnum getTemplateType() {
		return templateType;
	}

	@Override
	public void setTemplateType(TemplateTypeEnum templateType) {
		this.templateType = templateType;
	}
	
	@Override
	public void setConsoleService(ConsoleService service) {
		this.consoleService = service;
	}

	@Override
	public void setTemplateService(TemplateService service) {
		this.templateService = service;
	}

	@Override
	public void setupVariables() {
		
		this.classPackage = this.resolvePackage(this.classPackage);
		
		this.addVariable(TemplateVariables.ENTITY, this.targetEntity);
		this.addVariable(TemplateVariables.CLASSNAME, this.className);
		this.addVariable(TemplateVariables.PACKAGE, this.classPackage);
		this.addVariable(TemplateVariables.ROOT, this.getDefaultPackage());
	}
}

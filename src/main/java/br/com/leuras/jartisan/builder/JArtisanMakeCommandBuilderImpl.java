package br.com.leuras.jartisan.builder;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import br.com.leuras.jartisan.command.JArtisanMakeCommandImpl;
import br.com.leuras.jartisan.command.core.JArtisanCommand;
import br.com.leuras.jartisan.command.core.JArtisanMakeCommand;
import br.com.leuras.jartisan.command.core.JArtisanMakeCommandBuilder;
import br.com.leuras.jartisan.enumerator.TemplateTypeEnum;
import br.com.leuras.jartisan.service.ConsoleService;
import br.com.leuras.jartisan.service.TemplateService;

@Component
public final class JArtisanMakeCommandBuilderImpl implements JArtisanMakeCommandBuilder, InitializingBean {
	
    @Autowired
    private Environment env;
    
	@Autowired
	private ConsoleService consoleService;
	
	@Autowired
	private TemplateService templateService;
	
	private JArtisanMakeCommand command;
	
	@Override
	public JArtisanCommand build() {
		return command;
	}

	@Override
	public JArtisanMakeCommandBuilder withClassName(final String className) {
		this.command.setClassName(className);
		return this;
	}

	@Override
	public JArtisanMakeCommandBuilder withClassPackage(final String classPackage) {
		this.command.setClassPackage(classPackage);
		return this;
	}

	@Override
	public JArtisanMakeCommandBuilder withTargetEntity(final String targetEntity) {
		this.command.setTargetEntity(targetEntity);
		return this;
	}

	@Override
	public JArtisanMakeCommandBuilder withTemplateType(final TemplateTypeEnum type) {
		this.command.setTemplateType(type);
		return this;
	}

    @Override
    public void afterPropertiesSet() throws Exception {
        this.command = new JArtisanMakeCommandImpl(this.env);
        
        this.command.setConsoleService(this.consoleService);
        this.command.setTemplateService(this.templateService);
    }

}

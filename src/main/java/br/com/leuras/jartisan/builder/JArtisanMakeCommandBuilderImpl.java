package br.com.leuras.jartisan.builder;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.leuras.jartisan.command.JArtisanMakeCommandImpl;
import br.com.leuras.jartisan.command.core.JArtisanCommand;
import br.com.leuras.jartisan.command.core.JArtisanMakeCommand;
import br.com.leuras.jartisan.command.core.JArtisanMakeCommandBuilder;
import br.com.leuras.jartisan.constant.TemplateVariables;
import br.com.leuras.jartisan.enumerator.TemplateTypeEnum;
import br.com.leuras.jartisan.service.ConsoleService;
import br.com.leuras.jartisan.service.TemplateService;

@Component
public final class JArtisanMakeCommandBuilderImpl implements JArtisanMakeCommandBuilder {
	
    @Value("${default.package}")
    private String defaultPackage;
    
    @Value("${default.subpackage}")
    private String defaultSubpackage;
    
	@Autowired
	private ConsoleService consoleService;
	
	@Autowired
	private TemplateService templateService;
	
	private JArtisanMakeCommand command;
	
	private TemplateTypeEnum templateType;
	
	private Map<String, Object> variables = new HashMap<String, Object>();
	
	@Override
	public JArtisanCommand build() {
	    
	    this.variables.put(TemplateVariables.ROOT, this.defaultPackage.trim());
	    
	    if (StringUtils.isBlank((String) this.variables.get(TemplateVariables.PACKAGE))) {
	        final String pkg = String.format("%s.%s", this.defaultPackage.trim(), this.defaultSubpackage.trim());
	        this.variables.put(TemplateVariables.PACKAGE, pkg);
	    }
	    
	    this.command = new JArtisanMakeCommandImpl(this.variables);
	    
	    this.command.setTemplateType(this.templateType);
	    this.command.setConsoleService(this.consoleService);
        this.command.setTemplateService(this.templateService);
	    
		return this.command;
	}

	@Override
	public JArtisanMakeCommandBuilder withClassName(final String className) {
	    this.variables.put(TemplateVariables.CLASSNAME, className);
		return this;
	}

	@Override
	public JArtisanMakeCommandBuilder withClassPackage(final String classPackage) {
	    this.variables.put(TemplateVariables.PACKAGE, classPackage);
		return this;
	}

	@Override
	public JArtisanMakeCommandBuilder withTargetEntity(final String targetEntity) {
	    this.variables.put(TemplateVariables.ENTITY, targetEntity);
		return this;
	}
	
	@Override
    public JArtisanMakeCommandBuilderImpl withDAOClassName(final String dao) {
	    this.variables.put(TemplateVariables.DAO, dao);
        return this;
    }
	
	@Override
	public JArtisanMakeCommandBuilderImpl withServiceClassName(final String service) {
	    this.variables.put(TemplateVariables.SERVICE, service);
	    return this;
    }


	@Override
	public JArtisanMakeCommandBuilder withTemplateType(final TemplateTypeEnum type) {
		this.templateType = type;
		return this;
	}

    @Override
    public JArtisanMakeCommandBuilder withVar(final String name, final Object value) {
        this.variables.put(name, value);
        return this;
    }

}

package br.com.leuras.jartisan.command.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

public abstract class AbstractJArtisanCommand {
    
	private String defaultPackage;

	private String defaultSubpackage;
	
	private Map<String, Object> variables = new HashMap<String, Object>();
	
	public AbstractJArtisanCommand(final Environment env) {
        this.defaultPackage = env.getProperty("default.package");
        this.defaultSubpackage = env.getProperty("default.subpackage");
    }

	public final String getDefaultPackage() {
		return this.defaultPackage.trim();
	}

	public final void addVariable(final String name, final Object value) {
		this.variables.put(name, value);
	}
	
	public final Map<String, Object> getVariables() {
	    return this.variables;
	}

	public final void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public String resolvePackage(final String classPackage) {
		if (StringUtils.isBlank(classPackage)) {
			return String.format("%s.%s", this.defaultPackage.trim(), this.defaultSubpackage.trim());
		}

		return classPackage;
	}
	
	public abstract void setupVariables();
}

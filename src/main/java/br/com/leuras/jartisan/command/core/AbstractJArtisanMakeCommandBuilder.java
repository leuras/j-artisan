package br.com.leuras.jartisan.command.core;

public abstract class AbstractJArtisanMakeCommandBuilder implements JArtisanCommandBuilder {
	
	protected String classname;
	
	protected String targetEntity;
	
	protected String classPackage;
	
	public JArtisanCommandBuilder withClassName(final String classname) {
		this.classname = classname;
		
		return this;
	}
	
	public JArtisanCommandBuilder withClassPackage(final String classPackage) {
		this.classPackage = classPackage;
		
		return this;
	}
	
	public JArtisanCommandBuilder withTargetEntity(final String targetEntity) {
		this.targetEntity = targetEntity;
		
		return this;
	}
}

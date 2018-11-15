package br.com.leuras.jartisan.enumerator;

public enum TemplateEnum {

	CONTROLLER("controller.twig", ".controller"), 
	SERVICE("service.twig", ".service"),
	DAO("dao.twig", ".dao"), 
	DAO_IMPL("daoImpl.twig", ".dao.impl"); 

	private String filename;
	
	private String subPackageFragment;

	private TemplateEnum(final String filename, final String subPackageFragment) {
		this.filename = filename;
		this.subPackageFragment = subPackageFragment;
	}

	public String getFilename() {
		return filename;
	}
	
	public String getSubPackageFragment() {
		return subPackageFragment;
	}
}

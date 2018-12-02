package br.com.leuras.jartisan.enumerator;

public enum TemplateTypeEnum {

	CONTROLLER("controller.twig", "controller"), 
	SERVICE("service.twig", "service"),
	DAOIMPL("daoImpl.twig", "dao.impl"), 
	DAO("dao.twig", "dao");

	private String filename;
	
	private String subPackageFragment;

	private TemplateTypeEnum(final String filename, final String subPackageFragment) {
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

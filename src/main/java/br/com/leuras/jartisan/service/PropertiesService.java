package br.com.leuras.jartisan.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {
	
	@Value(value = "${default.package}")
	private String defaultPackage;
	
	@Value(value = "${default.subpackage}")
	private String defaultSubpackage;
	
	public String getDefaultBasePackage() {
		return defaultPackage.trim();
	}

	public String getFullPackage() {
		return String.format("%s.%s", this.defaultPackage.trim(), this.defaultSubpackage.trim());
	}
	
	public String defaultPackageIfNullOrEmpty(final String pkg) {
		return (pkg == null || pkg.isEmpty()) ? this.getFullPackage() : pkg;
	}
}

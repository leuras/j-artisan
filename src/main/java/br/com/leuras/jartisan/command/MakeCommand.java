package br.com.leuras.jartisan.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import br.com.leuras.jartisan.constant.ShellMethodHelp;
import br.com.leuras.jartisan.constant.ShellOptionHelp;

@ShellComponent
public class MakeCommand {
	
	@Autowired
	private JArtisanMakeDaoCommand cmdDao;
	
	@ShellMethod(ShellMethodHelp.MAKE_CONTROLLER)
	public void makeController(
			@ShellOption(help = ShellOptionHelp.NAME)    String name, 
			@ShellOption(help = ShellOptionHelp.ENTITY,  defaultValue = ShellOption.NULL) String entity,
			@ShellOption(help = ShellOptionHelp.PACKAGE, defaultValue = ShellOption.NULL) String pkg) {
		
		
	}

	@ShellMethod(ShellMethodHelp.MAKE_SERVICE)
	public void makeService(
			@ShellOption(help = ShellOptionHelp.NAME)    String name, 
			@ShellOption(help = ShellOptionHelp.ENTITY,  defaultValue = ShellOption.NULL) String entity,
			@ShellOption(help = ShellOptionHelp.PACKAGE, defaultValue = ShellOption.NULL) String pkg) {
		
	}
	
	@ShellMethod(ShellMethodHelp.MAKE_DAO)
	public void makeDao(
			@ShellOption(help = ShellOptionHelp.NAME)    String name,
			@ShellOption(help = ShellOptionHelp.ENTITY)  String entity,
			@ShellOption(help = ShellOptionHelp.PACKAGE, defaultValue = ShellOption.NULL) String pkg) {
		
		this.cmdDao
			.withClassName(name)
			.withClassPackage(pkg)
			.withTargetEntity(entity);
		
		this.cmdDao.run();
	}
}

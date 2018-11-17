package br.com.leuras.jartisan.command;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.leuras.jartisan.command.core.AbstractjArtisanCommand;
import br.com.leuras.jartisan.command.core.jArtisanCommand;
import br.com.leuras.jartisan.constant.ConsoleNotificationMessage;
import br.com.leuras.jartisan.enumerator.TemplateEnum;
import br.com.leuras.jartisan.service.ConsoleService;
import br.com.leuras.jartisan.service.TemplateService;

@Component
public class JArtisanMakeDaoCommand extends AbstractjArtisanCommand implements jArtisanCommand {
	
	@Autowired
	private ConsoleService svcConsole;
	
	@Autowired
	private TemplateService svcTemplate;
	
	public JArtisanMakeDaoCommand() {
		super();		
	}
	
	@Override
	public void run() {
		
		this.useDefaultVariables();
		
		try {
			
			String filename = this.svcTemplate.generate(TemplateEnum.DAO, this.model);
			this.svcConsole.printInGreen(ConsoleNotificationMessage.FILE_ADDED, filename);
			
		} catch (FileAlreadyExistsException e) {
			this.svcConsole.printInYellow(ConsoleNotificationMessage.FILE_UNMODIFIED, e.getFile());
		} catch (FileNotFoundException e) {
			this.svcConsole.printInRed(e.getMessage());
		}
	}
}

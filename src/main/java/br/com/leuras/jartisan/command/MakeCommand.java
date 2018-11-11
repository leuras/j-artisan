package br.com.leuras.jartisan.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MakeCommand {
	
	@ShellMethod("Create a new controller class")
    public void makeController() {
        
    }
	
	@ShellMethod("Create a new DAO interface and your implementation class")
    public void makeDao() {
        
    }
	
	@ShellMethod("Create a new service class")
    public void makeService() {
        
    }
}

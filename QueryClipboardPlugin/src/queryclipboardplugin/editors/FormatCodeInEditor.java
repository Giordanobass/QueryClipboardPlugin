package queryclipboardplugin.editors;

import org.eclipse.core.commands.Command;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

public class FormatCodeInEditor {

	void formatCodeInEditor() {
		try {
			IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench()
					.getService(IHandlerService.class);
			ICommandService commandService = (ICommandService) PlatformUI.getWorkbench()
					.getService(ICommandService.class);

			// Verifica se o comando de formatação está disponível
			Command formatCommand = commandService.getCommand("org.eclipse.jdt.ui.edit.text.java.format");
			if (formatCommand.isDefined()) {
				handlerService.executeCommand("org.eclipse.jdt.ui.edit.text.java.format", null);
				System.out.println("Código formatado.");
			} else {
				System.out.println("Comando de formatação não está disponível.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

package queryclipboardplugin.editors;
import org.eclipse.core.commands.Command;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.texteditor.ITextEditor;

public class FormatCodeInEditor {

    void formatCodeInEditor(ITextEditor textEditor, int startOffset, int length) {
        try {
            ICommandService commandService = (ICommandService) PlatformUI.getWorkbench()
                    .getService(ICommandService.class);
            IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench()
                    .getService(IHandlerService.class);

            Command formatCommand = commandService.getCommand("org.eclipse.jdt.ui.edit.text.java.format");
            if (formatCommand.isDefined()) {
                // Seleciona apenas o trecho recentemente adicionado
                textEditor.selectAndReveal(startOffset, length);

                handlerService.executeCommand("org.eclipse.jdt.ui.edit.text.java.format", null);

                // Remove a seleção após a formatação
                textEditor.selectAndReveal(0, 0);

                System.out.println("Código formatado.");
            } else {
                System.out.println("Comando de formatação não está disponível.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

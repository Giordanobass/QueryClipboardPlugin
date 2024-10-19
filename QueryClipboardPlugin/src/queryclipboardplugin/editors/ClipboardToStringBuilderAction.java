package queryclipboardplugin.editors;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.texteditor.ITextEditor;

public class ClipboardToStringBuilderAction extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			System.out.println("Atalho Ctrl+6 acionado.");
			// Pega o conteúdo da área de transferência
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable content = clipboard.getContents(null);

			if (content != null && content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				// Converte o conteúdo para String
				String clipboardText = (String) content.getTransferData(DataFlavor.stringFlavor);
				String[] queryLines = clipboardText.split("\n");

				// Pega o editor ativo e verifica se é um editor de texto
				IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
				IEditorPart editor = window.getActivePage().getActiveEditor();

				if (editor instanceof ITextEditor) {
					ITextEditor textEditor = (ITextEditor) editor;

					// Adiciona cada linha da query ao StringBuilder no arquivo Java
					addQueryToJavaFile(textEditor, queryLines);
				}
			} else {
				System.out.println("O editor ativo não é um editor de texto.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void addQueryToJavaFile(ITextEditor textEditor, String[] queryLines) {

		try {
			// Obter o documento do editor
			IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());

			// Obter a posição do cursor no documento
			ITextSelection selection = (ITextSelection) textEditor.getSelectionProvider().getSelection();
			int cursorPosition = selection.getOffset();

			// Cria o conteúdo a ser adicionado
			StringBuilder sb = new StringBuilder();
			for (String line : queryLines) {
				sb.append("sql.append(\" ").append(line.trim()).append("\");\n");
			}

			// Insere o conteúdo da área de transferência na posição do cursor
			document.replace(cursorPosition, 0, sb.toString());
			
			// Aplicar formatação de código
	        formatCodeInEditor();

			System.out.println("Conteúdo adicionado na posição do cursor.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void formatCodeInEditor() {
	    try {
	        IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
	        ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);

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

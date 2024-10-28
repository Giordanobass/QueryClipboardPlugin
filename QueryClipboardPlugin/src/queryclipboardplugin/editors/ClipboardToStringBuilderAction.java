package queryclipboardplugin.editors;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.ITextEditor;

public class ClipboardToStringBuilderAction extends AbstractHandler {

	AditionalQueryToJavaFile addQuery = new AditionalQueryToJavaFile();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			System.out.println("Atalho Ctrl+8 acionado.");
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
					addQuery.addQueryToJavaFile(textEditor, queryLines);
				}
			} else {
				System.out.println("O editor ativo não é um editor de texto.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
